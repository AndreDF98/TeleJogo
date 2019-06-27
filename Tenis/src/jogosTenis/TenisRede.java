package jogosTenis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import framework.Bola;
import framework.Computador;
import framework.Jogador;
import framework.Jogo;
import framework.Obstaculo;
import framework.Placar;

public class TenisRede extends Jogo {

	private boolean teclas[]; //Esse vetor guarda quais teclas estão sendo tecladas
    private int CIMA = 0;
    private int BAIXO = 1;
    private int ESPACO = 2;
    
    private Obstaculo obCima;
    private Obstaculo obBaixo;
    private Obstaculo rede;
    
    private Jogador jogador;
    private Jogador oponente;
    
    DatagramSocket socket;
    
    private int tam;
    private int vel;
    
    InetAddress ip;
	
	public TenisRede(int tamanho, int velocidade, InetAddress ip, String conexao) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.ip = ip;
		
		this.defineTitulo("Tênis");
		
        timer.start();
        
        teclas = new boolean[]{false,false,false,false,false};
        
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
        oponente = new Jogador();
        
        if(tam == 0) {
        	jogador.defineTamanho(50, 20);
        }
        if(tam == 1) {
        	jogador.defineTamanho(100, 20);
        }
        if(tam == 2) {
        	jogador.defineTamanho(150, 20);
        }
        
        if(vel == 1) bola.defineVel(3);
        if(vel == 2) bola.defineVel(4);
        if(vel == 3) bola.defineVel(8);
        if(vel == 0) bola.defineVel(2); // velocidade inicial
        
        if(conexao == "host")
        	jogador.definePosicao(jogador.CENTRO_Y, 50);
        
        if(conexao == "guest")
        	jogador.definePosicao(jogador.CENTRO_Y, TenisLocal.Largura() - 20 - 50);
        
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        
        try {
			socket = new DatagramSocket();
		}
		catch(SocketException e) {
			System.err.println("Erro na criação do socket");
		}
        
        try {
        	
        	byte dados[] = (jogador.Pos_X() + "-" +jogador.Pos_Y() + "-" + jogador.Largura() + "-" + jogador.Altura()).getBytes();
        	
        	DatagramPacket pacoteEnviado = new DatagramPacket(dados, dados.length, ip, 5000);
        	
        	socket.send(pacoteEnviado);
        	
        }
        catch(IOException e) {
        	System.err.println("Erro na criação ou envio de pacote");
        }
		
	}
	
	public void esperaPacotes() {
		while(true) {
			try {
				byte data[] = new byte[100];
				DatagramPacket pacoteRecebido = new DatagramPacket(data, data.length);
				socket.receive(pacoteRecebido);
			}
			catch(IOException e) {
				System.err.println("Erro ao receber pacote");
			}
		}
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
		
		if (jogador.colide(bola)) {
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		obCima.colide(bola); obBaixo.colide(bola);
        	
	}
	
	@Override
	public void checaBolaFora() {
		if(bola.Pos_X() < 0){
       		bola.definePos_X(TenisTreino.Largura() / 2);
            bola.definePos_Y(TenisTreino.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaDir();
            pausa = true;
        }
        	
       	else if(bola.Pos_X() > TenisTreino.Largura()){
       		bola.definePos_X(TenisTreino.Largura() / 2);
            bola.definePos_Y(TenisTreino.Altura() / 2);
            if(vel == 0) bola.defineAcel(1.0);
            placar.aumentaEsq();
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
