package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.PositionButton;
import emprestes.game.sudoku.domain.Region;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

import static java.util.Optional.ofNullable;

public class RegionView extends JPanel {

    private static final long serialVersionUID = 4131847772422861702L;

    private final Region region;

    public RegionView(Region region) {
        super();

        this.region = region;

        initComponents();
        init();
    }

    private void init() {
        setLayout(new GridLayout(0, 3));
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
    }

    public void initComponents() {
        ofNullable(region)
                .ifPresent(_region -> _region.init(position -> add(new PositionButton(position))));
    }
}
