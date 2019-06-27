package jogosTenis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
    
    private static Socket                clientSoc  = null;
	private static ServerSocket          serverSoc  = null;
    
    private int tam;
    private int vel;
    
    String ip;
    
    private String tipoConexao;
	
	public TenisRede(int tamanho, int velocidade, String ip, String conexao) {
		
		tam = tamanho;
		vel = velocidade;
		
		this.ip = ip;
		
		tipoConexao = conexao;
		
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
        
        
        jogador.definePosicao(jogador.CENTRO_Y, 50);
        
        oponente.definePosicao(oponente.CENTRO_Y, 500);
        
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
		
	}
	
	public void hospedaConexao() {
		try {
   		 	serverSoc = new ServerSocket(5000);
        	 System.out.println("Server has started to running.\nWaiting for a player...");
        	 System.out.println("Waiting for connection...");
        	 clientSoc = serverSoc.accept();
   			 
        	 System.out.println("Connected a player...");
        	
        	 if(clientSoc.isConnected()){ // - If connected a player start to loop - //
        		 
            		 // - Creating Streams - //
        			ObjectInputStream getObj = new ObjectInputStream(clientSoc.getInputStream());
					oponente = (Jogador) getObj.readObject();
					getObj = null;
					
					// - Send Object to Client - //
					ObjectOutputStream sendObj = new ObjectOutputStream(clientSoc.getOutputStream());
                 	sendObj.writeObject(jogador);
                 	sendObj = null;
                 	
                 	if(pausa == false) bola.move();
                    checaColisao();
                    checaBolaFora();
                    if(teclas[CIMA]) jogador.moveCima();
                    if(teclas[BAIXO]) jogador.moveBaixo();
                    if(teclas[ESPACO]) pausa = false;
                    repaint();
                 
        	}
        	 else{
        		 System.out.println("Disconnected...");
        	 }
        }
        catch (Exception e) {System.out.println(e);}
	}
	
	public void acessaConexao() {
		try {
			 
       	 System.out.println("Finding server...\nConnecting to "+ ip);
       	 clientSoc = new Socket(ip, 5000);
   		 System.out.println("Connected to server...");
           
       	 if(clientSoc.isConnected()){
           	System.out.println("TEST");
           		// - Creating Streams - //
           		 ObjectOutputStream sendObj = new ObjectOutputStream(clientSoc.getOutputStream());
       			 sendObj.writeObject(jogador);
       			 sendObj = null;
       			 
       			 ObjectInputStream getObj = new ObjectInputStream(clientSoc.getInputStream());
       			 oponente = (Jogador) getObj.readObject();
       			 getObj = null;
       			 
       			if(pausa == false) bola.move();
       	        checaColisao();
       	        checaBolaFora();
       	        if(teclas[CIMA]) jogador.moveCima();
       	        if(teclas[BAIXO]) jogador.moveBaixo();
       	        if(teclas[ESPACO]) pausa = false;
       	        repaint();
       			 
         }
       	 else{
       		 System.out.println("Disconnected...");
       	 }
           

       }
       catch (Exception e) {
    	   System.err.println("Erro ao conectar no servidor");
       }
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(tipoConexao == "host") hospedaConexao();
		else acessaConexao();
		
	}
	
	@Override
	public void checaColisao() {
		
		if (jogador.colide(bola)) {
			if(vel == 0 && bola.Acel()*bola.Vel_X() < 10) bola.aumentaAcel(0.5);
		}
		
		if (oponente.colide(bola)) {
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
        g.fillRect(oponente.Pos_X(), oponente.Pos_Y(), oponente.Largura(), oponente.Altura());
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
