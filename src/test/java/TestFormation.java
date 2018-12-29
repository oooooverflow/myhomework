import battleground.Battlefield;
import creatures.*;
import form.*;
import org.junit.Assert;
import org.junit.Test;

public class TestFormation {
    @Test
    public void testHeYi() {
        Battlefield world = new Battlefield(10, 12);
        XieZiJing soldiers[] = new XieZiJing[7];
        SheJing snake = new SheJing(world);
        LouLuo team = new LouLuo(world, soldiers);
        Formation form = new Formation();
        form.HeYi(team, snake);
        Assert.assertTrue(world.fields[3][7].isAnyCreature() == false);
        Assert.assertTrue(world.fields[4][6].isAnyCreature() == false);
        Assert.assertTrue(world.fields[4][8].isAnyCreature() == false);
        Assert.assertTrue(world.fields[5][7].getCreature() == snake);
    }
}
