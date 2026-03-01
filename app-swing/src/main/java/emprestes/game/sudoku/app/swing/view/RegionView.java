package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.PositionButton;
import emprestes.game.sudoku.app.swing.controller.GameController;
import emprestes.game.sudoku.domain.IRegion;

import javax.swing.JPanel;
import java.awt.GridLayout;

import static javax.swing.BorderFactory.createRaisedSoftBevelBorder;

public class RegionView extends JPanel {

    private static final long serialVersionUID = 4131847772422861702L;

    private final IRegion region;
    private final byte side;
    private final GameController controller;

    public RegionView(IRegion region, byte side, GameController controller) {
        super();
        this.region = region;
        this.side = side;
        this.controller = controller;

        initComponents();
        init();
    }

    private void init() {
        setLayout(new GridLayout(0, side));
        setBorder(createRaisedSoftBevelBorder());
    }

    public void initComponents() {
        final byte size = (byte) (side * side);
        for (byte row = 1; row <= size; row++) {
            if (region.existsRow(row)) {
                region.getRowBy(row).positionList().stream()
                        .filter(position -> !position.hasChangedRegion(region))
                        .forEach(position -> add(new PositionButton(position, controller)));
            }
        }
    }
}
