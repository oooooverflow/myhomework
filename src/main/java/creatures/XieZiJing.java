package creatures;

import form.*;
import battleground.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class XieZiJing extends Creatures {
    public enum Job {大哥, 小弟};
    protected Job job;

    public XieZiJing(Battlefield world) {
        super(world);
    }

    public XieZiJing(ImageView myself, int rank, Battlefield world, Image dead) {
        super("蝎子精", rank, myself, world, dead, false);
    }

    public void setJob(Job a) {
        job = a;
    }
}
