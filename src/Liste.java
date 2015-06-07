import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mai on 04.06.15.
 */
public class Liste<T> implements List<T> {


    /**
     * definiert den Listenanfang.
     */
    Listenknoten<T> ersterKnoten;

    /**
     * Anzahl der Listenelemente.
     */
    int groesse= 0;


    /**
     * Eine leere Liste wird produziert.
     */
    public Liste(){

    }


    /**
     *return Anzahl der Listenelemente.
     */
    @Override
    public int size() {
        return groesse;
    }

    /**
     *
     * Abfrage ob die Inventarliste leer ist.
     *
     */
    @Override
    public boolean isEmpty() {
        if (ersterKnoten==null){
            return  true;
        }else {
            return false;
        }
    }


    /**
     *
     * holt das letzte Listenelement und fügt das neue Element als Nachfolger hinzu.
     * return true --> das Hinzugügen ist erlaubt.
     */
    @Override
    public boolean add(T element) {
        Listenknoten<T> neuerKnoten = new Listenknoten<T>(element);

        if (ersterKnoten == null) {
            ersterKnoten = neuerKnoten;
        } else {

            Listenknoten<T> letzterKnoten = getKnoten(groesse - 1);

            letzterKnoten.setNachfolger(neuerKnoten);
        }

        groesse = groesse + 1;

        return true;
    }
//
//    @Override
//    public boolean add(Item item) {
//        Inventarknoten neuerKnoten = new Inventarknoten(item);
//
//        if (ersterKnoten == null) {
//            ersterKnoten = neuerKnoten;
//        }else {
//
//            Inventarknoten aktuellerKnoten = ersterKnoten;
//            Inventarknoten vorgaengerKnoten = null;
//
//
//            int ergebnis = item.compareTo(aktuellerKnoten.getItem());
//
//
//            if(ergebnis < 0) {
//                neuerKnoten.setNachfolger(ersterKnoten);
//                neuerKnoten = ersterKnoten;
//
//                groesse = groesse+1;
//
//                return true;
//            }
//
//
//
//            while (ergebnis >=0){
//
//                if(aktuellerKnoten.getNachfolger() == null){
//                    aktuellerKnoten.setNachfolger(neuerKnoten);
//
//                    groesse=groesse+1;
//                return true;
//                }
//
//                vorgaengerKnoten = aktuellerKnoten;
//                aktuellerKnoten = aktuellerKnoten.getNachfolger();
//
//                ergebnis = item.compareTo(aktuellerKnoten.getItem());
//            }
//
//            if (ergebnis < 0){
//                if (vorgaengerKnoten != null) {
//                    neuerKnoten.setNachfolger(aktuellerKnoten);
//                    vorgaengerKnoten.setNachfolger(neuerKnoten);
//                }
//
//            }
//        }
//        groesse = groesse+1;
//
//        return true;
//    }


    /**
     * gibt den Knoten an der gesuchten Position aus.
     */
    public Listenknoten<T> getKnoten(int position){

        Listenknoten<T> gesuchterKnoten = ersterKnoten;


        for (int i = 0; i < position; i++) {

            gesuchterKnoten = gesuchterKnoten.getNachfolger();
        }
        return gesuchterKnoten;
    }


    /**
     *
     * @param zuLoeschendesObekt das Objekt, welches ich loeschen moechte.
     * @return ob Objekt geloescht wurde.
     * Diese Methode löscht Objekte aus dem Inventar.
     */
    @Override
    public boolean remove(Object zuLoeschendesObekt) {

        Listenknoten<T> zuLoeschenderKnoten = ersterKnoten;
        Listenknoten<T> vorgänger = null;

        for (int i = 0; i < groesse; i++) {

            if(zuLoeschenderKnoten.getElement().equals(zuLoeschendesObekt)){
                if(vorgänger==null){
                    ersterKnoten = ersterKnoten.getNachfolger();
                }else {
                    vorgänger.setNachfolger(zuLoeschenderKnoten.getNachfolger());
                }

                groesse = groesse-1;
                return true;

            }else {
                vorgänger = zuLoeschenderKnoten;
                zuLoeschenderKnoten = zuLoeschenderKnoten.getNachfolger();
            }



//            if(!zuLoeschenderKnoten.getItem().equals(zuLoeschendesItem)){
//                vorgänger = zuLoeschenderKnoten;
//                zuLoeschenderKnoten = zuLoeschenderKnoten.getNachfolger();
//            }else {
//                if(vorgänger==null){
//                    ersterKnoten = ersterKnoten.getNachfolger();
//                }else {
//                    vorgänger.setNachfolger(zuLoeschenderKnoten.getNachfolger());
//                }
//            }
        }      return false;
    }

    /**
     *
     * gibt gesuchtes Objekt an der gesuchten Position aus.
     *
     */
    @Override
    public T get(int position) {
        Listenknoten<T> gesuchterKnoten = getKnoten(position);
        T gesuchtesObjekt = gesuchterKnoten.getElement();

        return gesuchtesObjekt;
    }



    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size(); i++) {
            boolean gleich = o.equals(this.get(i));
            if (gleich){
                return i;
            }
        }return -1;
    }


    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public T set(int i, Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public void add(int i, Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public T remove(int i) {
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
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
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
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("geht nicht!");
    }
}
