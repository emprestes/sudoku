    package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Board;
import emprestes.game.sudoku.domain.Column;
import emprestes.game.sudoku.domain.Dimension;
import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.Region;
import emprestes.game.sudoku.domain.Row;
import emprestes.game.sudoku.domain.SymbolValues;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.PositionNotFoundException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static emprestes.game.sudoku.domain.Dimension.D3X3;
import static java.util.Optional.ofNullable;

/**
 * Implementação concreta de Board com preenchimento inicial via backtracking.
 *
 * @author Dude
 * @since 02/2026
 */
public final class SudokuBoard implements Board {

    @Serial
    private static final long serialVersionUID = 5804390727980289178L;

    private final Dimension dimension;
    private final SymbolValues symbols;
    private final List<Region> regionList;


    public SudokuBoard() {
        this(D3X3);
    }

    public SudokuBoard(Dimension dimension) {
        super();

        this.dimension = dimension;
        this.symbols = dimension.symbols;
        this.regionList = new ArrayList<>(dimension.size);

        init();
    }

    private void init() {
        init(regionList::add);
    }

    private Stream<Region> getRegionList() {
        return regionList.stream();
    }

    /** {@inheritDoc} */
    @Override
    public Byte getSide() {
        return dimension.side;
    }

    /** {@inheritDoc} */
    @Override
    public void init(Consumer<Region> action) {
        ofNullable(action).ifPresent(_action -> {
            Region region = null;
            Column column = null;
            Row row = null;
            byte fromRow, toRow, fromColumn, toColumn;

            fromRow = fromColumn = dimension.from();
            toRow = toColumn = dimension.to();

            for (byte regionNumber = 1; regionNumber <= dimension.size; regionNumber++) {
                if (regionNumber == 1) {
                    region = new SudokuRegion(regionNumber, dimension);
                } else {
                    region = region.next(regionNumber, dimension);
                }

                for (byte rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
                    final byte actualRowIndex = rowIndex;
                    final Row actualRow = row;
                    final Region finalRegion = region;
                    row = getRow(rowIndex)
                            .filter(region::nonExistsRow)
                            .map(region::add)
                            .orElseGet(() -> finalRegion.getRowOr(actualRowIndex, actualRow));
                    for (byte columnIndex = fromColumn; columnIndex <= toColumn; columnIndex++) {
                        final byte actualColumnIndex = columnIndex;
                        final Column actualColumn = column;
                        column = getColumn(columnIndex)
                                .filter(region::nonExistsColumn)
                                .map(region::add)
                                .orElseGet(() -> finalRegion.getColumnOr(actualColumnIndex, actualColumn));
                        region.createPositionFor(row, column);
                    }
                }

                _action.accept(region);

                fromColumn = region.nextFromColumn(toColumn, regionNumber);
                fromRow = region.nextFromRow(fromRow, regionNumber);

                toColumn = region.nextTo(fromColumn);
                toRow = region.nextTo(fromRow);
            }
        });
    }

    private Optional<Column> getColumn(byte number) {
        return regionList.stream()
                .filter(region -> region.existsColumn(number))
                .map(region -> region.getColumnBy(number))
                .findFirst();
    }

    private Optional<Row> getRow(byte number) {
        return regionList.stream()
                .filter(region -> region.existsRow(number))
                .map(region -> region.getRowBy(number))
                .findFirst();
    }

    /** {@inheritDoc} */
    @Override
    public void start() {
        clear();
        initValues();
    }

    private void clear() {
        regionList.forEach(Region::clear);
    }

    private void initValues() {
        final List<Position> positions = new ArrayList<>();
        getRegionList()
                .flatMap(Region::getRows)
                .forEach(row -> row.forEach(positions::add));

        fillValuesBacktracking(positions, 0);
    }

    private boolean fillValuesBacktracking(List<Position> positions, int index) {
        if (index >= positions.size()) {
            return true;
        }

        final Position position = positions.get(index);
        position.clear();

        final List<Character> candidates = symbols.shuffle().toList();
        for (Character symbol : candidates) {
            if (position.isInvalidFor(symbol)) {
                continue;
            }
            position.setValue(symbol);
            if (fillValuesBacktracking(positions, index + 1)) {
                return true;
            }
            position.clear();
        }

        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException {
        final Position position = regionList.stream()
                .filter(region -> region.equals(regionNumber))
                .map(region -> region.getBy(rowNumber, columnNumber))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new PositionNotFoundException("Position has not found for row(%s) and column(%s)",
                        rowNumber, columnNumber));

        play(value, position);
    }

    /** {@inheritDoc} */
    @Override
    public void play(Character value, Position position) throws WrongPositionException {
        position.play(value);
    }

    @Override
    public boolean isGameOver() {
        return regionList.stream()
                .map(Region::isCompleted)
                .reduce(true, ((result, isCompleted) -> result && isCompleted));
    }

    int getSizePositions() {
        return regionList.stream()
                .map(Region::getSizePositions)
                .reduce(0, Integer::sum);
    }

    int getSizeRegions() {
        return regionList.size();
    }

    int getSizeRows() {
        return regionList.stream()
                .map(Region::getSizeRows)
                .distinct()
                .reduce(0, Integer::sum);
    }

    int getSizeColumns() {
        return regionList.stream()
                .map(Region::getSizeColumns)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuBoard that)) return false;
        return dimension == that.dimension && Objects.equals(regionList, that.regionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimension, regionList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int boxSize = dimension.side;

        // Criar a linha horizontal
            String horizontalLine = createHorizontalLine(boxSize);

            // Organizar as regiões por linhas
            for (int rowGroup = 0; rowGroup < boxSize; rowGroup++) {
                // Adicionar linha horizontal no início de cada grupo de linhas
                sb.append(horizontalLine).append("\n");

                // Para cada linha dentro do grupo
                for (int rowInGroup = 0; rowInGroup < boxSize; rowInGroup++) {
                    byte rowNumber = (byte) (rowGroup * boxSize + rowInGroup + 1);

                    // Para cada grupo de colunas
                    for (int colGroup = 0; colGroup < boxSize; colGroup++) {
                        // Adicionar separador vertical no início de cada grupo
                        sb.append("| ");

                        // Para cada coluna dentro do grupo
                        for (int colInGroup = 0; colInGroup < boxSize; colInGroup++) {
                            byte colNumber = (byte) (colGroup * boxSize + colInGroup + 1);

                            // Calcular o número da região
                            byte regionNumber = (byte) (rowGroup * boxSize + colGroup + 1);

                            // Obter o valor da posição usando coordenadas absolutas
                            Character value = ' ';
                            try {
                                Optional<Region> region = regionList.stream()
                                        .filter(r -> r.equals(regionNumber))
                                        .findFirst();

                                if (region.isPresent()) {
                                    Optional<Position> position = region.get().getBy(rowNumber, colNumber);
                                    if (position.isPresent()) {
                                        value = position.get().getValue();
                                        if (value == null || value == '0') {
                                            value = ' ';
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                value = ' ';
                            }

                            sb.append(value).append(" ");
                        }
                    }
                    // Fechar a linha com separador vertical
                    sb.append("|\n");
                }
            }
            // Adicionar linha horizontal no final
            sb.append(horizontalLine);

            return sb.toString();
    }

    private String createHorizontalLine(int boxSize) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < boxSize; i++) {
            line.append("+");
            line.append("-".repeat(Math.max(0, boxSize * 2 + 1)));
        }
            line.append("+");
        return line.toString();
    }
}
