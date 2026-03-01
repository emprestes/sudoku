package emprestes.game.sudoku.app.swing.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagLayout;

public class WelcomeView extends JPanel {

    private static final long serialVersionUID = 7482901563748291056L;

    public WelcomeView() {
        super(new GridBagLayout());

        initView();
    }

    private void initView() {
        final var title = new JLabel("Sudoku");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 48));
        add(title);
    }
}
