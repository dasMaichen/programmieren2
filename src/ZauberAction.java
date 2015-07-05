import javax.swing.*;
import java.awt.event.ActionEvent;

public class ZauberAction extends KampfAction {

    private Action DOLL_HAUEN = new AbstractAction(String.format("Harter Schlag (%d AP, %d%% Selbstschaden)", Player
            .HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT)) {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int playerDamage = Player.getInstance().hardHit(monster);

            if (playerDamage != -1) {
                setzeStatus("Spieler schlägt hart zu!");
                setzeStatus(String.format("Spieler verursacht %d Schaden!", playerDamage));
                setzeStatus(String.format("Spieler verursacht %d Selbstschaden!",
                        playerDamage * Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100));
            } else {
                setzeStatus("Nicht genügend AP!");
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
                setzeStatus("Spieler schießt einen Feuerball!");
                setzeStatus(String.format("Spieler verursacht %d Schaden!%n", playerDamage));
            } else {
                setzeStatus("Nicht genügend AP!");
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
                setzeStatus("ATK neu ausgewürfelt!");
                setzeStatus("Jetz neu: " + Player.getInstance().getAtk());
            } else {
                setzeStatus("Nicht genügend AP!");
            }
        }

        @Override
        public String toString() {
            return getValue("Name").toString();
        }
    };

    public ZauberAction(JLabel statusLabel, Monster monster) {
        super(statusLabel, monster, "Zauber");
    }

    @Override
    protected void fuehreSpielerAktionAus() {
        Action[] actions = new Action[]{DOLL_HAUEN, FEUERBALL, NEU_WUERFELN};

        String dialogTitle = getValue("Name").toString();

        int actionIndex = JOptionPane.showOptionDialog(null, "Welcher Zauber soll ausgeführt werden?", dialogTitle,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, actions, null);

        if (actionIndex == -1) {
            setzeStatus("Fehlerhafte Aktion!");
        } else {
            actions[actionIndex].actionPerformed(null);
        }
    }

}
