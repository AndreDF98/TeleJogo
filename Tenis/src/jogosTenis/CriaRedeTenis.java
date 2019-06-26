package jogosTenis;

import java.net.InetAddress;

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
		
		//new Servidor();
		new TenisRede(tam, vel, ip);
	}
	
	@Override
	public void criaCliente() {
		
		new TenisRede(tam, vel, ip);
	}
	
}
