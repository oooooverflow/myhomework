package creatures;

import form.*;
import battleground.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HuluWa extends Creatures {
    public HuluWa(ImageView myself, int rank, Battlefield world, Image dead, int i) {
        super("葫芦娃", rank, myself, world, dead, true);
    }
}
