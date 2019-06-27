package jogosTenis;

import java.net.InetAddress;

import javax.swing.JOptionPane;

import framework.CriaRede;
import framework.Servidor;

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
		
		TenisRede host = new TenisRede(tam, vel, ip, "host");
		
	}
	
	@Override
	public void criaCliente() {
		
		ip = JOptionPane.showInputDialog(null, "ip do host: ", "Conectar com ip", 1);
		
		if(ip != null) {
			TenisRede guest = new TenisRede(tam, vel, ip, "guest");
		}
		
	}
	
}
