package replay;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import battleground.Battlefield;
import creatures.Creatures;

public class Record {
    private Vector<String> vector;          // 用于回看的向量
    private Vector<String> vectorforsave;   // 用于存储的向量
    private Battlefield world;
    private boolean isrecording;
    private boolean isSaved;

    public Record(Battlefield world) {
        vector = new Vector<>();
        vectorforsave = new Vector<>();
        this.world = world;
        isrecording = true;
        isSaved = false;
    }
    // 是否保存
    public boolean getIsSaved() {
        return isSaved;
    }
    // 停止记录
    public void reversefuc() {
        isrecording = false;
    }
    // 获取当前是否在记录
    public boolean getFunc() {
        return isrecording;
    }
    // 写入一条记录到缓存区
    public void write(String str) {
        vector.add(str);
        vectorforsave.add(str);
    }
    // 保存文件
    public void save(File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : vectorforsave) {
            bw.write(str);
        }
        bw.close();
        isSaved = true;
    }
    // 加载文件，回放以前的战斗
    public void loadFile(File file) throws IOException {
        FileReader fr = new FileReader(file.getAbsoluteFile());
        BufferedReader br = new BufferedReader(fr);
        String str = null;
        while((str = br.readLine()) != null) {
            vector.add(str);
        }
        replay();
    }
    // 获取当前记录所对应的生物
    public Creatures getCreature(String str, String no) {
        if(str.equals("爷爷"))
            return world.getGrandPa();
        else if(str.equals("蛇精"))
            return world.getSnake();
        else if(str.equals("葫芦娃")) {
            int index = Integer.parseInt(no);
            return world.getBrothers().getMember(index);
        }
        else if(str.equals("蝎子精")) {
            int index = Integer.parseInt(no);
            return world.getSoldiers().getMember(index);
        }
        else
            return null;
    }
    // 回放和复盘
    public void replay() {
        int num = Integer.parseInt(vector.firstElement().split("\r\n")[0]);
        vector.remove(0);
        for(int i = 0; i < num; i++) {
            String str = vector.firstElement();
            String[] line = str.split(" |\r\n");
            Creatures current = getCreature(line[1], line[2]);
            current.setPosition(Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            vector.remove(0);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(world.isDone()) {
                    if(world.isHuLuWin())
                        world.setLog(new String("葫芦娃们取得了最终的胜利！\n\n"));
                    else
                        world.setLog(new String("妖精们取得了最终的胜利！\n\n"));
                    timer.cancel();
                    System.gc();
                }
                String str = vector.firstElement();
                vector.remove(0);
                String[] line = str.split(" |\r\n");
                if (!line[0].equals("battle")) {
                    Creatures current = getCreature(line[1], line[2]);
                    if (line[0].equals("sacrifice")) {
                        current.Sacrifice();
                    }
                    else if(line[0].equals("disappear")) {
                        current.disappear();
                    }
                    else {
                        current.leavePosition();
                        current.setPosition(Integer.parseInt(line[3]), Integer.parseInt(line[4]));
                    }
                }
            }
        }, 0, 100);
    }
}
