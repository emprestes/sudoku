package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.ValueButton;
import emprestes.game.sudoku.domain.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ValueListView extends JPanel {

    private static final long serialVersionUID = 368856940315102671L;

    private final Dimension dimension;
    private final List<ValueButton> values;

    ValueListView() {
        this(Dimension.D3X3);
    }

    ValueListView(Dimension dimension) {
        super();

        this.dimension = dimension;
        this.values = new ArrayList<>();

        initComponents();
        initView();
        init();
    }

    private void initComponents() {
        // FIXME Use dimension here
        values.add(new ValueButton('1'));
        values.add(new ValueButton('2'));
        values.add(new ValueButton('3'));
        values.add(new ValueButton('4'));
        values.add(new ValueButton('5'));
        values.add(new ValueButton('6'));
        values.add(new ValueButton('7'));
        values.add(new ValueButton('8'));
        values.add(new ValueButton('9'));
    }

    private void initView() {
        values.forEach(this::add);
    }

    private void init() {
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
        setLayout(new GridLayout(0, dimension.size));
    }
}
