package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Jogo extends JPanel  implements ActionListener, KeyListener{
	
	private static int ALTURA = 800; // criar singleton para configuracao de jogo
	private static int LARGURA = 1200;
	
	private JFrame janela = new JFrame();
	
	protected Bola bola;
    protected Placar placar;
	
	protected Timer timer;
	protected int ATRASO = 6;
	
	protected boolean pausa = true; //o jogo inicia pausado
	
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
		janela.setVisible(true);
		janela.setLocationRelativeTo(null);
		
		janela.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                e.getWindow().dispose();
            }
        });
		
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
	
	public JFrame Janela() {
		return janela;
	}

	//O jogo deve possuir os seguintes listeners e funcoes (podem ser definidos de maneira diferente dependendo do jogo):
	
	@Override
    public final void paintComponent(Graphics g) { 
    	super.paintComponent(g);
    	desenhaObstaculo(g);
    	if(pausa == true) desenhaPlacar(g);
    	desenhaBola(g);
    	desenhaJogador(g);
    }

	public void desenhaObstaculo(Graphics g) {}

	public void desenhaPlacar(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Agency FB", Font.BOLD, 50));
		g.drawBytes(placar.PtsEsq(), 0, placar.PtsEsq().length, placar.Pos_X_Esq(), placar.Pos_Y_Esq());
		g.drawBytes(placar.PtsDir(), 0, placar.PtsDir().length, placar.Pos_X_Dir(), placar.Pos_Y_Dir());
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void desenhaBola(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(bola.Pos_X() - 1, bola.Pos_Y() - 1, bola.Diametro() + 2, bola.Diametro() + 2);
    	g.setColor(Color.WHITE);
        g.fillRect(bola.Pos_X(), bola.Pos_Y(), bola.Diametro(), bola.Diametro());
        Toolkit.getDefaultToolkit().sync();
    }
	
	public void desenhaJogador(Graphics g) {}
	
	public void checaColisao() {}
	
	public void checaBolaFora() {}
    
	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {}
	
}
