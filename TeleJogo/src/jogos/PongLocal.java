package jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import framework.Bola;
import framework.Jogador;
import framework.Jogo;
import framework.Obstaculo;
import framework.Placar;

public class PongLocal extends Jogo{
	
	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA_E = 0;
    private int BAIXO_E = 1;
    private int CIMA_D = 2;
    private int BAIXO_D = 3;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo rede;
    
    private Jogador jogador1;
    private Jogador jogador2;
    
    private int tam;
    private int vel;
	
	public PongLocal(int tamanho, int velocidade) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.definePlanoFundo(Color.BLUE);
		this.defineTitulo("Tenis");
		
        timer.start();
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(50, PongLocal.Largura());
        obCima.definePosicao(0,  0);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(50, PongLocal.Largura());
        obBaixo.definePosicao(PongLocal.Altura() - 50 - 29, 0);
        
        rede = new Obstaculo();
        rede.defineTamanho(PongLocal.Altura(), 50);
        rede.definePosicao(0, PongLocal.Largura()/2 - rede.Largura());
        
        bola = new Bola();
        placar = new Placar();
        
        jogador1 = new Jogador();
        jogador2 = new Jogador();
        
        if(tam == 0) {
        	jogador1.defineTamanho(50, 20);
        	jogador2.defineTamanho(50, 20);
        }
        if(tam == 1) {
        	jogador1.defineTamanho(100, 20);
        	jogador2.defineTamanho(100, 20);
        }
        if(tam == 2) {
        	jogador1.defineTamanho(150, 20);
        	jogador2.defineTamanho(150, 20);
        }
        
        if(vel == 1) bola.defineVel(4);
        if(vel == 2) bola.defineVel(6);
        if(vel == 3) bola.defineVel(11);
        if(vel == 0) bola.defineVel(4); // velocidade inicial
        
        jogador1.definePosicao(jogador1.CENTRO_Y, 50);
        jogador1.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        
        jogador2.definePosicao(jogador2.CENTRO_Y, PongLocal.Largura() - 20 - 50);
        jogador2.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false};
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		bola.move();
        checaColisao();
        if(teclas[CIMA_E]) jogador1.moveCima();
        if(teclas[BAIXO_E]) jogador1.moveBaixo();
        if(teclas[CIMA_D]) jogador2.moveCima();
        if(teclas[BAIXO_D]) jogador2.moveBaixo();
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		//colisao nos jogadores
		if(bola.Pos_X() < (jogador1.Pos_X() + jogador1.Largura()) && bola.Pos_X() + bola.Diametro() > jogador1.Pos_X()) {
            if((bola.Pos_Y() + bola.Diametro() > jogador1.Pos_Y()) && (bola.Pos_Y() < (jogador1.Pos_Y() + jogador1.Altura()))) {
                bola.inverteVelX();
                if(vel == 0) bola.aumentaAcel(0.2);
            }
            
        }
		if(bola.Pos_X() < jogador2.Pos_X() + jogador2.Largura() && bola.Pos_X() + bola.Diametro() > jogador2.Pos_X()) {
            if((bola.Pos_Y() + bola.Diametro() > jogador2.Pos_Y()) && (bola.Pos_Y() < (jogador2.Pos_Y() + jogador2.Altura()))) {
                bola.inverteVelX();
            }
            
        }
        else{
        	//colisao nos obstaculos
        	if(bola.Pos_Y() < obCima.Pos_Y() + obCima.Altura() || bola.Pos_Y() + bola.Diametro() > obBaixo.Pos_Y())
        		bola.inverteVelY();
        	
        	//bola fora da tela
        	if(bola.Pos_X() < 0){
                bola.definePos_X(PongLocal.Largura() / 2);
                bola.definePos_Y(PongLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
                placar.aumentaDir();
            }
        	
        	else if(bola.Pos_X() > PongLocal.Largura()){
                bola.definePos_X(PongLocal.Largura() / 2);
                bola.definePos_Y(PongLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
                placar.aumentaEsq();
            }
        }
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.BLACK);
        g.fillRect(jogador1.Pos_X(), jogador1.Pos_Y(), jogador1.Largura(), jogador1.Altura());
        g.fillRect(jogador2.Pos_X(), jogador2.Pos_Y(), jogador2.Largura(), jogador2.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.GRAY);
        g.fillRect(obCima.Pos_X(), obCima.Pos_Y(), obCima.Largura(), obCima.Altura());
        g.fillRect(obBaixo.Pos_X(), obBaixo.Pos_Y(), obBaixo.Largura(), obBaixo.Altura());
        g.fillRect(rede.Pos_X(), rede.Pos_Y(), rede.Largura(), rede.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
            teclas[CIMA_D] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	teclas[BAIXO_D] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
        	teclas[CIMA_E] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
        	teclas[BAIXO_E] = true;
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			teclas[CIMA_D] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
        	teclas[BAIXO_D] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
        	teclas[CIMA_E] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
        	teclas[BAIXO_E] = false;
        }
		
	}
	
}
