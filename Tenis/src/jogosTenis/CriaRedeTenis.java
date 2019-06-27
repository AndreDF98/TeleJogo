package jogosTenis;

import java.net.InetAddress;

import framework.CriaRede;
import framework.Servidor;

public class CriaRedeTenis extends CriaRede {

	int tam;
	int vel;
	
	InetAddress ip;
	
	public CriaRedeTenis(int t, int v) {
		this.setTitle("Tênis Online");
		
		tam = t;
		vel = v;
		
	}
	
	@Override
	public void criaServidor() {

		try {
            ip = InetAddress.getLocalHost();
        } catch (Exception e) {
        }
		
		Servidor servidor = new Servidor();
		TenisRede host = new TenisRede(tam, vel, ip, "host");
		
		//servidor.esperaPacotes();
		//host.esperaPacotes();
	}
	
	@Override
	public void criaCliente() {
		//ip = InetAddress.getLocalHost().getHostAddress();
		try {//trocar para pegar o ip com o usuario
            ip = InetAddress.getLocalHost();
        } catch (Exception e) {
        }
		
		TenisRede guest = new TenisRede(tam, vel, ip, "guest");
		//guest.esperaPacotes();
		
	}
	
}
