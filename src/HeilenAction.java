import javax.swing.*;
import java.awt.event.ActionEvent;

public class HeilenAction extends AbstractAction {

    private final JLabel statusLabel;

    public HeilenAction(JLabel statusLabel) {
        super("Trank trinken");
        this.statusLabel = statusLabel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Player.getInstance().heal()) {
            this.statusLabel.setText("Spieler heilt sich!");
        } else {
            this.statusLabel.setText("Nicht genügend Heiltränke!");
        }
    }
}
