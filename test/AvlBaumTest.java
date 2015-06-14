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

        Baumknoten<Integer> knoten2 = new Baumknoten<>(90);
        testBaum.add(knoten2);
        Assert.assertEquals(2,testBaum.size());
        Assert.assertEquals(knoten2, wurzel.getRechtesKind());

        Baumknoten<Integer> knoten3 = new Baumknoten<>(100);
        testBaum.add(knoten3);
        Assert.assertEquals(3,testBaum.size());
        Assert.assertEquals(knoten3, knoten2.getRechtesKind());

        Baumknoten<Integer> knoten4 = new Baumknoten<>(2);
        testBaum.add(knoten4);
        Assert.assertEquals(4,testBaum.size());
        Assert.assertEquals(knoten4, wurzel.getLinkesKind());


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

}
