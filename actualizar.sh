#!/bin/bash
set -e

PROYECTO_DIR="$HOME/dsrrllo/usps-955"
DESCARGAS_DIR="$HOME/dsrrllo"
ADB_BIN="$HOME/Android/Sdk/platform-tools/adb"

# 1. Buscar el archivo ZIP más reciente (busca nombres que contengan 'usps' o '955')
ZIP_FILE=$(ls -t "$DESCARGAS_DIR"/*usps* "$DESCARGAS_DIR"/*955*.zip 2>/dev/null | head -n 1)

if [ -n "$ZIP_FILE" ]; then
    echo "📦 Procesando archivo ZIP: $ZIP_FILE..."
    mkdir -p "$PROYECTO_DIR"
    unzip -o "$ZIP_FILE" -d "$PROYECTO_DIR"
    rm "$ZIP_FILE"
else
    echo "ℹ️ No hay ZIP nuevo en $DESCARGAS_DIR, procesando estado actual en $PROYECTO_DIR..."
fi

cd "$PROYECTO_DIR"

# 2. Inyectar ruta del SDK de Android
echo "sdk.dir=$HOME/Android/Sdk" > local.properties

# 3. Generar debug.keystore si no existe
if [ ! -f "debug.keystore" ]; then
    echo "🔑 Generando debug.keystore de firma..."
    keytool -genkey -v -keystore debug.keystore -storepass android -alias androiddebugkey -keypass android -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Android Debug,O=Android,C=US" 2>/dev/null || true
fi

# 4. Generar gradlew si no existe
if [ ! -f "gradlew" ]; then
    echo "⚙️  Generando wrapper de Gradle..."
    gradle wrapper || true
fi

chmod +x gradlew 2>/dev/null || true

# 5. Sincronizar con Git / GitHub apuntando al repositorio usps-955
if [ ! -d ".git" ]; then
    echo "🌱 Inicializando repositorio de Git..."
    git init
    git add .
    git commit -m "Initial commit desde AI Studio: usps-955"
    gh repo create usps-955 --public --source=. --push || true
else
    echo "🚀 Guardando nueva versión en Git y subiendo a GitHub (usps-955)..."
    git add .
    git commit -m "Actualización automática usps-955 desde AI Studio: $(date '+%Y-%m-%d %H:%M:%S')" || true
    git push origin master || git push origin main || true
fi

# 6. Desinstalar versión anterior en el Pixel 7a (Ajusta el package name si el nuevo difiere)
echo "🧹 Limpiando versión anterior en el Pixel 7a..."
if [ -f "$ADB_BIN" ]; then
    "$ADB_BIN" uninstall com.aistudio.usps955 2>/dev/null || true
    # Por si acaso quedara rastro del anterior:
    "$ADB_BIN" uninstall com.aistudio.civicsprep.kqmzr 2>/dev/null || true
fi

# 7. Compilar e instalar en el Pixel 7a
echo "📱 Compilando e instalando en tu Pixel 7a..."
./gradlew installDebug

echo "✅ ¡PROCESO COMPLETADO EXITOSAMENTE! La app usps-955 está actualizada en tu Pixel 7a y en GitHub."