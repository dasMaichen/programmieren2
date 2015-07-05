import javax.swing.*;
import java.awt.*;

/**
 * Created by mai on 27.06.15.
 */
public class PlayerStatusPanel extends JPanel {

    private JLabel name = new JLabel("ich bin ein Player");

    private Bar lpAnzeige = new Bar(0,120);
    private Bar apAnzeige = new Bar(0,30,Color.BLUE);


    public PlayerStatusPanel(){


        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();


        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        add(name,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        JLabel ap = new JLabel("AP: ");
        add(ap, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(apAnzeige,gridBagConstraints);


        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 2;
        Ausgabetext maxAp = new Ausgabetext();
        add(maxAp,gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        JLabel lebenspunkte = new JLabel("LP: ");
        lpAnzeige.onCounterHasChanged(Player.getInstance().getHp(),Player.getInstance().getMaxHp());
        add(lebenspunkte, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(lpAnzeige,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridx = 2;
        Ausgabetext maxLp = new Ausgabetext();


        add(maxLp,gridBagConstraints);

    }

}
