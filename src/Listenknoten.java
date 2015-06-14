/**
 * Created by mai on 16.05.15.
 */
public class Listenknoten<T> {


    private final T element;
    private Listenknoten<T> nachfolger;
    private Listenknoten<T> vorgaenger;


    /**
     * Konstruktor erzeugt neue Instanzen von Inventarknoten.
     */
    public Listenknoten(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        this.element = element;
    }


    public void setNachfolger(Listenknoten<T> nachfolgerKnoten) {

        this.nachfolger = nachfolgerKnoten;
    }

    public Listenknoten<T> getNachfolger() {
        return this.nachfolger;
    }


    public Listenknoten<T> getVorgaenger() {
        return vorgaenger;
    }

    public void setVorgaenger(Listenknoten<T> vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    /**
     * Getter-Methode fuer das Element des Knotens.
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
