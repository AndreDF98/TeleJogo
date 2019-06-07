package jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import framework.Bola;
import framework.Computador;
import framework.Jogador;
import framework.Jogo;
import framework.Obstaculo;

public class PongTreino extends Jogo {

	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo rede;
    
    private Jogador jogador;
    private Computador computador;
	
	public PongTreino() {
		
		this.definePlanoFundo(Color.BLUE);
		this.defineTitulo("Teste2");
		
        timer.start();
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(50, PongTreino.Largura());
        obCima.definePosicao(0,  0);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(50, PongTreino.Largura());
        obBaixo.definePosicao(PongTreino.Altura() - 50 - 29, 0);
        
        bola = new Bola();
        
        jogador = new Jogador();
        jogador.defineTamanho(100, 20);
        jogador.definePosicao(jogador.CENTRO_Y, 10);
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        computador = new Computador();
        computador.defineTamanho(100, 20);
        computador.definePosicao(computador.CENTRO_Y, PongTreino.Largura() - 20 - 17);
        computador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false};
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		bola.move();
		computador.moveVertical(bola);
        checaColisao();
        if(teclas[CIMA]) jogador.moveCima();
        if(teclas[BAIXO]) jogador.moveBaixo();
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		//colisao nos jogadores
		if(bola.Pos_X() < (jogador.Pos_X() + jogador.Largura())) {
            if((bola.Pos_Y() + bola.Diametro() > jogador.Pos_Y()) && (bola.Pos_Y() < (jogador.Pos_Y() + jogador.Altura())))
                bola.inverteVelX();
            
            else if(bola.Pos_X() < 0){
                bola.definePos_X(PongTreino.Largura() / 2);
                bola.definePos_Y(PongTreino.Altura() / 2);
            }
        }
		if(bola.Pos_X() > (computador.Pos_X() - computador.Largura())) {
            if((bola.Pos_Y() + bola.Diametro() > computador.Pos_Y()) && (bola.Pos_Y() < (computador.Pos_Y() + computador.Altura())))
                bola.inverteVelX();
            
            else if(bola.Pos_X() > PongTreino.Largura()){
                bola.definePos_X(PongTreino.Largura() / 2);
                bola.definePos_Y(PongTreino.Altura() / 2);
            }
        }
		//colisao nos obstaculos
        else{
        	if(bola.Pos_Y() < obCima.Pos_Y() + obCima.Altura() || bola.Pos_Y() + bola.Diametro() > obBaixo.Pos_Y())
        		bola.inverteVelY();
        }
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.BLACK);
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        g.fillRect(computador.Pos_X(), computador.Pos_Y(), computador.Largura(), computador.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.GRAY);
        g.fillRect(obCima.Pos_X(), obCima.Pos_Y(), obCima.Largura(), obCima.Altura());
        g.fillRect(obBaixo.Pos_X(), obBaixo.Pos_Y(), obBaixo.Largura(), obBaixo.Altura());
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
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			teclas[CIMA] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	teclas[BAIXO] = false;
        }
		
	}
	
}
