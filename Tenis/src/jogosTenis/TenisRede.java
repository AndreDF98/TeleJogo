package jogosTenis;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import framework.Bola;
import framework.ConfigGrafica;
import framework.Jogador;
import framework.Jogo;
import framework.Obstaculo;
import framework.Placar;

public class TenisRede extends Jogo implements Runnable{

	private boolean teclas[]; //Esse vetor guarda quais teclas est�o sendo tecladas
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
		
		this.Janela().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tam = tamanho;
		vel = velocidade;
		
		this.ip = ip;
		
		tipoConexao = conexao;
		
		this.defineTitulo("T�nis");
		
        timer.start();
        
        teclas = new boolean[]{false,false,false,false,false};
        
        obCima = new Obstaculo();
    	obCima.defineTamanho(50, this.Largura());
        obCima.definePosicao(0,  0);
        
        obBaixo = new Obstaculo();
        obBaixo.defineTamanho(50, this.Largura());
        obBaixo.definePosicao(this.Altura() - 50 - 29, 0);
        
        rede = new Obstaculo();
        rede.defineTamanho(this.Altura(), 50);
        rede.definePosicao(0, this.Largura()/2 - rede.Largura()/2);
        
        bola = new Bola();
        placar = new Placar();
        
        jogador = new Jogador();
        oponente = new Jogador();
        
        if(tam == 0) {
        	jogador.defineTamanho(50, 20);
        	oponente.defineTamanho(50, 20);
        }
        if(tam == 1) {
        	jogador.defineTamanho(100, 20);
        	oponente.defineTamanho(100, 20);
        }
        if(tam == 2) {
        	jogador.defineTamanho(150, 20);
        	oponente.defineTamanho(150, 20);
        }
        
        if(vel == 1) bola.defineVel(3);
        if(vel == 2) bola.defineVel(4);
        if(vel == 3) bola.defineVel(8);
        if(vel == 0) bola.defineVel(2); // velocidade inicial
        
        if(tipoConexao == "host") {
        	jogador.definePosicao(jogador.CENTRO_Y, 50);
            oponente.definePosicao(oponente.CENTRO_Y, this.Largura() - 20 - 50);
        }
        else {
        	oponente.definePosicao(oponente.CENTRO_Y, 50);
            jogador.definePosicao(jogador.CENTRO_Y, this.Largura() - 20 - 50);
        }
        
        jogador.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
        oponente.defineLimitesVert(obCima.Altura(), obBaixo.Pos_Y());
		
	}
	
	public void hospedaConexao() {
		try {
   		 	serverSoc = new ServerSocket(5000);
        	 System.out.println("Server has started to running.\nWaiting for a player...");
        	 System.out.println("Waiting for connection...");
        	 clientSoc = serverSoc.accept();
   			 
        	 System.out.println("Connected a player...");
        	
        	 if(clientSoc.isConnected()){ // - If connected a player start to loop - //
        		 while(true){
        		 
	        		Jogador aux;
	        		 
	            	 // - Creating Streams - //
	        		ObjectInputStream getObj = new ObjectInputStream(clientSoc.getInputStream());
					aux = (Jogador) getObj.readObject();
					getObj = null;
					
					oponente.definePosicao(aux.Pos_Y(), this.Largura() - 20 - 50);
								
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
       		while(true){
           	System.out.println("TEST");
           		// - Creating Streams - //
           		 ObjectOutputStream sendObj = new ObjectOutputStream(clientSoc.getOutputStream());
       			 sendObj.writeObject(jogador);
       			 sendObj = null;
       			 
       			 ObjectInputStream getObj = new ObjectInputStream(clientSoc.getInputStream());
       			 Jogador aux = (Jogador) getObj.readObject();
       			 getObj = null;
       			 
       			oponente.definePosicao(aux.Pos_Y(), this.Largura() - 20 - 50);
       			
       			if(pausa == false) bola.move();
       		    checaColisao();
       		    checaBolaFora();
       		    if(teclas[CIMA]) jogador.moveCima();
       		    if(teclas[BAIXO]) jogador.moveBaixo();
       		    if(teclas[ESPACO]) pausa = false;
       		    repaint();
       			
       		}
         }
       	 else{
       		 System.out.println("Disconnected...");
       	 }
           

       }
       catch (Exception e) {
    	   System.err.println("Erro ao conectar no servidor");
       }
	}
	
	public void run() {
		if(tipoConexao == "host") hospedaConexao();
		else acessaConexao();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
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

    @Override
	public void desenhaJogador(Graphics g) {
    	g.setColor(config.corJogador());
        g.fillRect(jogador.Pos_X(), jogador.Pos_Y(), jogador.Largura(), jogador.Altura());
        g.fillRect(oponente.Pos_X(), oponente.Pos_Y(), oponente.Largura(), oponente.Altura());
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
	public void desenhaObstaculo(Graphics g) {
    	g.setColor(config.corObstaculo());
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
