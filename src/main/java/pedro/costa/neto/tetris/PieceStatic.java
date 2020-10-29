package pedro.costa.neto.tetris;

import javafx.scene.image.Image;

/**
 *
 * @author Pedro Costa
 * @date 21/10/2020
 */
public class PieceStatic {
    private Image figure;
    private int positionX;
    private int positionY;

    public PieceStatic(Image figure, int positionX, int positionY) {
        this.figure = figure;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    public Image getFigure() {
        return figure;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
