package framework;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CriaRede extends JFrame {

	Container conteudo;
	
	JButton novoServidor;
	JButton novoCliente;
	
	public CriaRede() {
		
		conteudo = this.getContentPane();
		conteudo.setLayout(new GridLayout(2, 1));
		
		novoServidor = new JButton("Criar Servidor");
		novoCliente = new JButton("Conectar a um Servidor");
		
		conteudo.add(novoServidor);
		conteudo.add(novoCliente);
		
		novoServidor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criaServidor();
			}
		});
		
		novoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criaCliente();
			}
		});
		
		this.setTitle("Jogo Online");
		this.setVisible(true);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                e.getWindow().dispose();
            }
        });
		
	}
	
	public void criaServidor() {} ;
	
	public void criaCliente() {} ;
	
}
