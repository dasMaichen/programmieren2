import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Mai Kuginuki on 14.06.2015.
 */
public class AvlBaumTest {

    AvlBaum<Integer> testBaum;

    @Before
    public void setup(){
        testBaum = new AvlBaum<Integer>();
    }

    @Test
    public void testadd(){
        Baumknoten<Integer> wurzel = new Baumknoten<Integer>(42);
        testBaum.add(wurzel);
        Assert.assertFalse(testBaum.isEmpty());
        Assert.assertEquals(1,testBaum.size());
        Assert.assertEquals(wurzel, testBaum.getWurzelknoten());
        Assert.assertEquals(null, wurzel.getElternKnoten());

        Baumknoten<Integer> knoten2 = new Baumknoten<>(90);
        testBaum.add(knoten2);
        Assert.assertEquals(2,testBaum.size());
        Assert.assertEquals(knoten2, wurzel.getRechtesKind());
        Assert.assertEquals(wurzel,knoten2.getElternKnoten());

        Baumknoten<Integer> knoten3 = new Baumknoten<>(100);
        testBaum.add(knoten3);
        Assert.assertEquals(3,testBaum.size());
        Assert.assertEquals(knoten3, knoten2.getRechtesKind());
        Assert.assertEquals(knoten2,knoten3.getElternKnoten());

        Baumknoten<Integer> knoten4 = new Baumknoten<>(2);
        testBaum.add(knoten4);
        Assert.assertEquals(4,testBaum.size());
        Assert.assertEquals(knoten4, wurzel.getLinkesKind());
        Assert.assertEquals(wurzel,knoten4.getElternKnoten());


    }

    @Test
    public void sucheElternknotenTest(){
        Baumknoten<Integer> wurzel = new Baumknoten<Integer>(42);
        Assert.assertNull(testBaum.sucheElternknoten(wurzel));
        testBaum.add(wurzel);

        Baumknoten<Integer> knoten2 = new Baumknoten<>(3);
        Assert.assertEquals(wurzel,testBaum.sucheElternknoten(knoten2));
        testBaum.add(knoten2);

        Baumknoten<Integer> knoten3 = new Baumknoten<>(7);
        Assert.assertEquals(knoten2, testBaum.sucheElternknoten(knoten3));
    }

    @Test
    public void vorgaengerNachfolgerTest(){

        for (int i = 0; i < 10; i++) {
            testBaum.add(i);
        }

        Listenknoten<Integer> knoten = testBaum.getMinimum();

        for (int i = 0; i < 10; i++) {
            //System.out.println(knoten.getElement());
            Assert.assertEquals(i, (int) knoten.getElement());
            knoten = knoten.getNachfolger();

        }

    }

    @Test
    public void suchenTest(){

        Baumknoten<Integer> wurzel = new Baumknoten<>(7);
        testBaum.add(wurzel);

        Baumknoten<Integer> knoten2 = new Baumknoten<>(3);
        testBaum.add(knoten2);

        Baumknoten<Integer> knoten3 = new Baumknoten<>(2);
        testBaum.add(knoten3);

        Baumknoten<Integer> knoten4 = new Baumknoten<>(4);
        testBaum.add(knoten4);

        Baumknoten<Integer> knoten5 = new Baumknoten<>(1);
        testBaum.add(knoten5);

        Baumknoten<Integer> knoten6 = new Baumknoten<>(10);
        testBaum.add(knoten6);

        Baumknoten<Integer> gesuchterKnoten = testBaum.suchen(knoten4.getElement());
        Assert.assertEquals(knoten4,gesuchterKnoten);

        Assert.assertEquals(null,testBaum.suchen(15));




    }

    @Test
    public void removeTest(){
        Baumknoten<Integer> wurzel = new Baumknoten<>(7);
        testBaum.add(wurzel);

        Baumknoten<Integer> knoten2 = new Baumknoten<>(3);
        testBaum.add(knoten2);

        Baumknoten<Integer> knoten3 = new Baumknoten<>(2);
        testBaum.add(knoten3);

        Baumknoten<Integer> knoten4 = new Baumknoten<>(4);
        testBaum.add(knoten4);

        Baumknoten<Integer> knoten5 = new Baumknoten<>(1);
        testBaum.add(knoten5);



        Listenknoten<Integer> rauswerfKnoten = new Listenknoten<>(3);

        testBaum.remove((Integer)7);

        System.out.println(testBaum.getWurzelknoten());
    }

    @Test
    public void setzeHoeheTest(){
        Baumknoten<Integer> wurzel = new Baumknoten<>(7);
        testBaum.add(wurzel);
        testBaum.hoeheAktualisieren(wurzel.getElternKnoten());

        Baumknoten<Integer> knoten2 = new Baumknoten<>(3);
        testBaum.add(knoten2);
        testBaum.hoeheAktualisieren(knoten2.getElternKnoten());

        Baumknoten<Integer> knoten3 = new Baumknoten<>(2);
        testBaum.add(knoten3);
        testBaum.hoeheAktualisieren(knoten3.getElternKnoten());

        Baumknoten<Integer> knoten4 = new Baumknoten<>(4);
        testBaum.add(knoten4);
        testBaum.hoeheAktualisieren(knoten4.getElternKnoten());

        Baumknoten<Integer> knoten5 = new Baumknoten<>(1);
        testBaum.add(knoten5);
        testBaum.hoeheAktualisieren(knoten5.getElternKnoten());

        Assert.assertEquals(3,wurzel.getHoehe());
        Assert.assertEquals(0,knoten4.getHoehe());
    }

    @Test
    public void rebalancieren(){
        Baumknoten<Integer> wurzel = new Baumknoten<>(7);
        testBaum.add(wurzel);

        Baumknoten<Integer> knoten2 = new Baumknoten<>(5);
        testBaum.add(knoten2);

        Baumknoten<Integer> knoten3 = new Baumknoten<>(4);
        testBaum.add(knoten3);

        Baumknoten<Integer> knoten4 = new Baumknoten<>(3);
        testBaum.add(knoten4);

        Assert.assertEquals(2,testBaum.getWurzelknoten().getHoehe());
    }

}
