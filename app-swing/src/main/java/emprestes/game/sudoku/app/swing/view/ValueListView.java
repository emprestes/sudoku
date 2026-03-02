package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.ValueButton;
import emprestes.game.sudoku.app.swing.controller.GameController;
import emprestes.game.sudoku.domain.GameDimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel displaying value buttons for number entry, plus a clear button.
 */
public class ValueListView extends JPanel {

    private static final long serialVersionUID = 368856940315102671L;

    private final GameDimension dimension;
    private final GameController controller;
    private final List<ValueButton> values;

    public ValueListView(GameController controller) {
        this(GameDimension.D3X3, controller);
    }

    public ValueListView(GameDimension dimension, GameController controller) {
        super();
        this.dimension = dimension;
        this.controller = controller;
        this.values = new ArrayList<>();

        initComponents();
        initView();
        init();
    }

    private void initComponents() {
        dimension.symbols.forEach(symbol -> values.add(new ValueButton(symbol, controller)));
    }

    private void initView() {
        values.forEach(this::add);

        // Clear button
        JButton clearButton = new JButton("✕");
        clearButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        clearButton.setForeground(Color.RED);
        clearButton.addActionListener(e -> controller.clearSelected());
        add(clearButton);
    }

    private void init() {
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
        setLayout(new GridLayout(0, dimension.size + 1));
    }
}
