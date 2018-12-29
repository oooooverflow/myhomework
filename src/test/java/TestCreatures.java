import battleground.Battlefield;
import creatures.*;
import org.junit.Assert;
import org.junit.Test;

public class TestCreatures {
    @Test
    public void testSetPosition() {
        Battlefield world = new Battlefield(10, 5);
        Creatures creature = new Creatures(world);
        creature.setPosition(3, 3);
        Assert.assertTrue(world.fields[3][3].isAnyCreature());
        Assert.assertTrue(world.fields[3][3].getCreature() == creature);
    }
}
