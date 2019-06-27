package framework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JFrame;

public class Servidor extends JFrame {

	DatagramSocket socket;
	
	public Servidor() {
		
		try {
			socket = new DatagramSocket(5000);
		}
		catch(SocketException e) {
			System.err.println("Erro na criação do socket");
		}
		
	}
	
	public void esperaPacotes() {
		while(true) {
			
			try {
				
				byte dado[] = new byte[100];
				DatagramPacket pacoteRecebido = new DatagramPacket(dado, dado.length);
				
				socket.receive(pacoteRecebido);
			
				enviaPacote(pacoteRecebido);
			}
			catch(IOException e) {
				System.err.println("Erro ao manipular pacotes");
			}
			
		}
	}
	
	private void enviaPacote(DatagramPacket pacoteRecebido) throws IOException{
		DatagramPacket pacoteEnviado = new DatagramPacket(	pacoteRecebido.getData(),
															pacoteRecebido.getLength(),
															pacoteRecebido.getAddress(),
															pacoteRecebido.getPort());
		
		socket.send(pacoteEnviado);
	}
	
}
