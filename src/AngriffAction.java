import javax.swing.*;
import java.awt.event.ActionEvent;

public class AngriffAction extends AbstractAction {
    private final Monster monster;
    private final JLabel statusLabel;

    public AngriffAction(JLabel statusLabel, Monster monster) {
        super("Angriff");
        this.monster = monster;
        this.statusLabel = statusLabel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.monster.setHp(this.monster.getHp() - 5);

        int playerDamage = Player.getInstance().attack(this.monster);
        if (playerDamage == -1) {
            this.statusLabel.setText("Spieler verfehlt!");
        } else {
            this.statusLabel.setText(String.format("Spieler trifft und macht %d Schaden!", playerDamage));
        }

    }
}
