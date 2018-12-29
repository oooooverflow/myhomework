package form;

import creatures.*;

interface Formations {
    public int HeYi(LouLuo soldiers, SheJing leader);
    public int YanXing(LouLuo soldiers, SheJing leader);
    public int YuLin(LouLuo soldiers, SheJing leader);
    public int YanYue(LouLuo soldiers, SheJing leader);
    public int FengShi(LouLuo soldiers, SheJing leader);
}

public class Formation implements Formations {
    public int HeYi(LouLuo soldiers, SheJing leader) {
        int centerX = 4, centerY = 7;
        soldiers.getMember(0).setPosition(centerX, centerY);
        for(int i = 1; i < 4; i++) {
            soldiers.getMember(i).setPosition(centerX-i, centerY-i);
            soldiers.getMember(i+3).setPosition(centerX-i, centerY+i);
        }
        leader.setPosition(5, 7);
        return 7;
    }

    public int YanXing(LouLuo soldiers, SheJing leader) {
        int centerX = 4, centerY = 9;
        soldiers.getMember(0).setPosition(centerX, centerY);
        for(int i = 1; i < 3; i++) {
            soldiers.getMember(i).setPosition(centerX-i, centerY-i);
            soldiers.getMember(i+2).setPosition(centerX+i, centerY+i);
        }
        leader.setPosition(4, 10);
        return 5;
    }

    public int YuLin(LouLuo soldiers, SheJing leader) {
        int centerX = 3, centerY = 8;
        soldiers.getMember(0).setPosition(centerX, centerY);
        soldiers.getMember(1).setPosition(centerX, centerY+2);
        soldiers.getMember(2).setPosition(centerX, centerY-2);
        soldiers.getMember(3).setPosition(centerX-1, centerY+1);
        soldiers.getMember(4).setPosition(centerX-2, centerY);
        soldiers.getMember(5).setPosition(centerX+1, centerY-3);
        soldiers.getMember(6).setPosition(centerX+1, centerY-1);
        soldiers.getMember(7).setPosition(centerX+1, centerY+1);
        soldiers.getMember(8).setPosition(centerX+1, centerY+3);
        soldiers.getMember(9).setPosition(centerX+2, centerY);
        leader.setPosition(2, 7);
        return 10;
    }

    public int YanYue(LouLuo soldiers, SheJing leader) {
        int centerX = 4, centerY = 8;
        soldiers.getMember(0).setPosition(centerX, centerY);
        soldiers.getMember(1).setPosition(centerX-1, centerY);
        soldiers.getMember(2).setPosition(centerX-2, centerY+1);
        soldiers.getMember(3).setPosition(centerX-3, centerY+2);
        soldiers.getMember(4).setPosition(centerX-4, centerY+3);
        soldiers.getMember(5).setPosition(centerX+1, centerY);
        soldiers.getMember(6).setPosition(centerX+2, centerY+1);
        soldiers.getMember(7).setPosition(centerX+3, centerY+2);
        soldiers.getMember(8).setPosition(centerX+4, centerY+3);
        soldiers.getMember(9).setPosition(centerX, centerY-1);
        soldiers.getMember(10).setPosition(centerX-1, centerY-1);
        soldiers.getMember(11).setPosition(centerX-2, centerY);
        soldiers.getMember(12).setPosition(centerX-3, centerY+1);
        soldiers.getMember(13).setPosition(centerX+1, centerY-1);
        soldiers.getMember(14).setPosition(centerX+2, centerY);
        soldiers.getMember(15).setPosition(centerX+3, centerY+1);
        soldiers.getMember(16).setPosition(centerX, centerY-2);
        soldiers.getMember(17).setPosition(centerX-1, centerY-2);
        soldiers.getMember(18).setPosition(centerX+1, centerY-2);
        leader.setPosition(4, 5);
        return 19;
    }

    public int FengShi(LouLuo soldiers, SheJing leader) {
        int centerX = 2, centerY = 8;
        soldiers.getMember(0).setPosition(centerX, centerY);
        soldiers.getMember(1).setPosition(centerX+1, centerY-1);
        soldiers.getMember(2).setPosition(centerX+2, centerY-2);
        soldiers.getMember(3).setPosition(centerX+3, centerY-3);
        soldiers.getMember(4).setPosition(centerX+1, centerY+1);
        soldiers.getMember(5).setPosition(centerX+2, centerY+2);
        soldiers.getMember(6).setPosition(centerX+3, centerY+3);
        soldiers.getMember(7).setPosition(centerX+1, centerY);
        soldiers.getMember(8).setPosition(centerX+2, centerY);
        soldiers.getMember(9).setPosition(centerX+3, centerY);
        soldiers.getMember(10).setPosition(centerX+4, centerY);
        soldiers.getMember(11).setPosition(centerX+5, centerY);
        leader.setPosition(1,8);
        return 12;
    }
}
