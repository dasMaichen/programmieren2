import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mai on 15.06.15.
 */
public class DoppeltVerketteteListeTest {

    DoppeltVerketteteListe<Integer> testListe;

    @Before
    public void setup() {
        testListe = new DoppeltVerketteteListe<>();
    }


    @Test
    public void testGet(){

        testListe.add(7);
        testListe.add(5);


        Assert.assertEquals(5,(int)testListe.get(1));

    }


    @Test
    public void testIndexOf(){

        testListe.add(7);
        testListe.add(5);


        Assert.assertEquals(1,testListe.indexOf(5));

    }

}
