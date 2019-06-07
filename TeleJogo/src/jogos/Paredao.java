package jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import framework.Bola;
import framework.Jogador;
import framework.Jogo;
import framework.Obstaculo;

public class Paredao extends Jogo{
	
	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    //private int DIREITA = 2;
    //private int ESQUERDA = 3;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo obDireita;
    
    private Jogador jogador;
    
	public Paredao() {
		
		this.definePlanoFundo(Color.PINK);
		this.defineTitulo("Teste");
		
        timer.start();
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(50, Paredao.Largura());
        obCima.definePosicao(0,  0);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(50, Paredao.Largura());
        obBaixo.definePosicao(Paredao.Altura() - 50 - 29, 0);
        
        obDireita = new Obstaculo();
        obDireita.defineTamanho(Paredao.Altura(), 50);
        obDireita.definePosicao(0,  Paredao.Largura() - 50 - 6);
        
        bola = new Bola();
        
        jogador = new Jogador();
        jogador.defineTamanho(100, 20);
        jogador.definePosicao(jogador.CENTRO_Y, 10);
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false};
		
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		bola.move();
        checaColisao();
        if(teclas[CIMA]) jogador.moveCima();
        if(teclas[BAIXO]) jogador.moveBaixo();
        //if(teclas[DIREITA]) jogador.moveDireita();
        //if(teclas[ESQUERDA]) jogador.moveEsquerda();
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		//colisao no jogador
		if(bola.Pos_X() < (jogador.Pos_X() + jogador.Largura())) {
            if((bola.Pos_Y() + bola.Diametro() > jogador.Pos_Y()) && (bola.Pos_Y() < (jogador.Pos_Y() + jogador.Altura())))
                bola.inverteVelX();
            
            else if(bola.Pos_X() < 0){
                bola.definePos_X(Paredao.Largura() / 2);
                bola.definePos_Y(Paredao.Altura() / 2);
                bola.inverteVelX();
            }
        }
		//colisao nos obstaculos
        else{
        	if(bola.Pos_Y() < obCima.Pos_Y() + obCima.Altura() || bola.Pos_Y() + bola.Diametro() > obBaixo.Pos_Y())
        		bola.inverteVelY();
        	if(bola.Pos_X() + bola.Diametro() > obDireita.Pos_X())
                bola.inverteVelX();
        }
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.BLACK);
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.GRAY);
        g.fillRect(obCima.Pos_X(), obCima.Pos_Y(), obCima.Largura(), obCima.Altura());
        g.fillRect(obBaixo.Pos_X(), obBaixo.Pos_Y(), obBaixo.Largura(), obBaixo.Altura());
        g.fillRect(obDireita.Pos_X(), obDireita.Pos_Y(), obDireita.Largura(), obDireita.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
            teclas[CIMA] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	teclas[BAIXO] = true;
        }
//        if(e.getKeyCode() == KeyEvent.VK_LEFT){
//        	teclas[ESQUERDA] = true;
//        }
//        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//        	teclas[DIREITA] = true;
//        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			teclas[CIMA] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	teclas[BAIXO] = false;
        }
//        if(e.getKeyCode() == KeyEvent.VK_LEFT){
//        	teclas[ESQUERDA] = false;
//        }
//        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//        	teclas[DIREITA] = false;
//        }
		
	}
	
}
