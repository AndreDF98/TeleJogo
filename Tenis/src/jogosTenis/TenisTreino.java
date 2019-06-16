package jogosTenis;

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
import framework.Placar;

public class TenisTreino extends Jogo {

	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    private int ESPACO = 2;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo rede;
    
    private Jogador jogador;
    private Computador computador;
    
    private int tam;
    private int vel;
	
	public TenisTreino(int tamanho, int velocidade) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.defineTitulo("Tênis");
		
        timer.start();
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(50, TenisTreino.Largura());
        obCima.definePosicao(0,  0);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(50, TenisTreino.Largura());
        obBaixo.definePosicao(TenisTreino.Altura() - 50 - 29, 0);
        
        rede = new Obstaculo();
        rede.defineTamanho(TenisLocal.Altura(), 50);
        rede.definePosicao(0, TenisLocal.Largura()/2 - rede.Largura()/2);
        
        bola = new Bola();
        placar = new Placar();
        
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
        
        if(vel == 1) bola.defineVel(3);
        if(vel == 2) bola.defineVel(4);
        if(vel == 3) bola.defineVel(8);
        if(vel == 0) bola.defineVel(2); // velocidade inicial
        
        jogador.definePosicao(jogador.CENTRO_Y, 50);
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        computador.definePosicao(computador.CENTRO_Y, TenisTreino.Largura() - 20 - 50);
        computador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false,false};
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(pausa == false) bola.move();
		computador.moveVertical(bola);
        checaColisao();
        if(teclas[CIMA]) jogador.moveCima();
        if(teclas[BAIXO]) jogador.moveBaixo();
        if(teclas[ESPACO]) pausa = false;
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		//colisao nos jogadores
		if(bola.Pos_X() < (jogador.Pos_X() + jogador.Largura()) && bola.Pos_X() + bola.Diametro() > jogador.Pos_X() + jogador.Largura()) {
            if((bola.Pos_Y() + bola.Diametro() > jogador.Pos_Y()) && (bola.Pos_Y() < (jogador.Pos_Y() + jogador.Altura()))) {
                bola.inverteVelX();
                if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
            }
            
        }
		if(bola.Pos_X() < computador.Pos_X() && bola.Pos_X() + bola.Diametro() > computador.Pos_X()) {
            if((bola.Pos_Y() + bola.Diametro() > computador.Pos_Y()) && (bola.Pos_Y() < (computador.Pos_Y() + computador.Altura()))) {
                bola.inverteVelX();
            }
            
        }
		//colisao nos obstaculos
        else{
        	if(bola.Pos_Y() < obCima.Pos_Y() + obCima.Altura() || bola.Pos_Y() + bola.Diametro() > obBaixo.Pos_Y())
        		bola.inverteVelY();
        	
        	if(bola.Pos_X() < 0){
                bola.definePos_X(TenisLocal.Largura() / 2);
                bola.definePos_Y(TenisLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
                placar.aumentaDir();
                pausa = true;
            }
        	
        	else if(bola.Pos_X() > TenisLocal.Largura()){
                bola.definePos_X(TenisLocal.Largura() / 2);
                bola.definePos_Y(TenisLocal.Altura() / 2);
                if(vel == 0) bola.defineAcel(1.0);
                placar.aumentaEsq();
                pausa = true;
            }
        }
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        g.fillRect(computador.Pos_X(), computador.Pos_Y(), computador.Largura(), computador.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(obCima.Pos_X(), obCima.Pos_Y(), obCima.Largura(), obCima.Altura());
        g.fillRect(obBaixo.Pos_X(), obBaixo.Pos_Y(), obBaixo.Largura(), obBaixo.Altura());
        g.fillRect(rede.Pos_X(), rede.Pos_Y(), rede.Largura(), rede.Altura());
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	teclas[ESPACO] = true;
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	teclas[ESPACO] = false;
        }
		
	}
	
}
