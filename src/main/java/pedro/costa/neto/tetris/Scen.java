package pedro.costa.neto.tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 2020-10-28
 */
public class Scen {

    private Image backFigure;
    private PieceStatic[][] partsGrid;

    public Scen(Image backFigure, int positionX, int positionY) {
        this.backFigure = backFigure;
        this.partsGrid = new PieceStatic[positionX][positionY];
    }

    public void renderGrid(GraphicsContext graphicsContext, int width, int height) {
        for (int axisX = 0; axisX < partsGrid.length; axisX++) {
            for (int axisY = 0; axisY < partsGrid[axisX].length; axisY++) {
                PieceStatic piece = partsGrid[axisX][axisY];
                int axisPositionX = (axisX * width);
                int axisPositionY = (axisY * height);
                
                if (piece != null) {
                    graphicsContext.drawImage(
                            piece.getFigure(), 
                            axisPositionX, 
                            axisPositionY
                    );
                } else {
                    graphicsContext.drawImage(
                            backFigure, 
                            axisPositionX, 
                            axisPositionY
                    );
                }
            }
        }
    }

    public void setPieceStatic(PieceStatic pieceStatic, int positionX, int positionY) {
        partsGrid[positionX][positionY] = pieceStatic;
    }

    public PieceStatic[][] getPartsGrid() {
        return partsGrid;
    }

    public boolean isCollision(int positionX, int positionY) {
        return partsGrid[positionX][positionY] != null;
    }
}
