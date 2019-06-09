package framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Placar extends JPanel{
	
	private JLabel esq;
	private JLabel dir;
	
	private int ptsEsq = 0;
	private int ptsDir = 0;
	
	public Placar() {
		
		this.setBounds(Jogo.Largura()/2 - 200, Jogo.Altura()/2 - 300, 400, 200);
		this.setLayout(new GridLayout(1, 2));
		this.setOpaque(false);
		
		esq = new JLabel("" + ptsEsq);
		dir = new JLabel("" + ptsDir);
		
		esq.setHorizontalAlignment(SwingConstants.CENTER);
		dir.setHorizontalAlignment(SwingConstants.CENTER);
		
		//esq.setForeground(Color.WHITE);
		dir.setForeground(Color.WHITE);
		
		//esq.setFont(new Font("Verdana", 10, Font.BOLD));
		//dir.setFont(new Font("Verdana", 10, Font.BOLD));
		
		this.add(esq);
		this.add(dir);
		
	}
	
	public void aumentaEsq() {
		ptsEsq++;
	}
	
	public void aumentaDir() {
		ptsDir++;
	}
	
}
