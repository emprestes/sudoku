package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.controller.GameController;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class BoardView extends JPanel {

    private static final long serialVersionUID = 2337305186388467101L;

    private final GameController controller;

    public BoardView(GameController controller) {
        super();
        this.controller = controller;

        initView();
        init();
    }

    private void initView() {
        controller.getService().start();

        final byte side = controller.getService().getSide();
        controller.getService().getBoard().forEachRegion(region -> add(new RegionView(region, side, controller)));
    }

    private void init() {
        setLayout(new GridLayout(0, controller.getService().getSide()));
    }
}
