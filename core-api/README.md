# Core API

Contratos públicos para o domínio do Sudoku: tabuleiro, região, linha, coluna,
posições, valores e exceções.

## Conteúdo
- Interfaces: `Board`, `Region`, `Row`, `Column`, `Position`
- Tipos de suporte: `Dimension`, `SymbolValues`, inicialização (`InitRegion`, `InitPosition`)
- Exceções de regra de negócio em `emprestes.game.sudoku.domain.exception`
- `package-info.java` com visão geral dos pacotes

## Uso
Esta API é usada pelo módulo `core` para fornecer a implementação concreta
do jogo. Inclua este módulo como dependência para implementar ou consumir
regras do Sudoku sem depender das classes concretas.

## Build e testes
```bash
JAVA_HOME=$HOME/.sdkman/candidates/java/25.0.2-amzn \
MAVEN_OPTS="--enable-native-access=ALL-UNNAMED" \
mvn -pl core-api -am test
```
