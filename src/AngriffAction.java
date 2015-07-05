import javax.swing.*;

public class AngriffAction extends KampfAction {
    public AngriffAction(JLabel statusLabel, Monster monster) {
        super(statusLabel, monster, "Angriff");
    }

    @Override
    protected void fuehreSpielerAktionAus() {
        int playerDamage = Player.getInstance().attack(this.monster);
        if (playerDamage == -1) {
            setzeStatus("Spieler verfehlt!");
        } else {
            setzeStatus(String.format("Spieler trifft und macht %d Schaden!", playerDamage));
        }

    }
}
