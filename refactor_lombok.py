#!/usr/bin/env python3
"""
Script para refactorizar archivos Java a usar Lombok @RequiredArgsConstructor
"""
import re
import sys
from pathlib import Path
from typing import List, Tuple


class LombokRefactor:
    def __init__(self, file_path: str):
        self.file_path = Path(file_path)
        self.content = self.file_path.read_text(encoding='utf-8')
        self.lines = self.content.split('\n')
        self.modified = False

    def needs_refactoring(self) -> bool:
        """Verifica si el archivo necesita refactorización"""
        # Si ya tiene @RequiredArgsConstructor, probablemente ya está refactorizado
        if '@RequiredArgsConstructor' in self.content:
            return False
        # Si tiene @Autowired, necesita refactorización
        if '@Autowired' in self.content:
            return True
        return False

    def add_lombok_imports(self) -> None:
        """Agrega los imports de Lombok si no existen"""
        has_required_args = 'import lombok.RequiredArgsConstructor;' in self.content
        has_slf4j = 'import lombok.extern.slf4j.Slf4j;' in self.content

        if has_required_args and has_slf4j:
            return

        # Encontrar la posición después del package y antes de los imports
        import_start = -1
        for i, line in enumerate(self.lines):
            if line.strip().startswith('import '):
                import_start = i
                break

        if import_start == -1:
            # No hay imports, buscar después del package
            for i, line in enumerate(self.lines):
                if line.strip().startswith('package '):
                    import_start = i + 1
                    break

        # Insertar imports de Lombok
        imports_to_add = []
        if not has_required_args:
            imports_to_add.append('import lombok.RequiredArgsConstructor;')
        if not has_slf4j:
            imports_to_add.append('import lombok.extern.slf4j.Slf4j;')

        if imports_to_add and import_start != -1:
            # Buscar línea vacía después del package
            insert_pos = import_start
            while insert_pos < len(self.lines) and not self.lines[insert_pos].strip():
                insert_pos += 1

            for imp in reversed(imports_to_add):
                self.lines.insert(insert_pos, imp)

            self.modified = True

    def add_lombok_annotations(self) -> None:
        """Agrega @RequiredArgsConstructor y @Slf4j a la clase"""
        # Buscar la declaración de la clase
        class_pattern = re.compile(r'^(public\s+)?(@\w+\s+)*(class|interface)\s+\w+')

        for i, line in enumerate(self.lines):
            if class_pattern.search(line.strip()):
                # Verificar si ya tiene las anotaciones
                has_required = False
                has_slf4j = False

                # Buscar anotaciones previas
                j = i - 1
                while j >= 0 and (self.lines[j].strip().startswith('@') or not self.lines[j].strip()):
                    if '@RequiredArgsConstructor' in self.lines[j]:
                        has_required = True
                    if '@Slf4j' in self.lines[j]:
                        has_slf4j = True
                    if self.lines[j].strip() and not self.lines[j].strip().startswith('@'):
                        break
                    j -= 1

                # Insertar anotaciones si no existen
                if not has_slf4j:
                    self.lines.insert(i, '@Slf4j')
                    i += 1
                    self.modified = True
                if not has_required:
                    self.lines.insert(i, '@RequiredArgsConstructor')
                    self.modified = True

                break

    def convert_autowired_to_final(self) -> None:
        """Convierte @Autowired private X a private final X"""
        i = 0
        while i < len(self.lines):
            line = self.lines[i]

            # Detectar @Autowired
            if line.strip() == '@Autowired':
                # La siguiente línea debería ser el campo
                if i + 1 < len(self.lines):
                    next_line = self.lines[i + 1]

                    # Convertir "private X" a "private final X"
                    if 'private ' in next_line and 'final' not in next_line:
                        # Eliminar @Autowired
                        self.lines.pop(i)

                        # Modificar la siguiente línea para agregar final
                        next_line = self.lines[i]
                        next_line = next_line.replace('private ', 'private final ', 1)
                        self.lines[i] = next_line
                        self.modified = True
                        continue

            i += 1

    def get_final_fields(self) -> List[str]:
        """Obtiene la lista de campos final declarados en la clase"""
        final_fields = []
        in_class = False

        for line in self.lines:
            # Detectar inicio de clase
            if re.search(r'(public\s+)?class\s+\w+', line):
                in_class = True
                continue

            if in_class:
                # Detectar campos final
                match = re.match(r'\s*private\s+final\s+\w+(<[^>]+>)?\s+(\w+);', line)
                if match:
                    final_fields.append(match.group(2))

        return final_fields

    def remove_constructor_if_simple(self) -> None:
        """Elimina constructores que solo hacen asignación de dependencias"""
        # Si hay @RequiredArgsConstructor, podemos eliminar constructores simples
        if '@RequiredArgsConstructor' not in '\n'.join(self.lines):
            return

        # Extraer el nombre de la clase
        class_name = None
        for line in self.lines:
            match = re.search(r'(public\s+)?class\s+(\w+)', line)
            if match:
                class_name = match.group(2)
                break

        if not class_name:
            return

        # Buscar constructores
        i = 0
        while i < len(self.lines):
            line = self.lines[i].strip()

            # Detectar inicio de constructor con el nombre de la clase
            constructor_pattern = re.compile(rf'(public|private|protected)?\s*{class_name}\s*\([^)]*\)')
            if constructor_pattern.search(line):
                # Encontrar el cierre del constructor
                start_line = i

                # Manejar caso donde { está en la misma línea o siguiente
                if '{' in line:
                    brace_count = line.count('{') - line.count('}')
                    j = i + 1
                else:
                    # Buscar la línea con {
                    j = i + 1
                    while j < len(self.lines) and '{' not in self.lines[j]:
                        j += 1
                    if j < len(self.lines):
                        brace_count = self.lines[j].count('{') - self.lines[j].count('}')
                        j += 1
                    else:
                        i += 1
                        continue

                # Encontrar el cierre del constructor
                while j < len(self.lines) and brace_count > 0:
                    brace_count += self.lines[j].count('{') - self.lines[j].count('}')
                    j += 1

                end_line = j

                # Verificar si solo contiene asignaciones del tipo "this.x = x;"
                is_simple = True
                has_content = False

                for line_idx in range(start_line, end_line):
                    stripped = self.lines[line_idx].strip()

                    # Ignorar líneas vacías, comentarios, y la declaración del constructor
                    if not stripped or stripped.startswith('//') or constructor_pattern.search(stripped) or stripped == '{' or stripped == '}':
                        continue

                    has_content = True

                    # Debe ser una asignación simple
                    if not re.match(r'this\.\w+\s*=\s*\w+;', stripped):
                        is_simple = False
                        break

                # Si tiene contenido y es simple (solo asignaciones), eliminar
                if has_content and is_simple:
                    # También eliminar líneas en blanco antes del constructor
                    while start_line > 0 and not self.lines[start_line - 1].strip():
                        start_line -= 1

                    # Eliminar el constructor
                    del self.lines[start_line:end_line]
                    self.modified = True
                    # No incrementar i porque eliminamos líneas
                    continue

            i += 1

    def save(self) -> None:
        """Guarda el archivo modificado"""
        if self.modified:
            content = '\n'.join(self.lines)
            self.file_path.write_text(content, encoding='utf-8')

    def refactor(self) -> bool:
        """Ejecuta la refactorización completa"""
        if not self.needs_refactoring():
            return False

        self.add_lombok_imports()
        self.add_lombok_annotations()
        self.convert_autowired_to_final()
        self.remove_constructor_if_simple()
        self.save()

        return self.modified


def main():
    if len(sys.argv) < 2:
        print("Uso: python refactor_lombok.py <archivo.java>")
        sys.exit(1)

    file_path = sys.argv[1]
    refactor = LombokRefactor(file_path)

    if refactor.refactor():
        print(f"✓ Refactorizado: {file_path}")
    else:
        print(f"- Sin cambios: {file_path}")


if __name__ == '__main__':
    main()
