import javax.swing.*;
import java.awt.*;

public class Layout extends JFrame {

    public Layout() {
        setSize(500, 500);

        setLocation(600,300);

        JLabel label;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;



        constraints.gridy = 0;
        constraints.gridx = 0;

        add(new MonsterStatusPanel(), constraints);

        label = new JLabel("Bild eines Monsters");
        constraints.gridx = 1;
        add(label, constraints);



        constraints.gridy = 1;

        label = new JLabel("Ein Bild von mir! :) ");
        constraints.gridx = 0;
        add(label, constraints);

        constraints.gridx = 1;
        add(new PlayerStatusPanel(), constraints);



        constraints.gridy = 2;
        constraints.weighty = 0.5;

        label = new JLabel("     Was möchtest du tun?");
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        constraints.gridx = 0;
        add(label, constraints);


        label = new JLabel("");
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        constraints.gridx = 1;
        add(label, constraints);




        JButton button;
        constraints.weighty = 0.25;

        constraints.gridy = 3;

        button = new JButton("Angriff");
        constraints.gridx = 0;
        add(button, constraints);

        button = new JButton("Zauber");
        constraints.gridx = 1;
        add(button, constraints);

        constraints.gridy = 4;

        button = new JButton("Items");
        constraints.gridx = 0;
        add(button, constraints);

        button = new JButton("Flucht");
        constraints.gridx = 1;
        add(button, constraints);
    }

    public static void main(String[] args) {
        new Layout().setVisible(true);
    }
}
