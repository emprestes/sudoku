# Core

Implementação do jogo Sudoku usando a API de domínio do módulo `core-api`.
Inclui modelo de tabuleiro, regiões, linhas, colunas e posições, além de
lógica de preenchimento/backtracking e verificação de jogo.

## Destaques
- `SudokuBoard`: geração de tabuleiro com backtracking para garantir estados válidos
- Modelos concretos: `SudokuRegion`, `SudokuRow`, `SudokuColumn`, `SudokuPosition`
- Suporte a dimensões definidas em `Dimension` (padrão 3x3)

## Build e testes
```bash
JAVA_HOME=$HOME/.sdkman/candidates/java/25.0.2-amzn \
MAVEN_OPTS="--enable-native-access=ALL-UNNAMED" \
mvn -pl core -am test
```

## Dependência
Consome as interfaces e tipos de `core-api` para manter a separação entre
contratos e implementação.
