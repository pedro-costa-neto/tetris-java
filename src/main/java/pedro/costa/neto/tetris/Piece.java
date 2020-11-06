package pedro.costa.neto.tetris;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 21/10/2020
 */
public class Piece {

    protected int width = 0;
    protected int height = 0;
    protected int initialPositionX = 0;
    protected int initialPositionY = 0;
    protected int finalPositionX = 0;
    protected int finalPositionY = 0;
    protected boolean[][] pieceStructure;
    protected Image figure;

    public Piece(Image figure, int pieceSize, boolean[][] pieceStructure) {
        this.figure = figure;
        this.width = pieceSize;
        this.height = pieceSize;
        this.pieceStructure = pieceStructure;
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
