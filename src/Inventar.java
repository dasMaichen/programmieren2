import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mai on 14.05.15.
 */
public class Inventar implements List<Item>{

    /**
     * definiert den Listenanfang.
     */
    Inventarknoten ersterKnoten;

    /**
     * Anzahl der Listenelemente.
     */
    int groesse= 0;


    /**
     * Eine leere Liste wird produziert.
     */
    public Inventar(){

    }


    /**
     *
     * holt das letzte Listenelement und fügt das neue Element als Nachfolger hinzu.
     * return true --> das Hinzugügen ist erlaubt.
     */

    @Override
    public boolean add(Item item) {
        Inventarknoten neuerKnoten = new Inventarknoten(item);

        if (ersterKnoten == null) {
            ersterKnoten = neuerKnoten;
        }else {

            Inventarknoten letzterKnoten = getKnoten(groesse-1);

            letzterKnoten.setNachfolger(neuerKnoten);
        }

        groesse = groesse+1;

        return true;
    }


//
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

    public Inventarknoten getKnoten(int position){

        Inventarknoten gesuchterKnoten = ersterKnoten;


        for (int i = 0; i < position; i++) {

            gesuchterKnoten = gesuchterKnoten.getNachfolger();
        }
        return gesuchterKnoten;
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
     * gibt gesuchtes Item an der gesuchten Position aus.
     *
     */

    @Override
    public Item get(int position) {

        Inventarknoten gesuchterKnoten = getKnoten(position);
        Item gesuchtesItem = gesuchterKnoten.getItem();

        return gesuchtesItem;
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
     * @param zuLoeschendesItem das Item, welches ich loeschen moechte.
     * @return boolean, ob Item geloescht wurde.
     *
     * Diese Methode löscht Items aus dem Inventar.
     */


    public boolean remove(Item zuLoeschendesItem) {

        Inventarknoten zuLoeschenderKnoten = ersterKnoten;
        Inventarknoten vorgänger = null;

        for (int i = 0; i < groesse; i++) {

            if(zuLoeschenderKnoten.getItem().equals(zuLoeschendesItem)){
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



    @Override
    public Item set(int i, Item item) {
        throw new UnsupportedOperationException("geht nicht!");
    }





    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }






    @Override
    public void clear() {
        throw new UnsupportedOperationException("geht nicht!");
    }









    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public Iterator<Item> iterator() {
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





    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(Collection<? extends Item> collection) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean addAll(int i, Collection<? extends Item> collection) {
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
    public void add(int i, Item item) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public Item remove(int i) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public ListIterator<Item> listIterator() {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public ListIterator<Item> listIterator(int i) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public List<Item> subList(int i, int i1) {
        throw new UnsupportedOperationException("geht nicht!");
    }
}
