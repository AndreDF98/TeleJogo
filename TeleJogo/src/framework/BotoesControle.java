package framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class BotoesControle extends JPanel{

	JButton treino;
	JButton velocidade;
	JButton tamanho;
	JButton online;
	
	ButtonGroup jogos;
	JRadioButton paredao;
	JRadioButton tenis;
	JButton trocaJogo;
	
	int escolhaTreino = 0;
	int escolhaVelocidade = 0;
	int escolhaTamanho = 0;
	int escolhaOnline = 0;
	
	JButton jogar;
	
	Font fonte = new Font("Arial", Font.BOLD, 10);
	
	public BotoesControle() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 500, 300);
		
		treino = new JButton(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		treino.setContentAreaFilled(false);
		treino.setBorderPainted(false);
		treino.setBounds(80, 45, 75, 25);
		treino.addActionListener(mudaTreino);
		this.add(treino);
		JLabel jogoTreino = new JLabel("JOGO  -  TREINO");
		jogoTreino.setBounds(75, 70, 100, 20);
		jogoTreino.setFont(fonte);
		this.add(jogoTreino);
		
		velocidade = new JButton(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		velocidade.setContentAreaFilled(false);
		velocidade.setBorderPainted(false);
		velocidade.setBounds(80, 95, 75, 25);
		velocidade.addActionListener(mudaVelocidade);
		this.add(velocidade);
		JLabel jogoVelocidade = new JLabel("CRS - BX - MD - ALT");
		jogoVelocidade.setBounds(70, 120, 110, 20);
		jogoVelocidade.setFont(fonte);
		this.add(jogoVelocidade);
		
		tamanho = new JButton(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		tamanho.setContentAreaFilled(false);
		tamanho.setBorderPainted(false);
		tamanho.setBounds(80, 145, 75, 25);
		tamanho.addActionListener(mudaTamanho);
		this.add(tamanho);
		JLabel jogoTamanho = new JLabel("PEQ - MED - GRAN");
		jogoTamanho.setBounds(75, 170, 100, 20);
		jogoTamanho.setFont(fonte);
		this.add(jogoTamanho);
		
		online = new JButton(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		online.setContentAreaFilled(false);
		online.setBorderPainted(false);
		online.setBounds(80, 195, 75, 25);
		online.addActionListener(mudaOnline);
		this.add(online);
		JLabel jogoOnline = new JLabel("LOCAL  -  ONLINE");
		jogoOnline.setBounds(75, 220, 100, 20);
		jogoOnline.setFont(fonte);
		this.add(jogoOnline);
		
		jogos = new ButtonGroup();
		tenis = new JRadioButton("TÊNIS");
		tenis.setBounds(260,135, 80, 20);
		tenis.setFont(fonte);
		tenis.setSelected(true);
		paredao = new JRadioButton("PAREDÃO");
		paredao.setBounds(260,155, 90, 20);
		paredao.setFont(fonte);
		jogos.add(tenis);
		jogos.add(paredao);
		this.add(tenis);
		this.add(paredao);
		
		trocaJogo = new JButton(new ImageIcon(getClass().getResource("imagens/botaoGrande.png")));
		trocaJogo.setContentAreaFilled(false);
		trocaJogo.setBorderPainted(false);
		trocaJogo.setBounds(280,180, 40, 40);
		trocaJogo.addActionListener(mudaJogo);
		this.add(trocaJogo);
		
		JLabel inicioJogo = new JLabel("INÍCIO-JOGO");
		inicioJogo.setBounds(350, 155, 100, 20);
		inicioJogo.setFont(fonte);
		this.add(inicioJogo);
		jogar = new JButton(new ImageIcon(getClass().getResource("imagens/botaoGrande.png")));
		jogar.setContentAreaFilled(false);
		jogar.setBorderPainted(false);
		jogar.setBounds(365,180, 40, 40);
		jogar.addActionListener(comecaJogo);
		this.add(jogar);
		
	}
	
	ActionListener mudaTreino = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaTreino = (escolhaTreino + 1) % 2;
			if(escolhaTreino == 1) treino.setIcon(new ImageIcon(getClass().getResource("imagens/botaoFinal.png")));
			else treino.setIcon(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		}
	};
	
	ActionListener mudaVelocidade = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaVelocidade = (escolhaVelocidade + 1) % 4;
			if(escolhaVelocidade == 0) velocidade.setIcon(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
			else if(escolhaVelocidade == 3) velocidade.setIcon(new ImageIcon(getClass().getResource("imagens/botaoFinal.png")));
			else velocidade.setIcon(new ImageIcon(getClass().getResource("imagens/botaoMeio" + escolhaVelocidade + ".png")));
		}
	};
	
	ActionListener mudaTamanho = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaTamanho = (escolhaTamanho + 1) % 3;
			if(escolhaTamanho == 1) tamanho.setIcon(new ImageIcon(getClass().getResource("imagens/botaoMeio.png")));
			else if(escolhaTamanho == 2) tamanho.setIcon(new ImageIcon(getClass().getResource("imagens/botaoFinal.png")));
			else tamanho.setIcon(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		}
	};
	
	ActionListener mudaOnline = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaOnline = (escolhaOnline + 1) % 2;
			if(escolhaOnline == 1) online.setIcon(new ImageIcon(getClass().getResource("imagens/botaoFinal.png")));
			else online.setIcon(new ImageIcon(getClass().getResource("imagens/botaoInicial.png")));
		}
	};
	
	ActionListener mudaJogo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(paredao.isSelected()) tenis.setSelected(true);
			else paredao.setSelected(true);
		}
	};
	
	ActionListener comecaJogo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
}
