package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.domain.Region;
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
        service.start(this::add);
    }

    private void init() {
        // FIXME Use dimension coming from service
        setLayout(new GridLayout(0, 3));
    }

    private void add(Region region) {
        add(new RegionView(region));
    }
}
