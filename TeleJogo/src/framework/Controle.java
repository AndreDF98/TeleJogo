package framework;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sun.javafx.iio.ImageMetadata;

public class Controle extends JFrame{
	
	Container conteudo;
	JLabel fundo;
	JLabel logo;
	
	JButton treino;
	JButton velocidade;
	JButton tamanho;
	JButton online;
	
	ButtonGroup jogos;
	JRadioButton paredao;
	JRadioButton tenis;
	JButton trocaJogo;
	
	JButton jogar;
	
	Font fonte = new Font("Arial", Font.BOLD, 10);
	
	public Controle() {
		
		conteudo = new Container();
		conteudo = this.getContentPane();
		conteudo.setLayout(null);
		
		fundo = new JLabel(new ImageIcon(getClass().getResource("imagens/fundoControle.png")));
		fundo.setBounds(0, 0, 500, 300);
		conteudo.add(fundo);
		
		logo = new JLabel(new ImageIcon(getClass().getResource("imagens/logo.png")));
		logo.setOpaque(true);
		logo.setBounds(240, 50, 210, 50);
		conteudo.add(logo);
		
		treino = new JButton();
		treino.setBounds(80, 45, 75, 25);
		conteudo.add(treino);
		JLabel jogoTreino = new JLabel("JOGO  -  TREINO");
		jogoTreino.setBounds(75, 70, 100, 20);
		jogoTreino.setFont(fonte);
		conteudo.add(jogoTreino);
		
		velocidade = new JButton();
		velocidade.setBounds(80, 95, 75, 25);
		conteudo.add(velocidade);
		JLabel jogoVelocidade = new JLabel("VELOCIDADE");
		jogoVelocidade.setBounds(75, 120, 100, 20);
		jogoVelocidade.setFont(fonte);
		conteudo.add(jogoVelocidade);
		
		tamanho = new JButton();
		tamanho.setBounds(80, 145, 75, 25);
		conteudo.add(tamanho);
		JLabel jogoTamanho = new JLabel("TAMANHO");
		jogoTamanho.setBounds(75, 170, 100, 20);
		jogoTamanho.setFont(fonte);
		conteudo.add(jogoTamanho);
		
		online = new JButton();
		online.setBounds(80, 195, 75, 25);
		conteudo.add(online);
		JLabel jogoOnline = new JLabel("ONLINE");
		jogoOnline.setBounds(75, 220, 100, 20);
		jogoOnline.setFont(fonte);
		conteudo.add(jogoOnline);
		
		jogos = new ButtonGroup();
		tenis = new JRadioButton("TÊNIS");
		tenis.setBounds(260,135, 80, 20);
		tenis.setFont(fonte);
		paredao = new JRadioButton("PAREDÃO");
		paredao.setBounds(260,155, 90, 20);
		paredao.setFont(fonte);
		jogos.add(tenis);
		jogos.add(paredao);
		conteudo.add(tenis);
		conteudo.add(paredao);
		
		trocaJogo = new JButton();
		trocaJogo.setBounds(280,180, 40, 40);
		conteudo.add(trocaJogo);
		
		JLabel inicioJogo = new JLabel("INÍCIO-JOGO");
		inicioJogo.setBounds(350, 155, 100, 20);
		inicioJogo.setFont(fonte);
		conteudo.add(inicioJogo);
		jogar = new JButton();
		jogar.setBounds(365,180, 40, 40);
		conteudo.add(jogar);
		
		this.setSize(500, 300);
		this.setTitle("Tele Jogo");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		new Controle();
	}
	
}
