/**
 * Created by mai on 03.06.15.
 */
public class Questlog implements ListAufg<Quest> {


    /**
     * Ueberprueft ob die Liste leer ist
     *
     * @return true, Liste ist leer
     */
    @Override
    public boolean isEmpty(){
        throw new UnsupportedOperationException("geht nicht!");
    }

    /**
     * Gibt die Laenge der Liste zurück
     *
     * @return die Laenge
     */
    @Override
    public int length() {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Prueft ob ein Item in der Liste ist
     *
     * @param x das Item
     * @return true, x ist in der Liste enthalten
     */
    public boolean isInList(Item x) {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     * @throws IllegalStateException wenn die Liste leer ist
     */
    public Item firstItem() throws IllegalStateException {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Gibt das i-te Item der Liste zurueck
     *
     * @param i der Index
     * @return das i-te Item
     * @throws IndexOutOfBoundsException wenn i < 0 oder  i >= length()
     */
    public Item getItem(int i) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Fuegt ein Element sortiert in die Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public ListAufg insert(Item x) {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Fuegt ein Element an das Ende der Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public ListAufg append(Item x) {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Loescht das erste vorkommen des Items x
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    public ListAufg delete(Item x) {
        throw new UnsupportedOperationException("geht nicht!");
    };

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    public ListAufg delete() {
        throw new UnsupportedOperationException("geht nicht!");
    };
}

