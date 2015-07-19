import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class KampfAction extends AbstractAction {

    private static final int STATUS_DELAY = 1000;

    private final JLabel statusLabel;
    protected final Monster monster;

    private Timer statusTimer = new Timer(STATUS_DELAY, new TimerCallback());
    private Queue<String> statusQueue = new PriorityQueue<>();

    public KampfAction(JLabel statusLabel, Monster monster, String aktionsName) {
        super(aktionsName);
        this.statusLabel = statusLabel;
        this.monster = monster;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        fuehreSpielerAktionAus();
        monster.new MonsterAction().actionPerformed(actionEvent);
        Player.getInstance().regenerateAp();
    }

    protected abstract void fuehreSpielerAktionAus();

    protected void setzeStatus(final String status) {
        if (!statusTimer.isRunning()) {
            statusTimer.start();
        }

        statusQueue.add(status);
    }



    private class TimerCallback implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String status = statusQueue.poll();
            if (status == null) {
                statusTimer.stop();
            } else {
                statusLabel.setText(status);
            }
        }
    }
}
