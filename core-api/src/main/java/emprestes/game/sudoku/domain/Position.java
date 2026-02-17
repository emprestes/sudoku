package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

/**
 * Representa uma célula de Sudoku com coordenadas, valor e regras de validação/visibilidade.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Position extends Serializable, Comparable<Position> {

    /** @return região da posição. */
    Region getRegion();

    /** @return linha da posição. */
    Row getRow();

    /** @return número da linha (1-based). */
    Byte getRowNumber();

    /** @return coluna da posição. */
    Column getColumn();

    /** @return número da coluna (1-based). */
    Byte getColumnNumber();

    /** @return valor atual da posição. */
    Character getValue();

    /** @return todos os símbolos já presentes em região/linha/coluna. */
    Character[] getAllExistSymbols();

    /** Define valor da posição. */
    void setValue(Character value);

    default void setValue(Value value) {
        setValue(value.getSymbol());
    }

    /** Executa jogada com validação de regras. */
    void play(Character value) throws WrongPositionException;

    /** @return true se o valor está na coluna. */
    boolean inColumn(Character value);

    default boolean notInColumn(Value value) {
        return notInColumn(value.getSymbol());
    }

    default boolean notInColumn(Character value) {
        return !inColumn(value);
    }

    /** @return true se o valor está na linha. */
    boolean inRow(Character value);

    default boolean notInRow(Value value) {
        return notInRow(value.getSymbol());
    }

    default boolean notInRow(Character value) {
        return !inRow(value);
    }

    /** @return true se o valor está na região. */
    boolean inRegion(Character value);

    default boolean notInRegion(Value value) {
        return notInRegion(value.getSymbol());
    }

    default boolean notInRegion(Character value) {
        return !inRegion(value);
    }

    /** Limpa o valor. */
    void clear();

    /** @return true se está em branco. */
    boolean isBlank();

    default boolean nonBlank() {
        return !isBlank();
    }

    /** Verifica se o símbolo é válido para a posição. */
    boolean isValidFor(Character symbol);

    default boolean isValidFor(Value value) {
        return isValidFor(value.getSymbol());
    }

    /** @return true se está visível. */
    boolean isVisible();

    /** Define visibilidade. */
    void setVisible(boolean visible);

    default boolean isInvalidFor(Character value) {
        return !isValidFor(value);
    }

    default boolean isInvalidFor(Value value) {
        return isInvalidFor(value.getSymbol());
    }

    default boolean isInvisible() {
        return !isVisible();
    }

    /** Compara coordenadas com números informados. */
    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);

    default boolean equals(Character value) {
        return getValue().equals(value);
    }
}
