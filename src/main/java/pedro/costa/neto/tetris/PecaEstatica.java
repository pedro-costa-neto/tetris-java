package pedro.costa.neto.tetris;

import javafx.scene.image.Image;

/**
 *
 * @author Pedro Costa
 * @date 21/10/2020
 */
public class PecaEstatica {
    private Image figura;
    private int posicaoX;
    private int posicaoY;

    public PecaEstatica(Image figura, int posicaoX, int posicaoY) {
        this.figura = figura;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }
    
    public Image getFigura() {
        return figura;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }
}
