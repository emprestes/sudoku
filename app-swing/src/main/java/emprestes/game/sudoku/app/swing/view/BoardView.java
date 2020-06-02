package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.service.BoardService;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class BoardView extends JPanel {

    private static final long serialVersionUID = 2337305186388467101L;

    private final BoardService service;

    public BoardView(BoardService service) {
        super();

        this.service = service;

        initView();
        init();
    }

    private void initView() {
        service.start(region -> add(new RegionView(region)));
    }

    private void init() {
        setLayout(new GridLayout(0, service.getSide()));
    }
}
