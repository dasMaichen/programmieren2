import java.io.Serializable;

/**
 * Created by Mai Kuginuki on 14.06.2015.
 * @param <T> Typ in Baumknoten.
 */
public class Baumknoten<T extends Comparable<T>> extends Listenknoten<T>
        implements Comparable<Baumknoten<T>>, Serializable {
    /**
     * linkes Kind ist vom Wert kleineres Kind
     */
    private Baumknoten<T> linkesKind;

    /**
     * rechtes Kind ist das größere Kind.
     */
    private Baumknoten<T> rechtesKind;

    /**
     * elternknoten ist ein Elternknoten
     */
    private Baumknoten<T> elternKnoten;

    /**
     * hoehe ist die Hoehe des Baumes.
     */
    private int hoehe;

    /**
     * Konstruktor erzeugt neuen Baumknoten
     * @param element ein Element von Type
     */
    public Baumknoten(T element) {
        super(element);
    }

    /**
     * parameterloser Konstruktor
     */
    protected Baumknoten() {
        super();
    }

    /**
     * getter-Methode fuer linkes Kind.
     *
     * @return linkes Kind.
     */
    public Baumknoten<T> getLinkesKind() {
        return this.linkesKind;
    }

    /**
     * setzt linkes Kind.
     *
     * @param kind ist der Knoten mit kleineren Element.
     */
    public void setLinkesKind(Baumknoten<T> kind) {
        this.linkesKind = kind;
    }

    /**
     * Getter-Methode fuer das rechte Kind.
     *
     * @return rechtes Kind.
     */
    public Baumknoten<T> getRechtesKind() {
        return this.rechtesKind;
    }

    /**
     * setzt rechtes Kind.
     *
     * @param kind ist der Knoten mit großwertigerem Element.
     */
    public void setRechtesKind(Baumknoten<T> kind) {
        this.rechtesKind = kind;
    }

    /**
     * Getter-Methode um Elternknoten zu bekommen.
     *
     * @return elternKnoten.
     */
    public Baumknoten<T> getElternKnoten() {
        return elternKnoten;
    }

    /**
     * Setter-Methode
     *
     * @param elternKnoten ist Elternknoten.
     */
    public void setElternKnoten(Baumknoten<T> elternKnoten) {
        this.elternKnoten = elternKnoten;
    }

    /**
     * vergleicht das Element der Baumknoten miteinander.
     *
     * @param baumknoten der Vergleichsknoten.
     * @return das Ergebniswert.
     */
    @Override
    public int compareTo(Baumknoten<T> baumknoten) {
        int ergebnis = this.getElement().compareTo(baumknoten.getElement());
        return ergebnis;
    }

    /**
     * Getter
     * @return Höhe
     */
    public int getHoehe() {
        return hoehe;
    }

    /**
     * Setter
     * @param hoehe setzt Höhe
     */
    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }
}
