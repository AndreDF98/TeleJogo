package jogoParedao;

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
import framework.Placar;

public class Paredao extends Jogo{
	
	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    private int ESPACO = 2;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo obDireita;
    
    private Jogador jogador;
    
    private int tam;
    private int vel;
    
	public Paredao(int tamanho, int velocidade) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.defineTitulo("Paredão");
		
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
        placar = new Placar();
        
        jogador = new Jogador();
        
        if(tam == 0) jogador.defineTamanho(50, 20);
        if(tam == 1) jogador.defineTamanho(100, 20);
        if(tam == 2) jogador.defineTamanho(150, 20);
        
        if(vel == 1) bola.defineVel(3);
        if(vel == 2) bola.defineVel(4);
        if(vel == 3) bola.defineVel(8);
        if(vel == 0) bola.defineVel(2); // velocidade inicial
        
        jogador.definePosicao(jogador.CENTRO_Y, 50);
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y() - 1);
        
        teclas = new boolean[]{false,false,false,false,false};
        
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(pausa == false) bola.move();
        checaColisao();
        checaBolaFora();
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
			placar.aumentaEsq();
		}
		
		if (jogador.colideCima(bola) || jogador.colideBaixo(bola)) bola.inverteVelY();
		
		if (obCima.colideBaixo(bola)) bola.inverteVelY();
		
		if (obBaixo.colideCima(bola)) bola.inverteVelY();
		
		if (obDireita.colideEsquerda(bola)) bola.inverteVelX();
        
	}
	
	@Override
	public void checaBolaFora() {
		if(bola.Pos_X() + bola.Diametro() < 0){
            bola.definePos_X(Paredao.Largura() / 2);
            bola.definePos_Y(Paredao.Altura() / 2);
            bola.inverteVelX();
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaDir();
            pausa = true;
		}
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(Color.WHITE);
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
