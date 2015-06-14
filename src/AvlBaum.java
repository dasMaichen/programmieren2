import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Mai Kuginuki on 14.06.2015.
 */
public class AvlBaum<T extends Comparable<T>> implements List<T> {

    private Baumknoten<T> wurzelknoten;
    private int groesse;


    Baumknoten<T> getWurzelknoten() {
        return wurzelknoten;
    }

    @Override
    public int size() {
        return groesse;
    }

    @Override
    public boolean isEmpty() {
        if (wurzelknoten==null){
            return  true;
        }else {
            return false;
        }
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

    void add(Baumknoten<T> elementknoten){

        if(this.isEmpty()){
            wurzelknoten = elementknoten;

        }else {
            Baumknoten<T> elternknoten = sucheElternknoten(elementknoten);
            int ergebnis = elementknoten.compareTo(elternknoten);

            if(ergebnis>=0){
                elternknoten.setRechtesKind(elementknoten);
            }else {
                elternknoten.setLinkesKind(elementknoten);
            }
        }

        groesse += 1;
    }


    @Override
    public boolean add(T element) {
        Baumknoten<T> elementknoten = new Baumknoten<T>(element);
        add(elementknoten);
        return true;
    }


    public Baumknoten<T> sucheElternknoten(Baumknoten<T> baumknoten){

        Baumknoten<T> aktuellerElternteil;
        Baumknoten<T> potentiellerElternteil = wurzelknoten;


        while (potentiellerElternteil != null){

            int ergebnis = baumknoten.compareTo(potentiellerElternteil);

            if(ergebnis>=0){
                aktuellerElternteil = potentiellerElternteil.getRechtesKind();
            }else{
                aktuellerElternteil = potentiellerElternteil.getLinkesKind();
            }

            if(aktuellerElternteil == null){
                return potentiellerElternteil;
            }else {
                potentiellerElternteil = aktuellerElternteil;
            }
        }return null;
    }


    @Override
    public boolean remove(Object o) {
        return false;
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
    public T get(int index) {
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
    public T remove(int index) {
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
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("geht nicht!");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("geht nicht!");
    }
}