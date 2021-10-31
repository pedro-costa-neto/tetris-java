package pedro.costa.neto.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 * JavaFX App
 */
public class TetrisApplicaption extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        int largura = 20;
        int altura = 20;

        int windowWidth = 300;
        int windowHeight = 400;

        Canvas canvas = new Canvas(windowWidth, windowHeight);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        ArrayList<String> teclas = new ArrayList<>();
        Screen screen = new Screen(graphicsContext, windowWidth, windowHeight, altura, teclas);
        screen.start();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                if (!teclas.contains(code)) {
                    teclas.add(code);
                }
            }
        });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                teclas.remove(code);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Tetris - Pedro Costa");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
