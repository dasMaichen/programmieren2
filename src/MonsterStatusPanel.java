import javax.swing.*;
import java.awt.*;

/**
 * Created by mai on 27.06.15.
 */
public class MonsterStatusPanel extends JPanel {

    private JLabel name = new JLabel("ich bin ein Monster");

    private Punkteleiste lpAnzeige = Punkteleiste.create(new Monster(), Creature.Property.HP);


    public MonsterStatusPanel(){

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        add(name,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        JLabel lebenspunkte = new JLabel("LP: ");
        add(lebenspunkte,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(lpAnzeige,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridx = 2;
        JLabel maxLp = new JLabel("70"+"/"+"100");


        add(maxLp,gridBagConstraints);


        lpAnzeige.setValue(70);
    }

}
