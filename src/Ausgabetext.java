import javax.swing.*;

/**
 * Created by mai on 30.06.15.
 */
public class Ausgabetext extends JLabel implements LogicListener {
    @Override
    public void onCounterHasChanged(int lpWert, int maxLpWert) {
        setText(lpWert+"/"+maxLpWert);
    }

    @Override
    public void apVeraendern(int apWert, int maxApWert) {
        setText(apWert+"/"+maxApWert);
    }
}
