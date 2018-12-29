package battleground;

import creatures.Grandfather;
import creatures.HuluBro;
import creatures.SheJing;
import creatures.LouLuo;
import form.Formation;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import replay.*;

public class Battlefield extends Formation{
    public static Record record;
    public Blocks fields[][];
    public int N;
    public int M;
    private Grandfather grandPa;
    private SheJing snake;
    private HuluBro Brothers;
    private LouLuo Soldiers;
    private int validsoldiers;
    private TextArea log;

    public Battlefield(int N, int M) {
        fields = new Blocks[N][M];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                fields[i][j] = new Blocks();
        }
    }

    public Battlefield(int N, int M, ImageView grandfa, ImageView Snake, ImageView Hulu[], ImageView XieZi, ImageView LoLo[], Image dead, TextArea log) {
        fields = new Blocks[N][M];
        record = new Record(this);
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                fields[i][j] = new Blocks();
        }
        this.N = N;
        this.M = M;
        this.log = log;
        grandPa = new Grandfather(grandfa, this, dead);
        snake = new SheJing(Snake, this, dead);
        Brothers = new HuluBro(Hulu, this, dead);
        Soldiers = new LouLuo(XieZi, LoLo, this, dead);
        validsoldiers = 19;
    }

    // 在记录框打印这个世界中发生的战斗事件和战斗结果
    public void setLog(String message) {
        log.appendText(message);
    }
    // 判断战斗是否结束
    public boolean isDone() {
        return (!Brothers.isAlive()&&!grandPa.isAlive())||(!snake.isAlive()&&!Soldiers.isAlive(validsoldiers));
    }
    // 是否为葫芦娃赢得了战斗
    public boolean isHuLuWin() {
        return (!snake.isAlive()&&!Soldiers.isAlive(validsoldiers));
    }
    // 开始整个游戏
    public void startTheGame() {
        setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
        record.write(new String((validsoldiers+9)+"\r\n"));
        record.write(new String("init "+"爷爷 "+"-1 "+grandPa.Getx()+" "+grandPa.Gety()+"\r\n"));
        record.write(new String("init "+"蛇精 "+"-1 "+snake.Getx()+" "+snake.Gety()+"\r\n"));
        for(int i = 0; i < 7; i++) {
            record.write(new String("init "+"葫芦娃 "+i+" "+Brothers.getMember(i).Getx()+" "+Brothers.getMember(i).Gety()+"\r\n"));
        }
        for(int i = 0; i < validsoldiers; i++) {
            record.write(new String("init "+"蝎子精 " + i+" "+Soldiers.getMember(i).Getx() + " " + Soldiers.getMember(i).Gety()+"\r\n"));
        }
        /*String str = new String("游戏开始!\n");
        try {
            setLog(new String(str.getBytes("GBK")));
        }catch(Exception e) {
            e.printStackTrace();
        }*/
        setLog(new String("游戏开始!\n"));
        new Thread(grandPa).start();
        new Thread(snake).start();
        for(int i = 0; i < 7; i++) {
            new Thread(Brothers.getMember(i)).start();
        }
        for(int i = 0; i < validsoldiers; i++) {
            new Thread(Soldiers.getMember(i)).start();
        }
    }
    // 清理战场
    public void clearTheField() {
        grandPa.setAlive();
        snake.setAlive();
        for(int i = 0; i < 7; i++) {
            Brothers.getMember(i).setAlive();
        }
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
    }

    public Grandfather getGrandPa() {
        return grandPa;
    }

    public SheJing getSnake() {
        return snake;
    }

    public HuluBro getBrothers() {
        return Brothers;
    }

    public LouLuo getSoldiers() {
        return Soldiers;
    }
    // 停止记录
    public void closeRecord() {
        record.reversefuc();
    }
    // 保存战场中的战斗记录至指定文件
    public void saveRecord(File file) {
        try {
            record.save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    // 复盘上一场战斗
    public void startReplayLast() {
        clearTheField();
        record.replay();
    }

    public void startReplayPre(File file) {
        //clearTheField();
        try{
            record.loadFile(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSaved() {
        return record.getIsSaved();
    }

    public void transformToHeYi() {
        snake.setAlive();
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
        validsoldiers = HeYi(Soldiers, snake);
        for(int i = validsoldiers; i < 19; i++)
            Soldiers.getMember(i).walkOut();
        setLog(new String("妖精们变阵了: 鹤翼阵!\n"));
        //setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
    }

    public void transformToYanXing() {
        snake.setAlive();
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
        validsoldiers = YanXing(Soldiers, snake);
        for(int i = validsoldiers; i < 19; i++)
            Soldiers.getMember(i).walkOut();
        setLog(new String("妖精们变阵了: 雁行阵!\n"));
        //setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
    }

    public void transformToYuLin() {
        snake.setAlive();
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
        validsoldiers = YuLin(Soldiers, snake);
        for(int i = validsoldiers; i < 19; i++)
            Soldiers.getMember(i).walkOut();
        setLog(new String("妖精们变阵了: 鱼鳞阵!\n"));
        //setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
    }

    public void transformToYanYue() {
        snake.setAlive();
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
        validsoldiers = YanYue(Soldiers, snake);
        for(int i = validsoldiers; i < 19; i++)
            Soldiers.getMember(i).walkOut();
        setLog(new String("妖精们变阵了: 偃月阵!\n"));
        //setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
    }

    public void transformToFengShi() {
        snake.setAlive();
        for(int i = 0; i < 19; i++) {
            Soldiers.getMember(i).setAlive();
        }
        validsoldiers = FengShi(Soldiers, snake);
        for(int i = validsoldiers; i < 19; i++)
            Soldiers.getMember(i).walkOut();
        setLog(new String("妖精们变阵了: 锋矢阵!\n"));
        //setLog(new String("葫芦娃们摆出了一字长蛇阵以不变应万变!\n"));
    }
}

