package creatures;

import battleground.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SheJing extends  Creatures {
    public SheJing(Battlefield world) {
        super(world);
    }

    public SheJing(ImageView myself, Battlefield world, Image dead) {
        super("蛇精", -1, myself, world, dead, false);
    }
}
