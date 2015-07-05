import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ausgabetext extends JLabel implements ChangeListener {

    public Ausgabetext(JProgressBar progressBar) {
        super(createTextFromProgress(progressBar));
        progressBar.addChangeListener(this);
    }

    private static String createTextFromProgress(JProgressBar progressBar) {
        return progressBar.getValue() + "/" + progressBar.getMaximum();
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        if (!(changeEvent.getSource() instanceof JProgressBar)) {
            throw new IllegalArgumentException();
        }

        setText(createTextFromProgress((JProgressBar) changeEvent.getSource()));
    }
}
