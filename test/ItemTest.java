import org.junit.Assert;
import org.junit.Test;

///**
// * Created by dasMaichen on 14.05.15.
// */


public class ItemTest {

//    @Test
//    public void kontruktor(){
//        Item item = new Item();
//
//        System.out.println(item);
//
//    }
//
//
//    @Test
//    public void testEquals(){
//        Item item_01 = new Item();
//        Item item_02 = new Item();
//
//        Assert.assertEquals(item_01,item_01);
//
//        Assert.assertNotEquals(item_01,item_02);

//    }

    @Test
    public void testCompareTo(){
        Item item_01 = new Item("Ball",4,100);
        Item item_02 = new Item("Alte Socke",0,300);

        Assert.assertEquals(1,item_01.compareTo(item_02));

        item_02 = new Item("Ball",69,900);
        Assert.assertEquals(1,item_01.compareTo(item_02));

        item_02 = new Item("Ball",4,1000);
        Assert.assertEquals(1,item_01.compareTo(item_02));

        Assert.assertEquals(0,item_01.compareTo(item_01));


    }

}
