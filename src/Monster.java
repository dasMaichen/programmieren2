import java.util.Random;

/**
 * The type Monster.
 */
public class Monster extends Creature {


    /**
     * The Name.
     */
    private String name;


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

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
     *
     * Monster bekommt jetzt random Item und random Gold.
     *
     */
    public Monster(String name, int hp, int atk, double hitChance) {
        super(hp, atk, hitChance);
        this.name = name;

        Random generator = new Random();
        this.setGold(generator.nextInt(101));

        int anzahlZufaelligerItems = generator.nextInt(5);
        for (int i = 0; i < anzahlZufaelligerItems; i++) {
            Item item = new Item();
            inventar.add(item);
        }


    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format("%s -- HP %d -- ATK %d%n", getName(), getHp(), getAtk());
    }

}
