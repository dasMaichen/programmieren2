import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by mai on 05.07.15.
 */
public class ZauberAction extends AbstractAction {

    private final JLabel statusLabel;
    private final Monster monster;

    private Action DOLL_HAUEN = new AbstractAction(String.format("Harter Schlag (%d AP, %d%% Selbstschaden)", Player
            .HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT)) {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int playerDamage = Player.getInstance().hardHit(monster);

            if (playerDamage != -1) {
                String status = "Spieler schlägt hart zu!";
                status += String.format(" Spieler verursacht %d Schaden!", playerDamage);
                status += String.format(" Spieler verursacht %d Selbstschaden!",
                        playerDamage * Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100);
                statusLabel.setText(status);
            } else {
                statusLabel.setText("Nicht genügend AP!");
            }
        }

        @Override
        public String toString() {
            return getValue("Name").toString();
        }
    };

    private Action FEUERBALL = new AbstractAction(String.format("Feuerball (%d AP)", Player.FIREBALL_COST)) {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int playerDamage = Player.getInstance().fireball(monster);
            if (playerDamage != -1) {
                String status = "Spieler schießt einen Feuerball!";
                status += String.format(" Spieler verursacht %d Schaden!%n", playerDamage);
                statusLabel.setText(status);
            } else {
                statusLabel.setText("Nicht genügend AP!");
            }
        }

        @Override
        public String toString() {
            return getValue("Name").toString();
        }
    };

    private Action NEU_WUERFELN = new AbstractAction(String.format("ATK auswürfeln (%d AP)", Player.REROLL_COST)) {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (Player.getInstance().reroll()) {
                String status = "ATK neu ausgewürfelt! ";
                status += "Jetz neu: " + Player.getInstance().getAtk();
                statusLabel.setText(status);
            } else {
                statusLabel.setText("Nicht genügend AP!");
            }
        }

        @Override
        public String toString() {
            return getValue("Name").toString();
        }
    };

    public ZauberAction(JLabel statusLabel, Monster monster) {
        super("Zauber");
        this.statusLabel = statusLabel;
        this.monster = monster;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Action[] actions = new Action[]{DOLL_HAUEN, FEUERBALL, NEU_WUERFELN};

        String dialogTitle = getValue("Name").toString();

        int actionIndex = JOptionPane.showOptionDialog(null, "Welcher Zauber soll ausgeführt werden?", dialogTitle,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, actions, null);

        if (actionIndex == -1) {
            statusLabel.setText("Fehlerhafte Aktion!");
        } else {
            actions[actionIndex].actionPerformed(actionEvent);
        }
    }

}
