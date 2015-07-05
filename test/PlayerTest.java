import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerTest {

    private Player testPlayer;

    @Before
    public void setUp() {
        this.testPlayer = Player.getInstance().newInstance();
    }

    @Test
    public void testApListener() {
        DummyListener dummyListener;

        dummyListener = createDummyListener(Player.Property.AP);

        int usedAp = 3;
        Assert.assertEquals(true, this.testPlayer.useAp(usedAp));
        Assert.assertEquals(true, dummyListener.isNotified);

        dummyListener.isNotified = false;
        this.testPlayer.regenerateAp();
        Assert.assertEquals(true, dummyListener.isNotified);
    }

    @Test
    public void testRegenerateAp() {
        Assert.assertEquals(this.testPlayer.getMaxAp(), this.testPlayer.getAp());

        int usedAp = 3;
        Assert.assertEquals(true, this.testPlayer.useAp(usedAp));
        Assert.assertEquals(this.testPlayer.getMaxAp() - usedAp, this.testPlayer.getAp());

        this.testPlayer.regenerateAp();
        Assert.assertEquals(this.testPlayer.getMaxAp(), this.testPlayer.getAp());
    }

    @Test
    public void testUseAp() {
        Assert.assertEquals(this.testPlayer.getMaxAp(), this.testPlayer.getAp());

        int usedAp = 3;
        Assert.assertEquals(true, this.testPlayer.useAp(usedAp));
        Assert.assertEquals(this.testPlayer.getMaxAp() - usedAp, this.testPlayer.getAp());

        Assert.assertEquals(true, this.testPlayer.useAp(this.testPlayer.getAp()));
        Assert.assertEquals(0, this.testPlayer.getAp());

        Assert.assertEquals(false, this.testPlayer.useAp(1));
        Assert.assertEquals(0, this.testPlayer.getAp());
    }

    private DummyListener createDummyListener(Player.Property property) {
        DummyListener dummyListener;
        dummyListener = new DummyListener(property);
        this.testPlayer.addPropertyChangeListener(property, dummyListener);

        return dummyListener;
    }

    private class DummyListener implements PropertyChangeListener {

        private final Player.Property property;
        private boolean isNotified;

        public DummyListener(Player.Property property) {
            this.property = property;
        }

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            Assert.assertEquals(this.property.name(), propertyChangeEvent.getPropertyName());
            Assert.assertFalse(isNotified);
            isNotified = true;
        }
    }

}
