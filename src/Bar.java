import javax.swing.*;
import java.awt.*;

/**
 * Created by mai on 27.06.15.
 */
public class Bar extends JProgressBar implements LogicListener {

    private final Color leerFarbe = Color.RED;
    private final Color vollFarbe;


    //   public Bar() {
    //     super(0, 5);
    //   setValue(getMaximum());
//
    //  }


    public Bar(int minWert, int maxWert) {
        this(minWert, maxWert, Color.GREEN);
    }

    public Bar(int minWert, int maxWert, Color vollFarbe) {
        super(minWert, maxWert);
        this.vollFarbe = vollFarbe;
    }

    @Override
    public void onCounterHasChanged(int lpWert, int maxLpWert) {
        setValue(getMaximum() - lpWert);
        setMaximum(maxLpWert);
    }

    @Override
    public void apVeraendern(int apWert, int maxApWert) {
        setValue(maxApWert-apWert);
        setMaximum(maxApWert);
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
}
