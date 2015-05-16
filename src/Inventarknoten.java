/**
 * Created by mai on 16.05.15.
 */
public class Inventarknoten {


    private final Item item;
    private Inventarknoten nachfolger;


    /**
     *
     * Konstruktor erzeugt neue Instanzen von Inventarknoten.
     *
     */
    public Inventarknoten(Item item){
    this.item = item;
    }


    public void setNachfolger(Inventarknoten nachfolgerKnoten){

        this.nachfolger = nachfolgerKnoten;
    }


    public Inventarknoten getNachfolger() {
        return this.nachfolger;
    }

    public Item getItem() {
        return this.item;
    }
}
