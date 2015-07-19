import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The type Monster.
 */
public class Monster extends Creature {

    private final Timer angriffTimer = new Timer(10000, new MonsterAction());

    /**
     * The Name.
     */
    private String name;


    /**
     * Instantiates a new Monster.
     */
    public Monster() {
        this("Gegner", 40, 8, 0.9);
    }

    /**
     * Instantiates a new Monster.
     *
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public Monster(int hp, int atk, double hitChance) {
        this("Gegner", hp, atk, hitChance);
    }

    /**
     * Instantiates a new Monster.
     *
     * @param name      the name
     * @param hp        the hp
     * @param atk       the atk
     * @param hitChance the hit chance
     *                  <p/>
     *                  Monster bekommt jetzt random Item und random Gold.
     */
    public Monster(String name, int hp, int atk, double hitChance) {
        super(hp, atk, hitChance);
        this.name = name;

        Random generator = new Random();
        this.setGold(generator.nextInt(101));

        int anzahlZufaelligerItems = generator.nextInt(5);
        for (int i = 0; i < anzahlZufaelligerItems; i++) {
            int j = generator.nextInt(Items.VERFUEGBARE_ITEMS.size() - 1);
            getInventar().add(Items.VERFUEGBARE_ITEMS.get(j));
        }


    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format("%s -- HP %d -- ATK %d%n", getName(), getHp(), getAtk());
    }

    public Timer getAngriffTimer() {
        return angriffTimer;
    }


    public class MonsterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int monsterDamage = attack(Player.getInstance());
            angriffTimer.setDelay((int) (Math.random() * 1000));
            System.out.println("Nächster Angriff in " + angriffTimer.getDelay() + "ms" );
        }
    }


}
