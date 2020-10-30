package pedro.costa.neto.tetris;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 21/10/2020
 */
public abstract class Piece {

    protected int width = 0;
    protected int height = 0;
    protected int initialPositionX = 0;
    protected int initialPositionY = 0;
    protected int finalPositionX = 0;
    protected int finalPositionY = 0;
    protected boolean[][] pieceStructure = new boolean[3][3];
    protected Image figure;

    public Piece(Image figure, int width, int height) {
        this.figure = figure;
        this.width = width;
        this.height = height;
    }

    public void renderPiece(GraphicsContext graphicsContext, int initialPositionX, int initialPositionY) {
        this.initialPositionX = (initialPositionX * this.width);
        this.initialPositionY = (initialPositionY * this.height);

        for (int axisX = 0; axisX < this.pieceStructure.length; axisX++) {
            for (int axisY = 0; axisY < this.pieceStructure[axisX].length; axisY++) {
                if (this.pieceStructure[axisX][axisY]) {
                    graphicsContext.drawImage(
                            this.figure,
                            this.initialPositionX + (axisX * this.width),
                            this.initialPositionY + (axisY * this.height),
                            this.width,
                            this.height
                    );

                    if (axisY >= finalPositionY) {
                        finalPositionY = axisY;
                    }

                    if (axisX >= finalPositionX) {
                        finalPositionX = axisX;
                    }
                }
            }
        }
    }

    public List<PieceStatic> getPiecesStatic() {
        List<PieceStatic> pieces = new ArrayList<>();

        for (int x = 0; x < this.pieceStructure.length; x++) {
            for (int y = 0; y < this.pieceStructure[x].length; y++) {
                if (this.pieceStructure[x][y]) {
                    pieces.add(new PieceStatic(
                            this.figure,
                            (this.initialPositionX + (x * this.width)) / this.width,
                            (this.initialPositionY + (y * this.height)) / this.height
                    ));
                }
            }
        }

        return pieces;
    }

    public abstract void montarPeca();

    public int getFinalPositionX() {
        return finalPositionX + 1;
    }

    public int getFinalPositionY() {
        return finalPositionY + 1;
    }
    
    public boolean isEmpty(int positionX, int positionY) {
        return pieceStructure[positionX][positionY] == false;
    }
}
