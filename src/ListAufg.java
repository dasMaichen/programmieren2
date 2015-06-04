/**
 * Created by mai on 03.06.15.
 */
public interface ListAufg<Obj> {


    /**
     * Ueberprueft ob die Liste leer ist
     *
     * @return true, Liste ist leer
     */
    boolean isEmpty();

    /**
     * Gibt die Laenge der Liste zurück
     *
     * @return die Laenge
     */
    int length();

    /**
     * Prueft ob ein Item in der Liste ist
     *
     * @param x das Item
     * @return true, x ist in der Liste enthalten
     */
    boolean isInList(Item x);

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     * @throws IllegalStateException wenn die Liste leer ist
     */
    Item firstItem() throws IllegalStateException;

    /**
     * Gibt das i-te Item der Liste zurueck
     *
     * @param i der Index
     * @return das i-te Item
     * @throws IndexOutOfBoundsException wenn i < 0 oder  i >= length()
     */
    Item getItem(int i) throws IndexOutOfBoundsException;

    /**
     * Fuegt ein Element sortiert in die Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    ListAufg insert(Item x);

    /**
     * Fuegt ein Element an das Ende der Liste ein
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    ListAufg append(Item x);

    /**
     * Loescht das erste vorkommen des Items x
     *
     * @param x das Item
     * @return die geanderte Liste
     */
    ListAufg delete(Item x);

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    ListAufg delete();
}


