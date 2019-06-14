package controle;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jogosTenis.TenisLocal;
import jogosTenis.TenisTreino;

public class Controle extends JFrame{
	
	Container conteudo;
	JLabel fundo;
	JLabel logo;
	
	BotoesControle botoes;
	
	public Controle() {
		
		conteudo = new Container();
		conteudo = this.getContentPane();
		conteudo.setLayout(null);
		
		fundo = new JLabel(new ImageIcon(getClass().getResource("../fundoControle.png")));
		fundo.setBounds(0, 0, 500, 300);
		conteudo.add(fundo);
		
		logo = new JLabel(new ImageIcon(getClass().getResource("../logo.png")));
		logo.setOpaque(true);
		logo.setBounds(240, 50, 210, 50);
		conteudo.add(logo);
		
		botoes = new BotoesControle();
		conteudo.add(botoes);
		
		this.setSize(500, 300);
		this.setTitle("TeleJogo");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String args[]) {
		new Controle();
		
	}
	
}
