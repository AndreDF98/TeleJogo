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
    
    private int tam;
    private int vel;
	
	public PongTreino(int tamanho, int velocidade) {
		
		tam = tamanho;
		vel = velocidade;
		
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
        computador = new Computador();
        
        if(tam == 0) {
        	jogador.defineTamanho(50, 20);
        	computador.defineTamanho(50, 20);
        }
        if(tam == 1) {
        	jogador.defineTamanho(100, 20);
        	computador.defineTamanho(100, 20);
        }
        if(tam == 2) {
        	jogador.defineTamanho(150, 20);
        	computador.defineTamanho(150, 20);
        }
        
        if(vel == 1) bola.defineVel(4);
        if(vel == 2) bola.defineVel(6);
        if(vel == 3) bola.defineVel(11);
        if(vel == 0) bola.defineVel(4); // velocidade inicial
        
        jogador.definePosicao(jogador.CENTRO_Y, 50);
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        computador.definePosicao(computador.CENTRO_Y, PongTreino.Largura() - 20 - 50);
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
		if(bola.Pos_X() < (jogador.Pos_X() + jogador.Largura()) && bola.Pos_X() + bola.Diametro() > jogador.Pos_X()) {
            if((bola.Pos_Y() + bola.Diametro() > jogador.Pos_Y()) && (bola.Pos_Y() < (jogador.Pos_Y() + jogador.Altura()))) {
                bola.inverteVelX();
                if(vel == 0) bola.aumentaAcel(0.2);
            }
            
        }
		if(bola.Pos_X() < computador.Pos_X() + computador.Largura() && bola.Pos_X() + bola.Diametro() > computador.Pos_X()) {
            if((bola.Pos_Y() + bola.Diametro() > computador.Pos_Y()) && (bola.Pos_Y() < (computador.Pos_Y() + computador.Altura()))) {
                bola.inverteVelX();
            }
            
        }
		//colisao nos obstaculos
        else{
        	if(bola.Pos_Y() < obCima.Pos_Y() + obCima.Altura() || bola.Pos_Y() + bola.Diametro() > obBaixo.Pos_Y())
        		bola.inverteVelY();
        	
        	if(bola.Pos_X() < 0){
                bola.definePos_X(PongLocal.Largura() / 2);
                bola.definePos_Y(PongLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
            }
        	
        	else if(bola.Pos_X() > PongLocal.Largura()){
                bola.definePos_X(PongLocal.Largura() / 2);
                bola.definePos_Y(PongLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
            }
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
