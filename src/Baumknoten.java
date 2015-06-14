/**
 * Created by Mai Kuginuki on 14.06.2015.
 */
public class Baumknoten<T extends Comparable<T>> extends Listenknoten<T> implements Comparable<Baumknoten<T>>{

    private Baumknoten<T> linkesKind;
    private Baumknoten<T> rechtesKind;


    /**
     *
     * Konstruktor erzeugt neue Baumknoten.
     *
     */
    public Baumknoten(T element) {
        super(element);
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
     * vergleicht das Element der Baumknoten miteinander.
     * @param baumknoten der Vergleichsknoten.
     * @return das Ergebniswert.
     */
    @Override
    public int compareTo(Baumknoten<T> baumknoten) {
        int ergebnis = this.getElement().compareTo(baumknoten.getElement());
        return ergebnis;
    }



}
