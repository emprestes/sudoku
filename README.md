# Sudoku Project

Implementação de domínio de Sudoku em Java, organizada em dois módulos Maven:

- **core-api**: interfaces e tipos de domínio (Board, Region, Row, Column, Position, Dimension, SymbolValues, exceções).
- **core**: implementações concretas do domínio (SudokuBoard, SudokuRegion, SudokuRow, SudokuColumn, SudokuPosition) e lógica de inicialização/validação.

## Build

- **JDK**: 25 (toolchain `openJDK`, configurado para `~/.sdkman/candidates/java/25.0.2-amzn`).
- **Maven**: usar `MAVEN_OPTS="--enable-native-access=ALL-UNNAMED"` para suprimir o aviso do jansi/guava no JDK 25.
- Comandos principais:
  ```bash
  mvn test
  mvn clean install
  ```

## Notas de implementação

- **Preenchimento inicial**: `SudokuBoard` usa backtracking para preencher o tabuleiro, garantindo consistência sem falhas aleatórias.
- **Sem module-info**: build usa classpath simples (`useModulePath=false`) para compatibilidade com JDK 25.
- **Javadoc**: pacotes `emprestes.game.sudoku.domain` e `...domain.exception` documentados; classes de `core` e `core-api` têm descrições de alto nível para geração de site.

## Estrutura

- `core-api/src/main/java/emprestes/game/sudoku/domain`: contratos do domínio (interfaces, enums, exceções).
- `core/src/main/java/emprestes/game/sudoku/domain/model`: implementações concretas do domínio.
- Testes em `core/src/test/java` cobrem comparabilidade, equals/hashCode, serialização e validação de tabuleiro.
