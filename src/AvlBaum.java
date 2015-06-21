import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Mai Kuginuki on 14.06.2015.
 *
 * @param <T> Typ der Baumelemente
 */
public class AvlBaum<T extends Comparable<T>> implements List<T>, Serializable {

    /**
     * eine neue verkettete Liste.
     */
    private final DoppeltVerketteteListe<T> sortierteListe = new DoppeltVerketteteListe<>();
    /**
     * Wurzelknoten ist der erste Knoten.
     */
    private Baumknoten<T> wurzelknoten;
    /**
     * Groesse des Baumes.
     */
    private int groesse;

    /**
     * Getter-Methode fuer den ersten Knoten des Baums.
     *
     * @return den Wurzelknoten.
     */
    Baumknoten<T> getWurzelknoten() {
        return wurzelknoten;
    }

    /**
     * Liefert Groesse des Baums.
     *
     * @return Groesse des Baums.
     */
    @Override
    public int size() {
        return groesse;
    }

    /**
     * Prueft, ob Baum leer ist.
     *
     * @return wahr, wenn leer.
     */
    @Override
    public boolean isEmpty() {
        if (wurzelknoten == null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Add-Methode
     *
     * @param elementknoten Knoten, was eingefuegt werden soll
     */
    void add(Baumknoten<T> elementknoten) {

        if (this.isEmpty()) {
            wurzelknoten = elementknoten;
            elementknoten.setElternKnoten(null);
            sortierteListe.add(elementknoten);

        } else {
            Baumknoten<T> elternknoten = sucheElternknoten(elementknoten);
            elementknoten.setElternKnoten(elternknoten);
            int ergebnis = elementknoten.compareTo(elternknoten);

            if (ergebnis >= 0) {
                elternknoten.setRechtesKind(elementknoten);

                sortierteListe.add(elementknoten, elternknoten);

            } else {
                elternknoten.setLinkesKind(elementknoten);

                sortierteListe.add(elementknoten, elternknoten.getVorgaenger());
            }
            hoeheAktualisieren(elementknoten.getElternKnoten());

            for (Baumknoten<T> knoten = elementknoten.getElternKnoten();
                 knoten != null; knoten = knoten.getElternKnoten()) {
                rebalancieren(knoten);
            }
        }
        groesse += 1;
    }


    /**
     * Add-Methode
     *
     * @param element das Element, was in den Baum soll (Item, Quest)
     * @return wahr, wenn eingefuegt.
     */
    @Override
    public boolean add(T element) {
        Baumknoten<T> elementknoten = new Baumknoten<T>(element);
        add(elementknoten);
        return true;
    }

    /**
     * Sucht den Elternknoten
     *
     * @param baumknoten Knoten, dessen Eltern gesucht werden soll.
     * @return gesuchten Elternknoten.
     */
    public Baumknoten<T> sucheElternknoten(Baumknoten<T> baumknoten) {

        Baumknoten<T> aktuellerElternteil;
        Baumknoten<T> potentiellerElternteil = wurzelknoten;


        while (potentiellerElternteil != null) {

            int ergebnis = baumknoten.compareTo(potentiellerElternteil);

            if (ergebnis >= 0) {
                aktuellerElternteil = potentiellerElternteil.getRechtesKind();
            } else {
                aktuellerElternteil = potentiellerElternteil.getLinkesKind();
            }

            if (aktuellerElternteil == null) {
                return potentiellerElternteil;
            } else {
                potentiellerElternteil = aktuellerElternteil;
            }
        }
        return null;
    }

    /**
     * gibt letzten Listenknoten.
     *
     * @return Minimum.
     */
    Listenknoten<T> getMinimum() {
        return sortierteListe.getErsterKnoten();
    }


    /**
     * sucht den zum Element gehörigen Knoten
     *
     * @param element Element zum Baumknoten
     * @return Baumknoten.
     */
    Baumknoten<T> suchen(T element) {

        if (isEmpty()) {
            return null;
        }

        Baumknoten<T> vergleichsKnoten = wurzelknoten;

        while (vergleichsKnoten != null) {
            int ergebnis = vergleichsKnoten.getElement().compareTo(element);

            if (ergebnis > 0) {
                vergleichsKnoten = vergleichsKnoten.getLinkesKind();
            } else if (ergebnis < 0) {
                vergleichsKnoten = vergleichsKnoten.getRechtesKind();
            } else {
                return vergleichsKnoten;
            }
        }
        return null;
    }

    /**
     * sucht Teilbaumminimum
     *
     * @param teilbaumwurzel Wurzel des Teilbaumes
     * @return Minimum des Teilbaums
     */
    Baumknoten<T> sucheLokalesMinimum(Baumknoten<T> teilbaumwurzel) {
        if (teilbaumwurzel == null) {
            throw new NullPointerException();
        }

        Baumknoten<T> minimum = teilbaumwurzel;

        while (minimum.getLinkesKind() != null) {
            minimum = minimum.getLinkesKind();
        }
        return minimum;
    }


    @Override
    public boolean remove(Object object) {

        Baumknoten<T> wegwerfKnoten = suchen((T) object);

        if (wegwerfKnoten == null) {
            return false;
        }

        Baumknoten<T> rechtesKind = wegwerfKnoten.getRechtesKind();
        Baumknoten<T> linkesKind = wegwerfKnoten.getLinkesKind();
        Baumknoten<T> elternKnoten = wegwerfKnoten.getElternKnoten();
        Baumknoten<T> kind;

        if (rechtesKind == null && linkesKind == null) {
            kind = null;
        } else if (rechtesKind == null) {
            kind = linkesKind;
        } else if (linkesKind == null) {
            kind = rechtesKind;
        } else {
            Baumknoten<T> lokalesMinimum = sucheLokalesMinimum(rechtesKind);

            linkesKind.setElternKnoten(lokalesMinimum);
            lokalesMinimum.setLinkesKind(linkesKind);

            kind = rechtesKind;
        }

        if (wegwerfKnoten == wurzelknoten && kind == null) {
            wurzelknoten = null;
        } else if (wegwerfKnoten == wurzelknoten) {
            wurzelknoten = kind;
            kind.setElternKnoten(null);
        } else {
            if (elternKnoten.compareTo(wegwerfKnoten) > 0) {
                elternKnoten.setLinkesKind(kind);
            } else {
                elternKnoten.setRechtesKind(kind);
            }
            if (kind != null) {
                kind.setElternKnoten(elternKnoten);
            }
        }

        hoeheAktualisieren(wegwerfKnoten.getElternKnoten());

        for (Baumknoten<T> knoten = wegwerfKnoten.getElternKnoten();
             knoten != null; knoten = knoten.getElternKnoten()) {
            rebalancieren(knoten);
        }

        groesse = groesse - 1;
        sortierteListe.remove(wegwerfKnoten);

        return true;
    }

    @Override
    public T remove(int index) {
        T element = sortierteListe.get(index);
        remove(element);
        return element;
    }


    @Override
    public T get(int index) {
        T element = sortierteListe.get(index);
        return element;
    }


    /**
     * aktualisiert Höhe des Baumes
     *
     * @param elternknoten ein Baumknoten
     */
    void hoeheAktualisieren(Baumknoten<T> elternknoten) {

        for (Baumknoten<T> knoten = elternknoten; knoten != null; knoten = knoten.getElternKnoten()) {

            int linkeHoehe;

            if (knoten.getLinkesKind() == null) {
                linkeHoehe = -1;
            } else {
                linkeHoehe = knoten.getLinkesKind().getHoehe();
            }

            int rechteHoehe;

            if (knoten.getRechtesKind() == null) {
                rechteHoehe = -1;
            } else {
                rechteHoehe = knoten.getRechtesKind().getHoehe();
            }

            knoten.setHoehe(Math.max(linkeHoehe, rechteHoehe) + 1);

        }
    }

    /**
     * Methode rebalanciert den Baum
     * @param baumknoten Baumknoten für den geprüft werden soll
     */
    void rebalancieren(Baumknoten<T> baumknoten) {

        int hoeheLinkesKind;
        int hoeheReschtesKind;

        if (baumknoten.getLinkesKind() == null) {
            hoeheLinkesKind = -1;
        } else {
            hoeheLinkesKind = baumknoten.getLinkesKind().getHoehe();
        }

        if (baumknoten.getRechtesKind() == null) {
            hoeheReschtesKind = -1;
        } else {
            hoeheReschtesKind = baumknoten.getRechtesKind().getHoehe();
        }

        if (hoeheLinkesKind - hoeheReschtesKind > 1) {
            if (baumknoten.getLinkesKind().getRechtesKind() != null) {
                nachLinksRotieren(baumknoten.getLinkesKind());
            }
            nachRechtsRotieren(baumknoten);
        } else if (hoeheLinkesKind - hoeheReschtesKind < -1) {
            if (baumknoten.getRechtesKind().getLinkesKind() != null) {
                nachRechtsRotieren(baumknoten.getRechtesKind());
            }
            nachLinksRotieren(baumknoten);
        }
    }

    /**
     * lässt nach links rotieren
     *
     * @param baumknoten kritischer Knoten
     */
    private void nachLinksRotieren(Baumknoten<T> baumknoten) {
        Baumknoten<T> kind = baumknoten.getRechtesKind();
        Baumknoten<T> eltern = baumknoten.getElternKnoten();

        if(eltern == null) {
            wurzelknoten = kind;
        }else {
            if (baumknoten == eltern.getLinkesKind()) {
                eltern.setLinkesKind(kind);
            } else if (baumknoten == eltern.getRechtesKind()) {
                eltern.setRechtesKind(kind);
            }
        }

        kind.setElternKnoten(eltern);

        baumknoten.setRechtesKind(kind.getLinkesKind());

        if (kind.getLinkesKind() != null) {
            kind.getLinkesKind().setElternKnoten(baumknoten);
        }

        kind.setLinkesKind(baumknoten);
        baumknoten.setElternKnoten(kind);

        hoeheAktualisieren(baumknoten);
    }

    /**
     * lässt nach rechts rotieren
     *
     * @param baumknoten kritischer Knoten
     */

    private void nachRechtsRotieren(Baumknoten<T> baumknoten) {
        Baumknoten<T> kind = baumknoten.getLinkesKind();
        Baumknoten<T> eltern = baumknoten.getElternKnoten();

        if(eltern == null) {
            wurzelknoten = kind;
        }else {
            if (baumknoten == eltern.getLinkesKind()) {
                eltern.setLinkesKind(kind);
            } else if (baumknoten == eltern.getRechtesKind()) {
                eltern.setRechtesKind(kind);
            }
        }

        kind.setElternKnoten(eltern);

        baumknoten.setLinkesKind(kind.getRechtesKind());

        if (kind.getRechtesKind() != null) {
            kind.getRechtesKind().setElternKnoten(baumknoten);
        }

        kind.setRechtesKind(baumknoten);
        baumknoten.setElternKnoten(kind);

        hoeheAktualisieren(baumknoten);
    }


    @Override
    public int indexOf(Object object) {
        return sortierteListe.indexOf(object);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("geht nicht!");
    }


    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("geht nicht!");
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("geht nicht!");
    }


    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("geht nicht!");
    }
}
