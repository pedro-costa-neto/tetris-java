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

        Image imagem1 = new Image("bloco_vermelho.png");
        Image imagem2 = new Image("bloco_azul.png");
        Image imagem3 = new Image("bloco_transparente.png");
        Image fundo = new Image("fundo02.png");

        Canvas canvas = new Canvas(300, 400);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(fundo, 0, 0);

//        // Exemplo peça 01
//        graphicsContext.drawImage(peca1, 5 , 25, 20, 20);
//        graphicsContext.drawImage(peca1, 25, 5 , 20, 20);
//        graphicsContext.drawImage(peca1, 25, 25, 20, 20);
//        graphicsContext.drawImage(peca1, 45, 25, 20, 20);
//        
//        // Exemplo peça 02
//        graphicsContext.drawImage(peca2, 5, 65, 20, 20);
//        graphicsContext.drawImage(peca2, 25, 65, 20, 20);
//        graphicsContext.drawImage(peca2, 45, 65, 20, 20);
//        
//        // Exemplo peça 03
//        graphicsContext.drawImage(peca2, 75, 45, 20, 20);
//        graphicsContext.drawImage(peca2, 95, 45, 20, 20);
//        graphicsContext.drawImage(peca2, 75, 65, 20, 20);
//        graphicsContext.drawImage(peca2, 95, 65, 20, 20);
//        
//        // Exemplo peça 04
//        graphicsContext.drawImage(peca1, 75, 95 , 20, 20);
//        graphicsContext.drawImage(peca1, 75, 115, 20, 20);
//        graphicsContext.drawImage(peca1, 95, 115, 20, 20);
//        
//        
//        // Exemplo peça 05
//        graphicsContext.drawImage(peca2, 5 , 130, 20, 20);
//        graphicsContext.drawImage(peca2, 25, 130, 20, 20);
//        graphicsContext.drawImage(peca2, 25, 150, 20, 20);
//        graphicsContext.drawImage(peca2, 45, 150, 20, 20);
        // Exemplo teste largura
//        int x = 0;
//        while(x <= 300) {
//            graphicsContext.drawImage(peca2, x, 170, 20, 20);
//            x += 20;
//        }
//        Peca peca = new Peca01(imagem1, 5, 2, largura, altura);
//        peca.renderizarPeca(graphicsContext);
//
//        Peca peca2 = new Peca02(imagem2, 7, 2, largura, altura);
//        peca2.renderizarPeca(graphicsContext);
//
//        Peca peca3 = new Peca03(imagem1, 5, 10, 20, 20);
//        peca3.renderizarPeca(graphicsContext);
        ArrayList<String> teclas = new ArrayList<>();

        new AnimationTimer() {
            private boolean teste = true;
            private Piece peca = new Peca02(imagem2, 20, 20);
            private long startNanoTime = System.nanoTime();
            private int width = 300;
            private int height = 400;
            private int pointWidthMax = (width / largura);
            private int pointHeightMax = (height / altura);
            private Scen scen = new Scen(fundo, 15, 20);
            private int eixoX = 140;

            public void handle(long currentNanoTime) {
                Double t = (currentNanoTime - startNanoTime) / 1000000000.0;//1000000000.0;
                scen.renderGrid(graphicsContext, largura, altura);
                peca.renderPiece(graphicsContext, this.getWidthPositionMax(), t.intValue());

                if (teste) {
                    System.out.println("Teclas: " + teclas.size());

                    if (teclas.contains("LEFT") && eixoX > 0) {
                        eixoX -= 5;
                    }

                    if (teclas.contains("RIGHT") && eixoX < 300) {
                        eixoX += 5;
                    }

                    

                    boolean isCollision = false;
                    for (int x = 0; x < peca.getFinalPositionX(); x++) {
                        for (int y = 0; y < peca.getFinalPositionY(); y++) {
                            int PosX = this.getWidthPositionMax() + x;
                            int PosY = (t.intValue() + y + 1);

                            //System.out.println("X: " + PosX + " / Y: " + PosY);
                            if (isHeightPositionMax(PosY) || (scen.isCollision(PosX, PosY) && !peca.isEmpty(x, y))) {
                                isCollision = true;
                                break;
                            }
                        }
                    }
                    
                    System.out.println("X: " + this.getWidthPositionMax() + " / Y: " + t.intValue());

                    if (isCollision) {
                        System.out.println("Limite");
                        scen.setPiecesStatic(peca.getPiecesStatic());

                        Random gerador = new Random();
                        int numPeca = gerador.nextInt(3);
                        int numCor = gerador.nextInt(1);
                        Image image = numCor == 0 ? imagem1 : imagem2;
                        switch (numPeca) {
                            case 0:
                                peca = new Peca01(image, 20, 20);
                                break;
                            case 1:
                                peca = new Peca02(image, 20, 20);
                                break;
                            case 2:
                                peca = new Peca03(image, 20, 20);
                                break;
                            case 3:
                                peca = new Peca04(image, 20, 20);
                                break;
                        }

                        startNanoTime = System.nanoTime();
                        eixoX = 140;
                        graphicsContext.clearRect(0, 0, 0, 0);
                        //teste = false;
                    }
                }
            }

            private int getWidthPositionMax() {
                int positionX = (this.eixoX / largura);
                return (positionX + peca.getFinalPositionX()) > 14 ? (positionX - peca.getFinalPositionX()) : positionX;
            }
            public boolean isHeightPositionMax(int positionY) {
                return pointHeightMax == positionY;
            }
        }.start();

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
