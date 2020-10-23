/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.costa.neto.tetris;

import javafx.scene.image.Image;

/**
 *
 * @author pedro
 */
public class Peca01 extends Peca{

    public Peca01(Image imagem, int largura, int altura) {
        super(imagem, largura, altura);
        montarPeca();
    }
    
    @Override
    public void montarPeca() {
        
        //     , TRUE,     
        // TRUE, TRUE, TRUE
        
        this.estrutura[0][0] = false;
        this.estrutura[1][0] = true;
        this.estrutura[2][0] = false;
        
        this.estrutura[0][1] = true;
        this.estrutura[1][1] = true;
        this.estrutura[2][1] = true;
        
        this.estrutura[0][2] = false;
        this.estrutura[1][2] = false;
        this.estrutura[2][2] = false;
    }
    
}
