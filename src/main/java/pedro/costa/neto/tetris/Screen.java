package pedro.costa.neto.tetris;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 2020-10-30
 */
public class Screen extends AnimationTimer {

    private long startNanoTime;
    private int screenWidth;
    private int screenHeight;
    private int positionMaxAxisX;
    private int positionMaxAxisY;
    private int pieceSize;
    private int currentHorizontalPosition;
    private GraphicsContext graphicsContext;
    private ArrayList<String> actionKeys;
    private Scen scen;
    private Piece piece;

    public Screen(GraphicsContext graphicsContext, int screenWidth, int screenHeight, int pieceSize, ArrayList<String> actionKeys) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.startNanoTime = System.nanoTime();
        this.pieceSize = pieceSize;
        this.positionMaxAxisX = (screenWidth / pieceSize);
        this.positionMaxAxisY = (screenHeight / pieceSize);
        this.currentHorizontalPosition = getCenterHorizontal();
        this.actionKeys = actionKeys;
        this.graphicsContext = graphicsContext;

        Image fundo = new Image("fundo02.png");
        Image imagem2 = new Image("bloco_azul.png");
        scen = new Scen(fundo, this.positionMaxAxisX, this.positionMaxAxisY);

        piece = new Peca02(imagem2, pieceSize, pieceSize);
    }

    @Override
    public void handle(long currentNanoTime) {
        Double t = (currentNanoTime - startNanoTime) / 1000000000.0;
        scen.renderGrid(graphicsContext, pieceSize, pieceSize);
        piece.renderPiece(graphicsContext, this.getWidthPositionMax(), t.intValue());

        System.out.println("Teclas: " + actionKeys.size());

        if (actionKeys.contains("LEFT") && currentHorizontalPosition > 0) {
            currentHorizontalPosition -= 5;
        }

        if (actionKeys.contains("RIGHT") && currentHorizontalPosition < 300) {
            currentHorizontalPosition += 5;
        }

        boolean isCollision = false;
        for (int x = 0; x < piece.getFinalPositionX(); x++) {
            for (int y = 0; y < piece.getFinalPositionY(); y++) {
                int PosX = this.getWidthPositionMax() + x;
                int PosY = (t.intValue() + y + 1);

                //System.out.println("X: " + PosX + " / Y: " + PosY);
                if (isHeightPositionMax(PosY) || (scen.isCollision(PosX, PosY) && !piece.isEmpty(x, y))) {
                    isCollision = true;
                    break;
                }
            }
        }

        System.out.println("X: " + this.getWidthPositionMax() + " / Y: " + t.intValue());

        if (isCollision) {
            System.out.println("Limite");
            scen.setPiecesStatic(piece.getPiecesStatic());

            Random gerador = new Random();
            int numPeca = gerador.nextInt(3);

            Image image = new Image("bloco_azul.png");
            switch (numPeca) {
                case 0:
                    piece = new Peca01(image, 20, 20);
                    break;
                case 1:
                    piece = new Peca02(image, 20, 20);
                    break;
                case 2:
                    piece = new Peca03(image, 20, 20);
                    break;
                case 3:
                    piece = new Peca04(image, 20, 20);
                    break;
            }

            this.startNanoTime = System.nanoTime();
            this.currentHorizontalPosition = getCenterHorizontal();
            this.graphicsContext.clearRect(0, 0, 0, 0);
        }
    }

    private int getCenterHorizontal() {
        return this.currentHorizontalPosition = (this.screenWidth / 2);
    }

    private int getWidthPositionMax() {
        int positionX = (this.currentHorizontalPosition / pieceSize);
        return (positionX + piece.getFinalPositionX()) > 14 ? (positionX - piece.getFinalPositionX()) : positionX;
    }

    public boolean isHeightPositionMax(int positionY) {
        return positionMaxAxisY == positionY;
    }

}
