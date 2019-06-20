package jogosFut;

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

public class FutTreino extends Jogo {

	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    private int ESPACO = 2;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo traveCimaEsq;
    private Obstaculo traveCimaDir;
    private Obstaculo traveBaixoEsq;
    private Obstaculo traveBaixoDir;
    
    private Jogador jogador;
    private Computador computador;
    
    private int tam;
    private int vel;
	
	public FutTreino(int tamanho, int velocidade) {
		
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
        
        computador.definePosicao(computador.CENTRO_Y, FutTreino.Largura() - 20 - 50);
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
		
		if (jogador.colideDireita(bola)) {
			bola.inverteVelX();
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (jogador.colideCima(bola) || jogador.colideBaixo(bola)) bola.inverteVelY();
		
		if (computador.colideEsquerda(bola)) {
			bola.inverteVelX();
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (computador.colideCima(bola) || computador.colideBaixo(bola)) bola.inverteVelY();
		
		if (obCima.colideBaixo(bola) || obBaixo.colideCima(bola)) bola.inverteVelY();
		
		if (traveCimaEsq.colideDireita(bola) || traveBaixoEsq.colideDireita(bola)) bola.inverteVelX();
		
		if (traveCimaDir.colideEsquerda(bola) || traveBaixoDir.colideEsquerda(bola)) bola.inverteVelX();
		
		if (traveCimaEsq.colideBaixo(bola) || traveBaixoEsq.colideCima(bola) || traveCimaDir.colideBaixo(bola) || traveBaixoDir.colideCima(bola)) bola.inverteVelY();
		
		//bola fora da tela
        if(bola.Pos_X() < 0){
        	bola.definePos_X(FutTreino.Largura() / 2);
            bola.definePos_Y(FutTreino.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaDir();
            pausa = true;
        }
        	
       	else if(bola.Pos_X() > FutTreino.Largura()){
       		bola.definePos_X(FutTreino.Largura() / 2);
            bola.definePos_Y(FutTreino.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaEsq();
            pausa = true;
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
        g.fillRect(traveCimaEsq.Pos_X(), traveCimaEsq.Pos_Y(), traveCimaEsq.Largura(), traveCimaEsq.Altura());
        g.fillRect(traveCimaDir.Pos_X(), traveCimaDir.Pos_Y(), traveCimaDir.Largura(), traveCimaDir.Altura());
        g.fillRect(traveBaixoEsq.Pos_X(), traveBaixoEsq.Pos_Y(), traveBaixoEsq.Largura(), traveBaixoEsq.Altura());
        g.fillRect(traveBaixoDir.Pos_X(), traveBaixoDir.Pos_Y(), traveBaixoDir.Largura(), traveBaixoDir.Altura());
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
