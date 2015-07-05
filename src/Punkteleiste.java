import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Punkteleiste extends JProgressBar implements PropertyChangeListener {

    private final Color leerFarbe = Color.RED;
    private final Color vollFarbe;

    public static Punkteleiste create(Creature creature, Creature.Property property) {
        if (property != Creature.Property.HP) {
            throw new IllegalArgumentException();
        }

        Punkteleiste punkteleiste = new Punkteleiste(creature.getHp(), creature.getMaxHp(), Color.GREEN);
        creature.addPropertyChangeListener(property, punkteleiste);
        return punkteleiste;
    }

    public static Punkteleiste create(Player player, Player.Property property) {
        if (property != Player.Property.AP) {
            throw new IllegalArgumentException();
        }

        Punkteleiste punkteleiste = new Punkteleiste(player.getAp(), player.getMaxAp(), Color.BLUE);
        player.addPropertyChangeListener(property, punkteleiste);
        return punkteleiste;
    }

    public Punkteleiste(int wert, int maxWert, Color vollFarbe) {
        super(0, maxWert);
        this.vollFarbe = vollFarbe;
        setValue(wert);
    }

    private int begrenzeAufFarbbereich(double value) {
        return Math.min(255, Math.max(0, (int) value));
    }

    @Override
    public void setValue(int wert) {
        super.setValue(wert);

        double faktor = 2.0 * getValue() / getMaximum();

        int rot = begrenzeAufFarbbereich(faktor * vollFarbe.getRed() + (2.0 - faktor) * leerFarbe.getRed());
        int gruen = begrenzeAufFarbbereich(faktor * vollFarbe.getGreen() + (2.0 - faktor) * leerFarbe.getGreen());
        int blau = begrenzeAufFarbbereich(faktor * vollFarbe.getBlue() + (2.0 - faktor) * leerFarbe.getBlue());

        setForeground(new Color(rot, gruen, blau));
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (!isValidProperty(propertyChangeEvent.getPropertyName())) {
            throw new IllegalArgumentException();
        }

        setValue((Integer) propertyChangeEvent.getNewValue());
    }

    private boolean isValidProperty(String propertyName) {
        return propertyName.equals(Creature.Property.HP.name()) || propertyName.equals(Player.Property.AP.name());
    }
}
