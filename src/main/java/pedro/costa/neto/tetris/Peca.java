package pedro.costa.neto.tetris;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Pedro Costa
 * @date 21/10/2020
 */
public abstract class Peca {

    protected int largura = 0;
    protected int altura = 0;
    protected int posicaoInicioX = 0;
    protected int posicaoInicioY = 0;
    protected int posicaoFimX = 0;
    protected int posicaoFimY = 0;
    protected boolean estrutura[][];
    protected Image imagem;

    public Peca(Image imagem, int largura, int altura) {
        this.imagem = imagem;
        this.largura = largura;
        this.altura = altura;
        this.estrutura = new boolean[3][3];
    }

    public void renderizarPeca(GraphicsContext graphicsContext, int posicaoInicioX, int posicaoInicioY) {
        this.posicaoInicioX = (posicaoInicioX * this.largura);
        this.posicaoInicioY = (posicaoInicioY * this.altura);

        for (int x = 0; x < this.estrutura.length; x++) {
            for (int y = 0; y < this.estrutura[x].length; y++) {
                if (this.estrutura[x][y]) {
                    graphicsContext.drawImage(
                            this.imagem,
                            this.posicaoInicioX + (x * this.largura),
                            this.posicaoInicioY + (y * this.altura),
                            this.largura,
                            this.altura
                    );
                }

                if (y >= posicaoFimY && this.estrutura[x][y]) {
                    posicaoFimY = y;
                }

                if (x >= posicaoFimX && this.estrutura[x][y]) {
                    posicaoFimX = x;
                }
            }
        }
    }

    public abstract void montarPeca();

    public int getPosicaoFimX() {
        return posicaoFimX;
    }

    public int getPosicaoFimY() {
        return posicaoFimY;
    }

    public List<PecaEstatica> getEstrutura() {
        List<PecaEstatica> pecas = new ArrayList<>();

        for (int x = 0; x < this.estrutura.length; x++) {
            for (int y = 0; y < this.estrutura[x].length; y++) {
                if (this.estrutura[x][y]) {
                    pecas.add(new PecaEstatica(
                            this.imagem, 
                            (this.posicaoInicioX + (x * this.largura)) / this.largura,
                            (this.posicaoInicioY + (y * this.altura)) / this.altura
                    ));
                }
            }
        }

        return pecas;
    }

    public Image getImagem() {
        return imagem;
    }
}
