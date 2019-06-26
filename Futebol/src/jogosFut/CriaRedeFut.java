package jogosFut;

import framework.CriaRede;
import framework.Servidor;

public class CriaRedeFut extends CriaRede {

	int tam;
	int vel;
	
	public CriaRedeFut(int t, int v) {
		this.setTitle("Futebol Online");
		
		tam = t;
		vel = v;
		
	}
	
	@Override
	public void criaServidor() {
		new Servidor();
		new FutRede(tam, vel);
	}
	
	@Override
	public void criaCliente() {
		new FutRede(tam, vel);
	}
	
}
