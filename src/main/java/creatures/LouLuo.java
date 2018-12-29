package creatures;

import battleground.Battlefield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LouLuo {
    private XieZiJing Members[];

    public LouLuo(Battlefield world, XieZiJing soldiers[]) {
        Members = new XieZiJing[soldiers.length];
        for(int i = 0; i < soldiers.length; i++) {
            Members[i] = new XieZiJing(world);
        }
    }

    public LouLuo(ImageView XieZi, ImageView LoLo[], Battlefield world, Image dead) {
        Members = new XieZiJing[19];
        Members[0] = new XieZiJing(XieZi, 0, world, dead);
        Members[0].setJob(XieZiJing.Job.大哥);
        for(int i = 1; i < 19; i++) {
            Members[i] = new XieZiJing(LoLo[i-1], i, world, dead);
            Members[i].setJob(XieZiJing.Job.小弟);
        }
    }

    public boolean isAlive(int num) {
        boolean res = false;
        for(int i = 0; i < num; i++) {
            if(Members[i].isAlive())
                res = true;
        }
        return res;
    }

    public XieZiJing getMember(int i) {
        return Members[i];
    }
}
