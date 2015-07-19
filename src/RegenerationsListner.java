import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mai on 19.07.15.
 */
public class RegenerationsListner implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        Player.getInstance().regenerateAp();

    }
}
