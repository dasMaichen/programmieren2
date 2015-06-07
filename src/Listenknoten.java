/**
 * Created by mai on 16.05.15.
 */
public class Listenknoten<T> {


    private final T element;
    private Listenknoten<T> nachfolger;


    /**
     *
     * Konstruktor erzeugt neue Instanzen von Inventarknoten.
     *
     */
    public Listenknoten(T element){
    this.element = element;
    }


    public void setNachfolger(Listenknoten<T> nachfolgerKnoten){

        this.nachfolger = nachfolgerKnoten;
    }


    public Listenknoten<T> getNachfolger() {
        return this.nachfolger;
    }

    public T getElement() {
        return this.element;
    }
}
