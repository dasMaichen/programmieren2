/**
 * Created by Mai Kuginuki on 14.06.2015.
 */
public class Baumknoten<T extends Comparable<T>> implements Comparable<Baumknoten<T>>{

    private final T element;
    private Baumknoten<T> linkesKind;
    private Baumknoten<T> rechtesKind;

    /**
     *
     * Konstruktor erzeugt neue Baumknoten.
     *
     */
    public Baumknoten(T element) {
        if(element == null){
            throw new NullPointerException();
        }
        this.element = element;
    }

    /**
     * setzt linkes Kind.
     * @param kind ist der Knoten mit kleineren Element.
     */
    public void setLinkesKind(Baumknoten<T> kind){
        this.linkesKind = kind;
    }

    /**
     * setzt rechtes Kind.
     * @param kind ist der Knoten mit großwertigerem Element.
     */
    public void setRechtesKind(Baumknoten<T> kind){
        this.rechtesKind = kind;
    }

    /**
     * getter-Methode fuer linkes Kind.
     * @return linkes Kind.
     */
    public Baumknoten<T> getLinkesKind() {
        return this.linkesKind;
    }

    /**
     * Getter-Methode fuer das rechte Kind.
     * @return rechtes Kind.
     */
    public Baumknoten<T> getRechtesKind(){
        return this.rechtesKind;
    }

    /**
     * Getter-Methode fuer das Element des Knotens.
     * @return das Element (Item oder Quest).
     */
    public T getElement() {
        return this.element;
    }

    /**
     * vergleicht das Element der Baumknoten miteinander.
     * @param baumknoten der Vergleichsknoten.
     * @return das Ergebniswert.
     */
    @Override
    public int compareTo(Baumknoten<T> baumknoten) {
        int ergebnis = this.getElement().compareTo(baumknoten.getElement());
        return ergebnis;
    }


    @Override
    public String toString() {
        return element.toString();
    }
}
