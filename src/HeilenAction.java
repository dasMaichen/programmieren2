import javax.swing.*;

public class HeilenAction extends KampfAction {

    public HeilenAction(JLabel statusLabel, Monster monster) {
        super(statusLabel, monster, "Trank trinken");
    }

    @Override
    protected void fuehreSpielerAktionAus() {
        if (Player.getInstance().heal()) {
            setzeStatus("Spieler heilt sich!");
        } else {
            setzeStatus("Nicht genügend Heiltränke!");
        }
    }
}
