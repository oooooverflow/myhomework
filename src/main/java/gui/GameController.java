package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import battleground.*;
import javafx.scene.image.*;

public class GameController {
    @FXML private Button StartButton;
    @FXML private Button ReDoButton;
    @FXML private Button ExitButton;
    @FXML private Button HeYi;
    @FXML private Button YanXing;
    @FXML private Button YuLin;
    @FXML private Button YanYue;
    @FXML private Button FengShi;
    @FXML private ImageView GrandPa;
    @FXML private ImageView HuLu1;
    @FXML private ImageView HuLu2;
    @FXML private ImageView HuLu3;
    @FXML private ImageView HuLu4;
    @FXML private ImageView HuLu5;
    @FXML private ImageView HuLu6;
    @FXML private ImageView HuLu7;
    @FXML private ImageView LuoLuo1;
    @FXML private ImageView LuoLuo2;
    @FXML private ImageView LuoLuo3;
    @FXML private ImageView LuoLuo4;
    @FXML private ImageView LuoLuo5;
    @FXML private ImageView LuoLuo6;
    @FXML private ImageView LuoLuo7;
    @FXML private ImageView LuoLuo8;
    @FXML private ImageView LuoLuo9;
    @FXML private ImageView LuoLuo10;
    @FXML private ImageView LuoLuo11;
    @FXML private ImageView LuoLuo12;
    @FXML private ImageView LuoLuo13;
    @FXML private ImageView LuoLuo14;
    @FXML private ImageView LuoLuo15;
    @FXML private ImageView LuoLuo16;
    @FXML private ImageView LuoLuo17;
    @FXML private ImageView LuoLuo18;
    @FXML private ImageView Snake;
    @FXML private ImageView XieZi;
    @FXML private TextArea Log;
    private Image Dead;
    private Battlefield battlefield;
    private boolean isWorking = false;
    private boolean isInit = false;
    private boolean isWorked = false;
    private boolean isReplayed = false;

    public void Initialize() {
        HuLu1.setImage(new Image(this.getClass().getResource("/images/1.png").toString()));
        HuLu2.setImage(new Image(this.getClass().getResource("/images/2.png").toString()));
        HuLu3.setImage(new Image(this.getClass().getResource("/images/3.png").toString()));
        HuLu4.setImage(new Image(this.getClass().getResource("/images/4.png").toString()));
        HuLu5.setImage(new Image(this.getClass().getResource("/images/5.png").toString()));
        HuLu6.setImage(new Image(this.getClass().getResource("/images/6.png").toString()));
        HuLu7.setImage(new Image(this.getClass().getResource("/images/7.png").toString()));
        ImageView HuLu[] = {HuLu1, HuLu2, HuLu3, HuLu4, HuLu5, HuLu6, HuLu7};
        ImageView LoLo[] = {LuoLuo1, LuoLuo2, LuoLuo3, LuoLuo4, LuoLuo5, LuoLuo6, LuoLuo7, LuoLuo8, LuoLuo9, LuoLuo10, LuoLuo11, LuoLuo12, LuoLuo13, LuoLuo14, LuoLuo15, LuoLuo16, LuoLuo17, LuoLuo18};
        int i;
        for(i = 0; i < 7; i++) {
            HuLu[i].setLayoutX(0);
            HuLu[i].setLayoutY(75*(i+1));
        }
        GrandPa.setLayoutX(100);
        GrandPa.setLayoutY(300);
        GrandPa.setImage(new Image(this.getClass().getResource("/images/Grandpa.png").toString()));
        for(i = 0; i < 9; i++) {
            LoLo[i].setLayoutX(1100);
            LoLo[i].setLayoutY(75*i);
            LoLo[i].setImage(new Image(this.getClass().getResource("/images/LouLuo.png").toString()));
        }
        for(; i < 18; i++) {
            LoLo[i].setLayoutX(1000);
            LoLo[i].setLayoutY(75*(i-9));
            LoLo[i].setImage(new Image(this.getClass().getResource("/images/LouLuo.png").toString()));
        }
        Snake.setLayoutX(900);
        Snake.setLayoutY(225);
        Snake.setImage(new Image(this.getClass().getResource("/images/Snake.png").toString()));
        XieZi.setLayoutX(900);
        XieZi.setLayoutY(375);
        XieZi.setImage(new Image(this.getClass().getResource("/images/XieZiJing.png").toString()));
        Dead = new Image(this.getClass().getResource("/images/Dead.png").toString());
        battlefield = new Battlefield(10, 12, GrandPa, Snake, HuLu, XieZi, LoLo, Dead, Log);
        isInit = true;
        isWorked = false;
        isWorking = false;
    }

    @FXML public void onPressKey(KeyEvent event) {
        if(isWorking && event.getCode() == KeyCode.SPACE) {
            HeYi.setVisible(false);
            YanXing.setVisible(false);
            YuLin.setVisible(false);
            YanYue.setVisible(false);
            FengShi.setVisible(false);
            try {
                battlefield.startTheGame();
            }catch(Exception e) {
                e.printStackTrace();
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if(battlefield.isDone()) {
                        isWorking = false;
                        isWorked = true;
                        if(battlefield.isHuLuWin())
                            Log.appendText(new String("葫芦娃们取得了最终的胜利！\n\n"));
                        else
                            Log.appendText(new String("妖精们取得了最终的胜利！\n\n"));
                        timer.cancel();
                        System.gc();
                    }
                }
            }, 0, 1000);
        }
        if(!isWorking && !isReplayed && event.getCode() == KeyCode.R) {
            isReplayed = true;
            Log.appendText(new String("开始回放：\n"));
            battlefield.closeRecord();
            battlefield.startReplayLast();
        }
        if(!isWorking && event.getCode() == KeyCode.L) {
            OnClickRedo();
        }
    }

    @FXML public void OnClickStart(MouseEvent event){
        Initialize();
        StartButton.setVisible(false);
        ReDoButton.setVisible(false);
        ExitButton.setVisible(false);
        HeYi.setVisible(true);
        YanXing.setVisible(true);
        YuLin.setVisible(true);
        YanYue.setVisible(true);
        FengShi.setVisible(true);
        isWorking = true;
    }

    @FXML public void OnClickRedo(){
        StartButton.setVisible(false);
        ReDoButton.setVisible(false);
        Initialize();
        battlefield.clearTheField();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog((Stage)ReDoButton.getScene().getWindow());
        if(file != null) {
            Log.appendText(new String("开始回放：\n"));
            battlefield.startReplayPre(file);
        }
        StartButton.setVisible(true);
        ReDoButton.setVisible(true);
        ExitButton.setVisible(true);
    }

    @FXML public void OnClickExit(MouseEvent event){
        if(!isInit)
            Initialize();
        if(!battlefield.isSaved() && isWorked) {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog((Stage)ExitButton.getScene().getWindow());
            if (file != null)
                battlefield.saveRecord(file);
        }
        System.exit(0);
    }

    @FXML public void HeYi(MouseEvent event) {
        battlefield.transformToHeYi();
    }

    @FXML public void YanXing(MouseEvent event) {
        battlefield.transformToYanXing();
    }

    @FXML public void YuLin(MouseEvent event) {
        battlefield.transformToYuLin();
    }

    @FXML public void YanYue(MouseEvent event) {
        battlefield.transformToYanYue();
    }

    @FXML public void FengShi(MouseEvent event) {
        battlefield.transformToFengShi();
    }

    public Battlefield getBattleField() {
        return battlefield;
    }

    public boolean getIsWorked() {
        return isWorked;
    }

    public boolean getIsInit() {
        return isInit;
    }
}
