#!/usr/bin/env sh
# Bootstrap simples do Gradle: baixa a versão fixada no gradle-wrapper.properties.
set -eu

GRADLE_VERSION="9.3.1"
BOOTSTRAP_DIR="${GRADLE_USER_HOME:-$HOME/.gradle}/bootstrap"
GRADLE_HOME="$BOOTSTRAP_DIR/gradle-$GRADLE_VERSION"
ZIP_FILE="$BOOTSTRAP_DIR/gradle-$GRADLE_VERSION-bin.zip"

if [ ! -x "$GRADLE_HOME/bin/gradle" ]; then
    mkdir -p "$BOOTSTRAP_DIR"
    if [ ! -f "$ZIP_FILE" ]; then
        URL="https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
        if command -v curl >/dev/null 2>&1; then
            curl --fail --location "$URL" --output "$ZIP_FILE"
        elif command -v wget >/dev/null 2>&1; then
            wget --https-only "$URL" -O "$ZIP_FILE"
        else
            echo "Erro: instale curl ou wget para baixar o Gradle." >&2
            exit 1
        fi
    fi

    if ! command -v unzip >/dev/null 2>&1; then
        echo "Erro: instale unzip para extrair o Gradle." >&2
        exit 1
    fi
    unzip -q -o "$ZIP_FILE" -d "$BOOTSTRAP_DIR"
fi

exec "$GRADLE_HOME/bin/gradle" "$@"
