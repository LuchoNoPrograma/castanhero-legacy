# Instrucciones de Compilación Post-Refactorización

## Estado Actual

✅ **Refactorización completada exitosamente**
⚠️ **Compilación pendiente** (problemas de red con Maven)

## Verificar Compilación

Cuando tengas conexión a internet, ejecuta:

```bash
cd /home/user/castanhero-legacy
mvn clean compile
```

## Si Hay Errores de Compilación

### 1. Verificar Lombok en el IDE

Si usas IntelliJ IDEA:
- Instalar plugin de Lombok: `Settings > Plugins > Lombok`
- Habilitar annotation processing: `Settings > Build > Compiler > Annotation Processors > Enable annotation processing`

Si usas Eclipse:
- Descargar lombok.jar de https://projectlombok.org/download
- Ejecutar: `java -jar lombok.jar`
- Seguir las instrucciones para instalar en Eclipse

### 2. Verificar Dependencia de Lombok en pom.xml

Asegúrate de que exista:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### 3. Limpiar y Recompilar

```bash
mvn clean install -U
```

## Ejecutar Tests

```bash
mvn test
```

## Construcción Final

```bash
mvn clean package
```

## Archivos de Ayuda

- `REFACTORING_REPORT.md` - Reporte completo de cambios
- `REFACTORING_EXAMPLES.md` - Ejemplos de transformaciones
- `refactor_lombok.py` - Script usado para refactorización
- `refactor_all.sh` - Script batch (opcional)

## Verificación Rápida

Para verificar que todos los archivos tienen las anotaciones correctas:

```bash
# Contar archivos con @RequiredArgsConstructor
grep -r "@RequiredArgsConstructor" src/main/java/uap/usic/siga/{servicios,web} --include="*.java" | wc -l

# Debería mostrar: 64+ archivos
```

## Rollback (si es necesario)

Si necesitas revertir los cambios:

```bash
git checkout src/main/java/uap/usic/siga/servicios/
git checkout src/main/java/uap/usic/siga/web/
```

---

**Nota**: La refactorización NO cambió la lógica de negocio, solo la forma de inyección de dependencias.
