import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mai on 14.06.15.
 */
public class DoppeltVerketteteListe<T> implements List<T> {

    private Listenknoten<T> ersterKnoten;
    private Listenknoten<T> letzterKnoten;

    private int groesse;


    @Override
    public int size() {
        return groesse;
    }

    @Override
    public boolean isEmpty() {
        if(groesse == 0){
            return true;
        }
        return false;
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
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public boolean add(T element) {

        Listenknoten<T> knoten = new Listenknoten<>(element);

        return add(knoten);
    }


    boolean add(Listenknoten<T> knoten) {



        return add(knoten,letzterKnoten);
    }


    boolean add(Listenknoten<T> einzufuegenderKnoten, Listenknoten<T> vorgaengerKnoten){

        if(isEmpty()){
            ersterKnoten = einzufuegenderKnoten;
            letzterKnoten = einzufuegenderKnoten;

        }else if(vorgaengerKnoten == null){
            ersterKnoten.setVorgaenger(einzufuegenderKnoten);
            einzufuegenderKnoten.setNachfolger(ersterKnoten);
            ersterKnoten = einzufuegenderKnoten;

        }else {
            if(vorgaengerKnoten == letzterKnoten){
                letzterKnoten = einzufuegenderKnoten;
            }else {
                einzufuegenderKnoten.setNachfolger(vorgaengerKnoten.getNachfolger());
                einzufuegenderKnoten.getNachfolger().setVorgaenger(einzufuegenderKnoten);
            }

            vorgaengerKnoten.setNachfolger(einzufuegenderKnoten);
            einzufuegenderKnoten.setVorgaenger(vorgaengerKnoten);
        }

        groesse = groesse + 1;
        return true;
    }


    @Override
    public boolean remove(Object object) {

        for (Listenknoten<T> knoten = ersterKnoten; !knoten.equals(letzterKnoten); knoten = knoten.getNachfolger()) {
            if(knoten.getElement().equals(object)){

                return remove(knoten);
            }
        }
        return false;
    }



    boolean remove(Listenknoten<T> knoten) {
        if(knoten == ersterKnoten){
            ersterKnoten = knoten.getNachfolger();
        }else {
            knoten.getVorgaenger().setNachfolger(knoten.getNachfolger());
        }

        if(knoten == letzterKnoten){
            letzterKnoten = knoten.getVorgaenger();
        }else {
            knoten.getNachfolger().setVorgaenger(knoten.getVorgaenger());
        }
        groesse = groesse - 1;
        return true;
    }

    Listenknoten<T> getErsterKnoten() {
        return ersterKnoten;
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
    public T get(int i) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public T set(int i, T t) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public T remove(int i) {
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
}
