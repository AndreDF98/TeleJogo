package framework;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Jogo extends JPanel  implements ActionListener, KeyListener{
	
	private static int ALTURA = 800;
	private static int LARGURA = 1200;
	
	private JFrame janela = new JFrame();
	
	protected Bola bola;
	
	protected Timer timer;
	protected int ATRASO = 10;
	
	public Jogo() {
		
		this.definePlanoFundo(Color.BLACK);
		this.defineTamanho(LARGURA, ALTURA);
		this.defineTitulo("Jogo");
		this.setDoubleBuffered(true);
		this.setLayout(null);
		janela.add(this);
		
		timer = new Timer(ATRASO,this);
		
		addKeyListener(this);
        setFocusable(true);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		janela.setLocationRelativeTo(null);
		
	}
	
	
	public static int Altura() {
		return ALTURA;
	}
	
	public static int Largura() {
		return LARGURA;
	}
	
	public void definePlanoFundo(Color cor) {
		this.setBackground(cor);
	}
	
	public void defineTamanho(int largura, int altura) {
		LARGURA = largura;
		ALTURA = altura;
		janela.setSize(LARGURA, ALTURA);
	}
	
	public void defineTitulo(String nome) {
		janela.setTitle(nome);
	}
	
	public void defineAtraso(int a) {
		ATRASO = a;
	}

	//O jogo deve possuir os seguintes listeners e funcoes:
	
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	desenhaObstaculo(g);
    	desenhaBola(g);
        desenhaJogador(g);
    }
	
	//estes serão configurados de maneira diferente em cada jogo:
	
	public void checaColisao() {}

    public void desenhaJogador(Graphics g) {}

	public void desenhaBola(Graphics g) {
    	g.setColor(Color.WHITE);
        g.fillRect(bola.Pos_X(),bola.Pos_Y(), bola.Diametro(), bola.Diametro());
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void desenhaObstaculo(Graphics g) {}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {}
	
}
