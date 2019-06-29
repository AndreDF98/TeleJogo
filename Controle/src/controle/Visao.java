package controle;

import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Visao extends JFrame {
	
	Modelo modelo;
	
	Container conteudo;
	JLabel fundo;
	JLabel logo;
	
	Font fonte = new Font("Arial", Font.BOLD, 10);
	
	public Visao(Modelo mod) {
		
		modelo = mod;
		
		conteudo = this.getContentPane();
		conteudo.setLayout(null);
		
		fundo = new JLabel(new ImageIcon(getClass().getResource("../fundoControle.png")));
		fundo.setBounds(0, 0, 500, 300);
		conteudo.add(fundo);
		
		logo = new JLabel(new ImageIcon(getClass().getResource("../logo.png")));
		logo.setOpaque(true);
		logo.setBounds(240, 50, 210, 50);
		conteudo.add(logo);
		
		modelo.treino.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
		modelo.treino.setContentAreaFilled(false);
		modelo.treino.setBorderPainted(false);
		modelo.treino.setBounds(80, 45, 75, 25);
		conteudo.add(modelo.treino);
		JLabel jogoTreino = new JLabel("JOGO  -  TREINO");
		jogoTreino.setBounds(75, 70, 100, 20);
		jogoTreino.setFont(fonte);
		conteudo.add(jogoTreino);
		
		modelo.velocidade.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
		modelo.velocidade.setContentAreaFilled(false);
		modelo.velocidade.setBorderPainted(false);
		modelo.velocidade.setBounds(80, 95, 75, 25);
		conteudo.add(modelo.velocidade);
		JLabel jogoVelocidade = new JLabel("CRS - BX - MD - ALT");
		jogoVelocidade.setBounds(70, 120, 110, 20);
		jogoVelocidade.setFont(fonte);
		conteudo.add(jogoVelocidade);
		
		modelo.tamanho.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
		modelo.tamanho.setContentAreaFilled(false);
		modelo.tamanho.setBorderPainted(false);
		modelo.tamanho.setBounds(80, 145, 75, 25);
		conteudo.add(modelo.tamanho);
		JLabel jogoTamanho = new JLabel("PEQ - MED - GRAN");
		jogoTamanho.setBounds(75, 170, 100, 20);
		jogoTamanho.setFont(fonte);
		conteudo.add(jogoTamanho);
		
		modelo.online.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
		modelo.online.setContentAreaFilled(false);
		modelo.online.setBorderPainted(false);
		modelo.online.setBounds(80, 195, 75, 25);
		conteudo.add(modelo.online);
		JLabel jogoOnline = new JLabel("LOCAL  -  ONLINE");
		jogoOnline.setBounds(75, 220, 100, 20);
		jogoOnline.setFont(fonte);
		conteudo.add(jogoOnline);
		
		modelo.tenis.setText("TÊNIS");
		modelo.tenis.setBounds(260,115, 80, 20);
		modelo.tenis.setFont(fonte);
		modelo.tenis.setSelected(true);
		modelo.paredao.setText("PAREDÃO");
		modelo.paredao.setBounds(260,135, 90, 20);
		modelo.paredao.setFont(fonte);
		modelo.futebol.setText("FUTEBOL");
		modelo.futebol.setBounds(260,155, 90, 20);
		modelo.futebol.setFont(fonte);
		
		conteudo.add(modelo.tenis);
		conteudo.add(modelo.paredao);
		conteudo.add(modelo.futebol);
		
		modelo.trocaJogo.setIcon(new ImageIcon(getClass().getResource("../botaoGrande.png")));
		modelo.trocaJogo.setContentAreaFilled(false);
		modelo.trocaJogo.setBorderPainted(false);
		modelo.trocaJogo.setBounds(280,180, 40, 40);
		conteudo.add(modelo.trocaJogo);
		
		JLabel inicioJogo = new JLabel("INÍCIO-JOGO");
		inicioJogo.setBounds(350, 155, 100, 20);
		inicioJogo.setFont(fonte);
		conteudo.add(inicioJogo);
		modelo.jogar.setIcon(new ImageIcon(getClass().getResource("../botaoGrande.png")));
		modelo.jogar.setContentAreaFilled(false);
		modelo.jogar.setBorderPainted(false);
		modelo.jogar.setBounds(365,180, 40, 40);
		conteudo.add(modelo.jogar);
		
		this.setSize(500, 300);
		this.setTitle("TeleJogo");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void mudaTreino(int escolha) {
		if(escolha == 1) modelo.treino.setIcon(new ImageIcon(getClass().getResource("../botaoFinal.png")));
		else modelo.treino.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
	}
	
	public void mudaVelocidade(int escolha) {
		if(escolha == 0) modelo.velocidade.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
		else if(escolha == 3) modelo.velocidade.setIcon(new ImageIcon(getClass().getResource("../botaoFinal.png")));
		else modelo.velocidade.setIcon(new ImageIcon(getClass().getResource("../botaoMeio" + escolha + ".png")));
	}
	
	public void mudaTamanho(int escolha) {
		if(escolha == 1) modelo.tamanho.setIcon(new ImageIcon(getClass().getResource("../botaoMeio.png")));
		else if(escolha == 2) modelo.tamanho.setIcon(new ImageIcon(getClass().getResource("../botaoFinal.png")));
		else modelo.tamanho.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
	}
	
	public void mudaOnline(int escolha) {
		if(escolha == 1) modelo.online.setIcon(new ImageIcon(getClass().getResource("../botaoFinal.png")));
		else modelo.online.setIcon(new ImageIcon(getClass().getResource("../botaoInicial.png")));
	}
	
}
