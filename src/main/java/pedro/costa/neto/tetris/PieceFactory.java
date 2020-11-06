package pedro.costa.neto.tetris;

import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 2020-10-31
 */
public class PieceFactory {
    private static String[] figuresNames = {
        "bloco_azul.png", 
        "bloco_vermelho.png"
    };
    private static boolean[][][] piecesStructures = {
        {
            {false, true , false},
            {true , true , false},
            {false, true , false}
        },
        {
            {true , false, false},
            {true , true , false},
            {false, true , false}
        },
        {
            {true , true , true },
            {false, false, false},
            {false, false, false}
        },
        {
            {true , true , false},
            {true , true , false},
            {false, false, false}
        }
    };
    
    private static boolean[][] getPieceStructure() {
        int random = getRandom(piecesStructures.length - 1);
        return piecesStructures[random];
    }
    
    private static Image getFigure() {
        int random = getRandom(figuresNames.length);
        return new Image(figuresNames[random]);
    }
    
    private static int getRandom(int valueMaximum){
        return (int) (1 + (Math.random() * (valueMaximum - 1)));
    }

    public static Piece getPiece(int pieceSize) {
        Image figure = getFigure();
        boolean[][] pieceStructure = getPieceStructure();
        return new Piece(figure, pieceSize, pieceStructure);
    }
}
