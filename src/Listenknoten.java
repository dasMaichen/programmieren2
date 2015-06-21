import java.io.Serializable;

/**
 * Created by mai on 16.05.15.
 * @param <T> Elementtyp
 */
public class Listenknoten<T> implements Serializable {


    /**
     * Typ Element
     */
    private final T element;
    /**
     * Nachfolger der Liste
     */
    private Listenknoten<T> nachfolger;
    /**
     * Vorgänger der Liste
     */
    private Listenknoten<T> vorgaenger;


    /**
     * Konstruktor erzeugt neue Instanzen von Inventarknoten.
     * @param element ein Element der Liste
     */
    public Listenknoten(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        this.element = element;
    }

    /**
     * parameterloser Knoten für Listenknoten
     */
    protected Listenknoten() {
        this.element = null;
    }

    /**
     * Gettermethode für NAchfolger
     * @return nachfolgenden Knoten
     */
    public Listenknoten<T> getNachfolger() {
        return this.nachfolger;
    }

    /**
     * Settermethode
     * @param nachfolgerKnoten setzt den Nachfolgerknoten.
     */
    public void setNachfolger(Listenknoten<T> nachfolgerKnoten) {

        this.nachfolger = nachfolgerKnoten;
    }

    /**
     * Gettermethode für Vorgänger
     * @return Vorgängerknoten
     */
    public Listenknoten<T> getVorgaenger() {
        return vorgaenger;
    }

    /**
     * Settermethode für Vorgänger
     * @param vorgaenger setzt Vorgängerknoten
     */
    public void setVorgaenger(Listenknoten<T> vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    /**
     * Getter-Methode fuer das Element des Knotens.
     *
     * @return das Element (Item oder Quest).
     */
    public T getElement() {
        return this.element;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
