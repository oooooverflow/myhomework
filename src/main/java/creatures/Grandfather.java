package creatures;

import battleground.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Grandfather extends Creatures {
    public Grandfather(ImageView myself, Battlefield world, Image dead) {
        super("爷爷", -1, myself, world, dead, true);
    }
}
