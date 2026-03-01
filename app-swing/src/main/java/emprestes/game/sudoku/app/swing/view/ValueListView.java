package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.ValueButton;
import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.GameSymbol;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ValueListView extends JPanel {

    private static final long serialVersionUID = 368856940315102671L;

    private final GameDimension dimension;
    private final List<ValueButton> values;

    ValueListView() {
        this(GameDimension.D3X3);
    }

    ValueListView(GameDimension dimension) {
        super();

        this.dimension = dimension;
        this.values = new ArrayList<>();

        initComponents();
        initView();
        init();
    }

    private void initComponents() {
        dimension.symbols.forEach(symbol -> values.add(new ValueButton(symbol)));
    }

    private void initView() {
        values.forEach(this::add);
    }

    private void init() {
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
        setLayout(new GridLayout(0, dimension.size));
    }
}
