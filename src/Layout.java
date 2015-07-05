import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Layout extends JFrame implements PropertyChangeListener {

    public static final int DEFAULT_DELAY = 2000;
    private final Monster monster = new Monster();
    private final JLabel statusLabel;

    public Layout() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(600, 300);
        setSize(500, 500);

        this.monster.addPropertyChangeListener(Creature.Property.HP, this);

        JLabel label;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;


        constraints.gridy = 0;
        constraints.gridx = 0;

        add(new MonsterStatusPanel(monster), constraints);

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

        this.statusLabel = new JLabel("     Was möchtest du tun?");
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        add(this.statusLabel, constraints);
        constraints.gridwidth = 1;


        JButton button;
        constraints.weighty = 0.25;

        constraints.gridy = 3;

        button = new JButton(new AngriffAction(this.statusLabel, monster));
        constraints.gridx = 0;
        add(button, constraints);

        button = new JButton(new ZauberAction(this.statusLabel, monster));
        constraints.gridx = 1;
        add(button, constraints);

        constraints.gridy = 4;

        button = new JButton(new HeilenAction(this.statusLabel));
        constraints.gridx = 0;
        add(button, constraints);

        button = new JButton("Flucht");
        constraints.gridx = 1;
        add(button, constraints);
    }

    public static void main(String[] args) {
        new Layout().setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (Creature.Property.HP.name().equals(propertyChangeEvent.getPropertyName())) {
            Creature creature = (Creature) propertyChangeEvent.getSource();
            if (creature.isDefeated()) {
                deaktiviereAlleButtons();
                new Timer(DEFAULT_DELAY, new BattleEndListener(creature)).start();
            }
        }
    }

    private void deaktiviereAlleButtons() {
        for(Component kindKomponente : getContentPane().getComponents()) {
            if (kindKomponente instanceof JButton) {
                JButton button = (JButton) kindKomponente;
                button.setEnabled(false);
            }
        }
    }

    private class BattleEndListener implements ActionListener {

        private final Creature deadCreature;

        BattleEndListener(Creature deadCreature) {
            this.deadCreature = deadCreature;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (this.deadCreature == Player.getInstance()) {
                System.out.println("Game Over!");
                System.exit(0);
            } else {
                statusLabel.setText("Spieler gewinnt!");

                //Monsteritem werden dem Spieler übertragen.
                int anzahlMonsterItem = monster.getInventar().size();

                for (int i = 0; i < anzahlMonsterItem; i++) {
                    Item monsterItem = (Item) monster.getInventar().get(i);
                    Player.getInstance().getInventar().add(monsterItem);
                }

                //Mostergold werden dem Spieler übertragen.
                Player.getInstance().setGold(Player.getInstance().getGold() + monster.getGold());

                new Timer(DEFAULT_DELAY, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        dispose();
                    }
                }).start();
            }
        }
    }
}
