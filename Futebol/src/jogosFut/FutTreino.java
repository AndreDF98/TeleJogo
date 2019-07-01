package jogosFut;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import framework.Bola;
import framework.Computador;
import framework.ConfigGrafica;
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
    
    private Computador[] jogadoresEsq;
    private Computador[] jogadoresDir;
    
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
    	obCima.defineTamanho(20, this.Largura() - 30);
        obCima.definePosicao(10,  10);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(20, this.Largura() - 30);
        obBaixo.definePosicao(this.Altura() - 20 - 39, 10);
      
        traveCimaEsq = new Obstaculo();
        traveCimaEsq.defineTamanho(290, 20);
        traveCimaEsq.definePosicao(10, 10);
        
        traveCimaDir = new Obstaculo();
        traveCimaDir.defineTamanho(290, 20);
        traveCimaDir.definePosicao(10, this.Largura() - 30 - 6);
        
        traveBaixoEsq = new Obstaculo();
        traveBaixoEsq.defineTamanho(300 - 39, 20);
        traveBaixoEsq.definePosicao(500, 10);
        
        traveBaixoDir = new Obstaculo();
        traveBaixoDir.defineTamanho(300 - 39, 20);
        traveBaixoDir.definePosicao(500, this.Largura() - 30 - 6);
        
        bola = new Bola();
        placar = new Placar();
        
        jogador = new Jogador();
        computador = new Computador();
        
        jogadoresEsq = new Computador[3];
        jogadoresDir = new Computador[3];
        
        int distancia = 0;
        for(int i = 0; i < 3; i++) {
        	jogadoresEsq[i] = new Computador();
        	jogadoresEsq[i].defineTamanho(30, 20);
        	jogadoresEsq[i].definePosicao(50 + distancia, this.Largura()/2 - 320 - 20);
        	jogadoresEsq[i].defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        	jogadoresEsq[i].defineVel(3);
        	jogadoresDir[i] = new Computador();
        	jogadoresDir[i].defineTamanho(30, 20);
        	jogadoresDir[i].definePosicao(180 + distancia, this.Largura()/2 + 320);
        	jogadoresDir[i].defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        	jogadoresDir[i].defineVel(3);
        	
        	distancia += 250;
        }
        
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
        
        computador.definePosicao(computador.CENTRO_Y, this.Largura() - 20 - 50);
        computador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        teclas = new boolean[]{false,false,false,false,false};
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(pausa == false) bola.move();
		computador.segueVertical(bola);
        checaColisao();
        checaBolaFora();
        moveJogadores();
        if(teclas[CIMA]) jogador.moveCima();
        if(teclas[BAIXO]) jogador.moveBaixo();
        if(teclas[ESPACO]) pausa = false;
        repaint();
		
	}
	
	@Override
	public void checaColisao() {
		
		if (jogador.colide(bola)) {
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (computador.colide(bola)) {
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		obCima.colide(bola); obBaixo.colide(bola);
		traveCimaEsq.colide(bola); traveBaixoEsq.colide(bola);
		traveCimaDir.colide(bola); traveBaixoDir.colide(bola);
		
		for(int i = 0; i < 3; i++) {
			jogadoresEsq[i].colide(bola);
			jogadoresDir[i].colide(bola);
		}
        	
	}
	
	@Override
	public void checaBolaFora() {
		if(bola.Pos_X() < 0){
       		bola.definePos_X(this.Largura() / 2);
            bola.definePos_Y(this.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaDir();
            pausa = true;
        }
        	
       	else if(bola.Pos_X() > this.Largura()){
       		bola.definePos_X(this.Largura() / 2);
            bola.definePos_Y(this.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaEsq();
            pausa = true;
       	}
	}
	
	public void moveJogadores() {
		for(int i = 0; i < 3; i++) {
			jogadoresEsq[i].moveVertical();
			jogadoresDir[i].moveVertical();
		}
		
		if(jogadoresEsq[2].Pos_Y() + jogadoresEsq[2].Altura() > obBaixo.Pos_Y() || jogadoresEsq[0].Pos_Y() < obCima.Altura()) {
			jogadoresEsq[0].inverteVel();
			jogadoresEsq[1].inverteVel();
			jogadoresEsq[2].inverteVel();
		}
		if(jogadoresDir[2].Pos_Y() + jogadoresDir[2].Altura() > obBaixo.Pos_Y() || jogadoresDir[0].Pos_Y() < obCima.Altura()) {
			jogadoresDir[0].inverteVel();
			jogadoresDir[1].inverteVel();
			jogadoresDir[2].inverteVel();
		}
	}

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(config.corJogador());
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        g.fillRect(computador.Pos_X(), computador.Pos_Y(), computador.Largura(), computador.Altura());
        
        for(int i = 0; i < 3; i++) {
        	g.fillRect(jogadoresEsq[i].Pos_X(), jogadoresEsq[i].Pos_Y(), jogadoresEsq[i].Largura(), jogadoresEsq[i].Altura());
        	g.fillRect(jogadoresDir[i].Pos_X(), jogadoresDir[i].Pos_Y(), jogadoresDir[i].Largura(), jogadoresDir[i].Altura());
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(config.corObstaculo());
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
