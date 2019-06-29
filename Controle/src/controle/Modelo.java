package controle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Modelo extends JPanel {

	JButton treino;
	JButton velocidade;
	JButton tamanho;
	JButton online;
	
	ButtonGroup jogos;
	JRadioButton tenis;
	JRadioButton paredao;
	JRadioButton futebol;
	JButton trocaJogo;
	
	JButton jogar;
	
	public Modelo() {
		
		treino = new JButton();
		velocidade = new JButton();
		tamanho = new JButton();
		online = new JButton();
		
		jogos = new ButtonGroup();
		tenis = new JRadioButton();;
		paredao = new JRadioButton();
		futebol = new JRadioButton();
		trocaJogo = new JButton();
		
		jogos.add(tenis);
		jogos.add(paredao);
		jogos.add(futebol);
		
		jogar = new JButton();
		
	}
	
}
