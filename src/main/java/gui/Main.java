package gui;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MyScene.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Calabash Brothers vs SheJing");
        primaryStage.setScene(new Scene(root, 1532, 840));
        primaryStage.show();
        GameController controller = fxmlLoader.getController();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(controller.getIsInit() && !controller.getBattleField().isSaved() && controller.getIsWorked()) {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showSaveDialog(primaryStage);
                    if (file != null)
                        controller.getBattleField().saveRecord(file);
                }
                System.exit(0);
            }
        });
    }
}
