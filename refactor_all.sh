#!/bin/bash
set -e

PROJECT_DIR="/home/user/castanhero-legacy"
REFACTOR_SCRIPT="$PROJECT_DIR/refactor_lombok.py"

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Contadores
total_files=0
modified_files=0
failed_files=0

echo "========================================="
echo "Refactorización Lombok - Iniciando"
echo "========================================="
echo ""

# Encontrar todos los archivos Java
echo "Buscando archivos..."
servicios_files=$(find "$PROJECT_DIR/src/main/java/uap/usic/siga/servicios" -maxdepth 1 -name "*.java" -type f | sort)
controllers_files=$(find "$PROJECT_DIR/src/main/java/uap/usic/siga/web" -name "*.java" -type f | sort)

all_files=()
while IFS= read -r line; do
    [[ -n "$line" ]] && all_files+=("$line")
done <<< "$servicios_files"

while IFS= read -r line; do
    [[ -n "$line" ]] && all_files+=("$line")
done <<< "$controllers_files"

total_files=${#all_files[@]}
echo -e "${GREEN}Encontrados $total_files archivos${NC}"
echo ""

# Procesar archivos
counter=0
for file in "${all_files[@]}"; do
    counter=$((counter + 1))
    echo -n "[$counter/$total_files] Procesando: $(basename "$file")... "

    if python3 "$REFACTOR_SCRIPT" "$file" 2>/dev/null | grep -q "Refactorizado"; then
        modified_files=$((modified_files + 1))
        echo -e "${GREEN}✓ Refactorizado${NC}"
    else
        echo -e "${YELLOW}- Sin cambios${NC}"
    fi

    # Verificar compilación cada 25 archivos
    if [ $((counter % 25)) -eq 0 ]; then
        echo ""
        echo -e "${YELLOW}Verificando compilación después de $counter archivos...${NC}"
        if mvn compile -f "$PROJECT_DIR/pom.xml" -q 2>&1 | grep -q "BUILD SUCCESS"; then
            echo -e "${GREEN}✓ Compilación exitosa${NC}"
        else
            echo -e "${RED}✗ Error de compilación${NC}"
            echo "Ejecutando compilación con detalles..."
            mvn compile -f "$PROJECT_DIR/pom.xml" -e
            exit 1
        fi
        echo ""
    fi
done

echo ""
echo "========================================="
echo "Resumen de Refactorización"
echo "========================================="
echo -e "Total de archivos procesados: ${GREEN}$total_files${NC}"
echo -e "Archivos modificados: ${GREEN}$modified_files${NC}"
echo -e "Archivos sin cambios: ${YELLOW}$((total_files - modified_files))${NC}"
echo ""

# Compilación final
echo "Ejecutando compilación final..."
if mvn compile -f "$PROJECT_DIR/pom.xml" -q; then
    echo -e "${GREEN}✓ Compilación final exitosa${NC}"
else
    echo -e "${RED}✗ Error en compilación final${NC}"
    exit 1
fi

echo ""
echo -e "${GREEN}¡Refactorización completada con éxito!${NC}"
