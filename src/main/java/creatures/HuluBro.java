package creatures;

import battleground.Battlefield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HuluBro {
    private HuluWa Members[];
    public HuluBro(ImageView myself[], Battlefield world, Image dead) {
        Members = new HuluWa[7];
        for(int i = 0; i < Members.length; i++)
            Members[i] = new HuluWa(myself[i], i, world, dead, i);
    }

    public boolean isAlive() {
        boolean res = false;
        for(int i = 0; i < 7; i++) {
            if(Members[i].isAlive())
                res = true;
        }
        return res;
    }

    public HuluWa getMember(int i) {
        return Members[i];
    }

    public HuluWa[] getMembers() {
        return Members;
    }
}
