import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * The type Character.
 */
public class Creature implements Serializable {

    /**
     * The constant ATTACK_NORMAL.
     */
    public static final int ATTACK_NORMAL = 0;
    /**
     * The constant ATTACK_SPECIAL.
     */
    public static final int ATTACK_SPECIAL = 1;
    /**
     * The Max hp.
     */
    private int maxHp;
    /**
     * The Hp.
     */
    private int hp;
    /**
     * The Atk.
     */
    private int atk;
    /**
     * The Hit chance.
     */
    private double hitChance;
    /**
     * Inventar.
     */
    private Inventar inventar = new Inventar();

    /**
     * Gold das die Instanz besitzt.
     */
    private int gold = 0;

    protected final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public static enum Property {
        HP;
    }

    /**
     * Instantiates a new Character.
     *
     * @param maxHp     the max hp
     * @param atk       the atk
     * @param hitChance the hit chance
     */
    public Creature(int maxHp, int atk, double hitChance) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.hitChance = hitChance;
    }

    public void addPropertyChangeListener(Property property, PropertyChangeListener changeListener) {
        this.changeSupport.addPropertyChangeListener(property.name(), changeListener);
    }

    /**
     * Gets hit chance.
     *
     * @return the hit chance
     */
    public double getHitChance() {
        return hitChance;
    }

    /**
     * Sets hit chance.
     *
     * @param hitChance the hit chance
     */
    public void setHitChance(double hitChance) {
        if (hitChance >= 0 && hitChance <= 1) {
            this.hitChance = hitChance;
        }
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        int oldValue = this.hp;
        hp = Math.min(this.maxHp, Math.max(0, hp));
        this.hp = hp;
        this.changeSupport.firePropertyChange(Property.HP.name(), oldValue, hp);
    }

    /**
     * Gets max hp.
     *
     * @return the max hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Gets atk.
     *
     * @return the atk
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Sets atk.
     *
     * @param atk the atk
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     * @return the int
     */
    public int takeDamage(int damage) {
        return takeDamage(damage, ATTACK_NORMAL);
    }

    /**
     * Take damage.
     *
     * @param damage     the damage
     * @param attackType the attack type
     * @return the damage
     */
    public int takeDamage(int damage, int attackType) {
        setHp(getHp() - damage);
        return damage;
    }

    /**
     * Is defeated.
     *
     * @return true, wenn man besiegt ist
     */
    public boolean isDefeated() {
        return getHp() == 0;
    }

    /**
     * Attack int.
     *
     * @param c the enemy
     * @return -1, für Verfehlt, sonst den angerichteten Schaden
     */
    public int attack(Creature c) {
        if (Math.random() <= hitChance) {
            int damage = (int) (atk * (Math.random() + 1.0));
            return c.takeDamage(damage);
        } else {
            return -1;
        }
    }

    //Creature erweitert um Inventar und Gold.

    /**
     * Gettermethode für Inventar
     *
     * @return Inventar.
     */
    public Inventar getInventar() {
        return inventar;
    }

    /**
     * Gettermethode für Gold
     *
     * @return Gold.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Settermethode für Gold
     *
     * @param neuerGoldwert neuer Goldwert.
     */
    public void setGold(int neuerGoldwert) {
        gold = neuerGoldwert;
    }

}
