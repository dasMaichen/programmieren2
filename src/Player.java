import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * The type Player.
 */
public final class Player extends Creature implements Serializable {


    /**
     * The constant HARD_HIT_COST.
     */
    public static final int HARD_HIT_COST = 20;
    /**
     * The constant HARD_HIT_SELF_DAMAGE_PERCENT.
     */
    public static final int HARD_HIT_SELF_DAMAGE_PERCENT = 20;
    /**
     * The constant FIREBALL_COST.
     */
    public static final int FIREBALL_COST = 20;
    /**
     * The constant REROLL_COST.
     */
    public static final int REROLL_COST = 35;

    /**
     * eine neue Player Instanz
     */
    private static Player instance = new Player();

    /**
     * Questlog für den Spieler
     */
    private Questlog questlog = new Questlog();
    /**
     * The Max ap.
     */
    private int maxAp;
    /**
     * The Ap regen.
     */
    private int apRegen;
    /**
     * The Ap.
     */
    private Integer ap;
    /**
     * The Healing power.
     */
    private int healingPower;
    /**
     * The Remaining item uses.
     */
    private int remainingItemUses;

    public int getAp() {
        return ap;
    }

    public enum Property {
        AP;
    }

    /**
     * Instantiates a new Player.
     */
    private Player() {
        this(130, 20, 70, 3, 0.8);
    }


    /**
     * Instantiates a new Player.
     *
     * @param maxHp             the max hp
     * @param atk               the atk
     * @param healingPower      the healing power
     * @param remainingItemUses the remaining item uses
     * @param hitChance         the hit chance
     */
    private Player(int maxHp, int atk, int healingPower, int remainingItemUses, double hitChance) {
        this(maxHp, atk, healingPower, remainingItemUses, hitChance, 70, 10);
    }


    /**
     * Instantiates a new Player.
     *
     * @param maxHp             the max hp
     * @param atk               the atk
     * @param healingPower      the healing power
     * @param remainingItemUses the remaining item uses
     * @param hitChance         the hit chance
     * @param maxAp             the max ap
     * @param apRegen           the ap regen
     */
    private Player(int maxHp, int atk, int healingPower, int remainingItemUses, double hitChance, int maxAp,
                   int apRegen) {
        super(maxHp, atk, hitChance);
        this.healingPower = healingPower;
        this.remainingItemUses = remainingItemUses;
        this.maxAp = maxAp;
        this.ap = maxAp;
        this.apRegen = apRegen;
    }

    /**
     * Gettermethode für instance
     *
     * @return die Instanz
     */
    public static Player getInstance() {
        return instance;
    }

    /**
     * Settermethode für die Instanz
     *
     * @param player setzt Instanz
     */
    public static void setInstance(Player player) {
        instance = player;
    }

    Player newInstance() {
        return new Player(this.getMaxHp(), this.getAtk(), this.healingPower, this.remainingItemUses, this
                .getHitChance(), this.maxAp, this.apRegen);
    }

    public void addPropertyChangeListener(Property property, PropertyChangeListener changeListener) {
        this.changeSupport.addPropertyChangeListener(property.name(), changeListener);
    }

    public int getMaxAp() {
        return maxAp;
    }

    /**
     * Gets remaining item uses.
     *
     * @return the remaining item uses
     */
    public int getRemainingItemUses() {
        return remainingItemUses;
    }

    /**
     * Heal boolean.
     *
     * @return true, wenn die Heilung erfolgreich war
     */
    public boolean heal() {
        if (remainingItemUses >= 1) {
            setHp(getHp() + healingPower);
            remainingItemUses--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Regenerate ap.
     *
     */
    public void regenerateAp() {
        synchronized (ap){
            int oldAp = this.ap;
            this.ap = Math.min(this.ap + 1, this.maxAp);
            this.changeSupport.firePropertyChange(Property.AP.name(), oldAp, (int)this.ap);
        }
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return String.format("Spieler -- HP %d -- ATK %d -- AP %d%n", getHp(), getAtk(), ap);
    }

    /**
     * Use ap.
     *
     * @param cost the cost
     * @return true, wenn die AP erfolgreich verbraucht wurden
     */
    boolean useAp(int cost) {
        synchronized (ap) {
            if (cost > this.ap) {
                return false;
            } else {
                int oldAp = this.ap;
                this.ap -= cost;
                this.changeSupport.firePropertyChange(Property.AP.name(), oldAp, (int) this.ap);
                return true;
            }
        }
    }

    /**
     * Hard hit.
     *
     * @param m the enemy
     * @return the damage
     */
    public int hardHit(Creature m) {
        if (!useAp(HARD_HIT_COST)) {
            return -1;
        } else {
            // 2-facher bis 4-facher Schaden
            int damage = (int) (getAtk() * (Math.random() * 2.0 + 2.0));
            damage = m.takeDamage(damage, ATTACK_SPECIAL);
            this.takeDamage((int) (HARD_HIT_SELF_DAMAGE_PERCENT / 100.0 * damage), ATTACK_SPECIAL);
            return damage;
        }
    }

    /**
     * Fireball int.
     *
     * @param m the enemy
     * @return the damage
     */
    public int fireball(Creature m) {
        if (!useAp(FIREBALL_COST)) {
            return -1;
        } else {
            // 1.5-facher Schaden
            int damage = (int) (getAtk() * 1.5);
            damage = m.takeDamage(damage, ATTACK_SPECIAL);
            return damage;
        }
    }

    /**
     * Reroll boolean.
     *
     * @return true, wenn erfolgreich
     */
    public boolean reroll() {
        if (!useAp(REROLL_COST)) {
            return false;
        } else {
            // ATK * Zufallsfaktor zwischen 0 und 2
            setAtk((int) (Math.random() * 2 * this.getAtk()));
            return true;
        }
    }

    /**
     * Gettermethode für den Spieler Questlog
     *
     * @return Questlog
     */
    public Questlog getQuestlog() {
        return questlog;
    }

}
