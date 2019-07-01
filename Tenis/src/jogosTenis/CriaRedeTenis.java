package jogosTenis;

import java.net.InetAddress;

import javax.swing.JOptionPane;

import framework.CriaRede;

public class CriaRedeTenis extends CriaRede {

	int tam;
	int vel;
	
	String ip;
	
	public CriaRedeTenis(int t, int v) {
		this.setTitle("Tênis Online");
		
		tam = t;
		vel = v;
		
	}
	
	@Override
	public void criaServidor() {
		
		try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
        }
		
		JOptionPane.showMessageDialog(null, "Seu ip é " + ip, "Aviso", 1);
		System.out.println("Servidor criado com ip " + ip);
		
		TenisRede host = new TenisRede(tam, vel, ip, "host");
		Thread myServerT = new Thread(host);
		myServerT.start();
		
	}
	
	@Override
	public void criaCliente() {
		
		ip = JOptionPane.showInputDialog(null, "ip do host: ", "Conectar com ip", 1);
		
		try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
        }
		
		if(ip != null) {
			TenisRede guest = new TenisRede(tam, vel, ip, "guest");
			Thread myServerT = new Thread(guest);
			myServerT.start();
		}
		
	}
	
}
