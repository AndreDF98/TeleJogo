package jogosFut;

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

public class FutLocal extends Jogo{
	
	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA_E = 0;
    private int BAIXO_E = 1;
    private int CIMA_D = 2;
    private int BAIXO_D = 3;
    private int ESPACO = 4;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo traveCimaEsq;
    private Obstaculo traveCimaDir;
    private Obstaculo traveBaixoEsq;
    private Obstaculo traveBaixoDir;
    
    private Jogador jogador1;
    private Jogador jogador2;
    
    private int tam;
    private int vel;
	
	public FutLocal(int tamanho, int velocidade) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.defineTitulo("Futebol");
		
        timer.start();
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(20, FutLocal.Largura() - 30);
        obCima.definePosicao(10,  10);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(20, FutLocal.Largura() - 30);
        obBaixo.definePosicao(FutLocal.Altura() - 20 - 39, 10);
        
        traveCimaEsq = new Obstaculo();
        traveCimaEsq.defineTamanho(290, 20);
        traveCimaEsq.definePosicao(10, 10);
        
        traveCimaDir = new Obstaculo();
        traveCimaDir.defineTamanho(290, 20);
        traveCimaDir.definePosicao(10, FutLocal.Largura() - 30 - 6);
        
        traveBaixoEsq = new Obstaculo();
        traveBaixoEsq.defineTamanho(300 - 39, 20);
        traveBaixoEsq.definePosicao(500, 10);
        
        traveBaixoDir = new Obstaculo();
        traveBaixoDir.defineTamanho(300 - 39, 20);
        traveBaixoDir.definePosicao(500, FutLocal.Largura() - 30 - 6);
        
        bola = new Bola();
        placar = new Placar();
        
        System.out.println("" + FutLocal.Altura());
        
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
        
        if(vel == 1) bola.defineVel(3);
        if(vel == 2) bola.defineVel(4);
        if(vel == 3) bola.defineVel(8);
        if(vel == 0) bola.defineVel(2); // velocidade inicial
        
        jogador1.definePosicao(jogador1.CENTRO_Y, 50);
        jogador1.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        
        jogador2.definePosicao(jogador2.CENTRO_Y, FutLocal.Largura() - 20 - 50);
        jogador2.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false,false};
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(pausa == false) { bola.move(); }
        checaColisao();
        if(teclas[CIMA_E]) jogador1.moveCima();
        if(teclas[BAIXO_E]) jogador1.moveBaixo();
        if(teclas[CIMA_D]) jogador2.moveCima();
        if(teclas[BAIXO_D]) jogador2.moveBaixo();
        if(teclas[ESPACO]) pausa = false;
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		
		if (jogador1.colideDireita(bola)) {
			bola.inverteVelX();
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (jogador1.colideCima(bola) || jogador1.colideBaixo(bola)) bola.inverteVelY();
		
		if (jogador2.colideEsquerda(bola)) {
			bola.inverteVelX();
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (jogador2.colideCima(bola) || jogador2.colideBaixo(bola)) bola.inverteVelY();
		
		if (obCima.colideBaixo(bola) || obBaixo.colideCima(bola)) bola.inverteVelY();
		
		if (traveCimaEsq.colideDireita(bola) || traveBaixoEsq.colideDireita(bola)) bola.inverteVelX();
		
		if (traveCimaDir.colideEsquerda(bola) || traveBaixoDir.colideEsquerda(bola)) bola.inverteVelX();
		
		if (traveCimaEsq.colideBaixo(bola) || traveBaixoEsq.colideCima(bola) || traveCimaDir.colideBaixo(bola) || traveBaixoDir.colideCima(bola)) bola.inverteVelY();
		
        //bola fora da tela
       	if(bola.Pos_X() < 0){
       		bola.definePos_X(FutLocal.Largura() / 2);
            bola.definePos_Y(FutLocal.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaDir();
            pausa = true;
        }
        	
       	else if(bola.Pos_X() > FutLocal.Largura()){
       		bola.definePos_X(FutLocal.Largura() / 2);
            bola.definePos_Y(FutLocal.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaEsq();
            pausa = true;
        }
       	
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(jogador1.Pos_X(), jogador1.Pos_Y(), jogador1.Largura(), jogador1.Altura());
        g.fillRect(jogador2.Pos_X(), jogador2.Pos_Y(), jogador2.Largura(), jogador2.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(obCima.Pos_X(), obCima.Pos_Y(), obCima.Largura(), obCima.Altura());
        g.fillRect(obBaixo.Pos_X(), obBaixo.Pos_Y(), obBaixo.Largura(), obBaixo.Altura());
        g.fillRect(traveCimaEsq.Pos_X(), traveCimaEsq.Pos_Y(), traveCimaEsq.Largura(), traveCimaEsq.Altura());
        g.fillRect(traveCimaDir.Pos_X(), traveCimaDir.Pos_Y(), traveCimaDir.Largura(), traveCimaDir.Altura());
        g.fillRect(traveBaixoEsq.Pos_X(), traveBaixoEsq.Pos_Y(), traveBaixoEsq.Largura(), traveBaixoEsq.Altura());
        g.fillRect(traveBaixoDir.Pos_X(), traveBaixoDir.Pos_Y(), traveBaixoDir.Largura(), traveBaixoDir.Altura());
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	teclas[ESPACO] = true;
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
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	teclas[ESPACO] = false;
        }
		
	}
	
}
