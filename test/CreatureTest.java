import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreatureTest {

    private Creature testCreature;

    @Before
    public void setUp() {
        this.testCreature = new Creature(100, 10, 0.5);
    }

    @Test
    public void testHpListener() {
        DummyListener dummyListener;

        dummyListener = createDummyListener(Creature.Property.HP);
        this.testCreature.setHp(50);
        Assert.assertEquals(true, dummyListener.isNotified);
    }

    @Test
    public void testSetHp() {
        Assert.assertEquals(this.testCreature.getMaxHp(), this.testCreature.getHp());

        this.testCreature.setHp(30);
        Assert.assertEquals(30, this.testCreature.getHp());

        this.testCreature.setHp(2 * this.testCreature.getMaxHp());
        Assert.assertEquals(this.testCreature.getMaxHp(), this.testCreature.getHp());

        this.testCreature.setHp(-10);
        Assert.assertEquals(0, this.testCreature.getHp());
    }

    private DummyListener createDummyListener(Creature.Property property) {
        DummyListener dummyListener;
        dummyListener = new DummyListener(property);
        this.testCreature.addPropertyChangeListener(property, dummyListener);

        return dummyListener;
    }

    private class DummyListener implements PropertyChangeListener {

        private final Creature.Property property;
        private boolean isNotified;

        public DummyListener(Creature.Property property) {
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
