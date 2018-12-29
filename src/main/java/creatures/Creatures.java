package creatures;

import battleground.Battlefield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.lang.Runnable;

public class Creatures implements Runnable{
    protected String creatureName;
    protected int pos_x, pos_y;
    protected int next_x, next_y;
    protected boolean isAlive;  // 生存状态
    public boolean GoodOrBad;   // 阵营
    protected ImageView myself;
    protected Battlefield world; //生存的世界
    protected Image dead;
    protected Image preimage;
    protected int rank;         // 排行，如果有的话

    public int Getx() {
        return pos_x;
    }
    public int Gety() {
        return pos_y;
    }
    public boolean isAlive() {
        return isAlive;
    }
    // 初始化该生物的状态
    public void setAlive() {
        myself.setImage(preimage);
        myself.setVisible(false);
        if(isAlive)
            leavePosition();
        pos_x = pos_y = -1;
        isAlive = true;
    }
    // 该生物此次战斗不参战，因为没有参与选择的阵法
    public void walkOut() {
        myself.setVisible(false);
        isAlive = false;
    }

    public Creatures(Battlefield world) {
        this.world = world;
        myself = null;
    }

    public Creatures(String name, int rank, ImageView myself, Battlefield world, Image dead ,boolean camp) {
        creatureName = name;
        this.rank = rank;
        pos_x = (int)myself.getLayoutY()/75;
        pos_y = (int)myself.getLayoutX()/100;
        isAlive = true;
        next_x = next_y = -1;
        this.myself = myself;
        GoodOrBad = camp;
        this.world = world;
        this.dead = dead;
        preimage = myself.getImage();
        setPosition(pos_x, pos_y);
    }
    // 移步到某个位置
    public synchronized void setPosition ( int x, int y){
        if(myself != null)
            myself.setVisible(true);
        if (!world.fields[x][y].isAnyCreature()) {
            pos_x = x;
            pos_y = y;
            world.fields[x][y].Summon(this);
            if(myself != null) {
                myself.setLayoutX(pos_y * 100);
                myself.setLayoutY(pos_x * 75);
            }
        } else {
            System.out.println("error! 此处已有生物占领!");
            System.out.println(world.fields[pos_x][pos_y].getCreature().creatureName);
        }
    }
    // 离开某个位置
    public synchronized void leavePosition() {
        if(world.fields[pos_x][pos_y].isAnyCreature()) {
            world.fields[pos_x][pos_y].DeSummon();
        }
        else {
            System.out.println("error! 此处无生物占领!");
            System.out.println(world.fields[pos_x][pos_y].getCreature().creatureName);
        }
    }
    // 牺牲
    public synchronized void Sacrifice() {
        isAlive = false;
        myself.setImage(dead);
        if(world.record.getFunc())
            world.record.write(new String("sacrifice "+creatureName+" "+rank+" "+pos_x+" "+pos_y+"\r\n"));
        if(rank != -1)
            world.setLog(new String(creatureName+rank+"牺牲于("+pos_x+","+pos_y+")\n"));
        else
            world.setLog(new String(creatureName+"牺牲于("+pos_x+","+pos_y+")\n"));
    }
    // 决策下一步要去哪里
    public void Decision() {
        next_x = next_y = -1;
        // 四个方向分别代表是否可以朝着四个方向行走
        boolean left = true, right = true, top = true, down = true;
        synchronized(Creatures.class) {
            if (pos_x == 0) {
                top = false;
                if (world.fields[pos_x + 1][pos_y].isAnyCreature() && (GoodOrBad == world.fields[pos_x + 1][pos_y].getCreature().GoodOrBad || !world.fields[pos_x + 1][pos_y].getCreature().isAlive()))
                    down = false;
            } else if (pos_x == 9) {
                down = false;
                if (world.fields[pos_x - 1][pos_y].isAnyCreature() && (GoodOrBad == world.fields[pos_x - 1][pos_y].getCreature().GoodOrBad || !world.fields[pos_x - 1][pos_y].getCreature().isAlive()))
                    top = false;
            } else {
                if (world.fields[pos_x + 1][pos_y].isAnyCreature() && (GoodOrBad == world.fields[pos_x + 1][pos_y].getCreature().GoodOrBad || !world.fields[pos_x + 1][pos_y].getCreature().isAlive()))
                    down = false;
                if (world.fields[pos_x - 1][pos_y].isAnyCreature() && (GoodOrBad == world.fields[pos_x - 1][pos_y].getCreature().GoodOrBad || !world.fields[pos_x - 1][pos_y].getCreature().isAlive()))
                    top = false;
            }
            if (pos_y == 0) {
                left = false;
                if (world.fields[pos_x][pos_y + 1].isAnyCreature() && (GoodOrBad == world.fields[pos_x][pos_y + 1].getCreature().GoodOrBad || !world.fields[pos_x][pos_y + 1].getCreature().isAlive()))
                    right = false;
            } else if (pos_y == 11) {
                right = false;
                if (world.fields[pos_x][pos_y - 1].isAnyCreature() && (GoodOrBad == world.fields[pos_x][pos_y - 1].getCreature().GoodOrBad || !world.fields[pos_x][pos_y - 1].getCreature().isAlive()))
                    left = false;
            } else {
                if (world.fields[pos_x][pos_y + 1].isAnyCreature() && (GoodOrBad == world.fields[pos_x][pos_y + 1].getCreature().GoodOrBad || !world.fields[pos_x][pos_y + 1].getCreature().isAlive()))
                    right = false;
                if (world.fields[pos_x][pos_y - 1].isAnyCreature() && (GoodOrBad == world.fields[pos_x][pos_y - 1].getCreature().GoodOrBad || !world.fields[pos_x][pos_y - 1].getCreature().isAlive()))
                    left = false;
            }
            if (!left && !right && !top && !down) {
                return;
            }
            if (!left && !right && !top) {
                next_x = pos_x + 1;
                next_y = pos_y;
                return;
            }
            if (!left && !right && !down) {
                next_x = pos_x - 1;
                next_y = pos_y;
                return;
            }
            if (!left && !top && !down) {
                next_x = pos_x;
                next_y = pos_y + 1;
                return;
            }
            if (!right && !top && !down) {
                next_x = pos_x;
                next_y = pos_y - 1;
                return;
            }
            if (!(left && right && top && down)) {
                boolean flag = true;
                while (flag) {
                    Random ra = new Random();
                    int res = ra.nextInt(4) + 1;
                    if (res == 1 && top) {
                        next_x = pos_x - 1;
                        next_y = pos_y;
                        flag = false;
                    } else if (res == 2 && down) {
                        next_x = pos_x + 1;
                        next_y = pos_y;
                        flag = false;
                    } else if (res == 3 && left) {
                        next_x = pos_x;
                        next_y = pos_y - 1;
                        flag = false;
                    } else if (res == 4 && right) {
                        next_x = pos_x;
                        next_y = pos_y + 1;
                        flag = false;
                    } else {
                        continue;
                    }
                }
                return;
            } else {
                int startx = 0, endx = 9, starty = 0, endy = 11;
                for (int i = startx; i <= endx; i++) {
                    for (int j = starty; j <= endy; j++) {
                        if (world.fields[i][j].isAnyCreature() && world.fields[i][j].getCreature().isAlive() && GoodOrBad != world.fields[i][j].getCreature().GoodOrBad) {
                            if (next_x == -1 && next_y == -1) {
                                next_x = i;
                                next_y = j;
                            } else {
                                if (computeDistance(next_x, next_y) > computeDistance(i, j)) {
                                    next_x = i;
                                    next_y = j;
                                }
                            }
                        }
                    }
                }
            }
            return;
        }
    }
    // 计算两点距离，便于决策
    private double computeDistance(int x, int y) {
        return Math.sqrt((x-pos_x)*(x-pos_x)+(y-pos_y)*(y-pos_y));
    }
    // 前往某个点，用于规划路线
    public void Forward() {
        if (next_x == -1 && next_y == -1) {
            return;
        }
        int distance_x = next_x - pos_x;
        int distance_y = next_y - pos_y;
        if (world.fields[next_x][next_y].isAnyCreature() && GoodOrBad != world.fields[next_x][next_y].getCreature().GoodOrBad && (Math.abs(distance_x) == 1 && distance_y == 0 || Math.abs(distance_y) == 1 && distance_x == 0)) {
            if(world.record.getFunc())
                world.record.write(new String("battle "+creatureName+" "+rank+" vs "+world.fields[next_x][next_y].getCreature().creatureName+" "+rank+" "+next_x+" "+next_y+"\r\n"));
            if(rank != -1) {
                if(world.fields[next_x][next_y].getCreature().rank!=-1)
                    world.setLog(new String(creatureName+rank+"与"+world.fields[next_x][next_y].getCreature().creatureName+rank+"交战于("+pos_x+","+pos_y+")\n"));
                else
                    world.setLog(new String(creatureName+rank+"与"+world.fields[next_x][next_y].getCreature().creatureName+"交战于("+pos_x+","+pos_y+")\n"));
            }
            else {
                if(world.fields[next_x][next_y].getCreature().rank!=-1)
                    world.setLog(new String(creatureName+"与"+world.fields[next_x][next_y].getCreature().creatureName+rank+"交战于("+pos_x+","+pos_y+")\n"));
                else
                    world.setLog(new String(creatureName+"与"+world.fields[next_x][next_y].getCreature().creatureName+"交战于("+pos_x+","+pos_y+")\n"));
            }
            Battle();
            return;
        }
        leavePosition();
        if (Math.abs(distance_x) <= Math.abs(distance_y) && distance_x != 0 || distance_y == 0) {
            if (distance_x < 0) {
                setPosition(pos_x - 1, pos_y);
            } else {
                setPosition(pos_x + 1, pos_y);
            }
        }
        else {
            if (distance_y < 0)
                setPosition(pos_x, pos_y - 1);
            else
                setPosition(pos_x, pos_y + 1);
        }
        if(world.record.getFunc())
            world.record.write(new String("move "+creatureName+" "+rank+" "+pos_x+" "+pos_y+"\r\n"));
    }
    // 与某个生物战斗
    public void Battle() {
        Random ra = new Random();
        int res = ra.nextInt(10)+1;
        if(GoodOrBad == true) {
            if(res <= 3) {
                world.fields[next_x][next_y].getCreature().Sacrifice();
            }
            else {
                Sacrifice();
            }
        }
        else {
            if(res <= 3) {
                Sacrifice();
            }
            else {
                world.fields[next_x][next_y].getCreature().Sacrifice();
            }
        }
    }
    // 尸骨消失于战场
    public void disappear() {
        leavePosition();
        myself.setVisible(false);
        if(world.record.getFunc())
            world.record.write(new String("disappear "+creatureName+" "+rank+"\r\n"));
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag) {
            synchronized (Creatures.class) {
                Decision();
                Forward();
                if (!isAlive() || world.isDone()) {
                    flag = false;
                }
            }
            if(!isAlive()) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (Creatures.class) {
                    disappear();
                }
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}

