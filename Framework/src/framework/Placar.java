package framework;

public class Placar{
	
	private int ptsEsq = 0;
	private int ptsDir = 0;
	
	private int pos_x_esq = Jogo.Largura()/2 - 200;
	private int pos_y_esq = Jogo.Altura()/5;
	private int pos_x_dir = Jogo.Largura()/2 + 100;
	private int pos_y_dir = Jogo.Altura()/5;
	
	public byte[] PtsEsq() {
		return ("" + ptsEsq).getBytes();
	}
	
	public byte[] PtsDir() {
		return ("" + ptsDir).getBytes();
	}
	
	public int Pos_X_Esq() {
		return pos_x_esq;
	}
	
	public int Pos_Y_Esq() {
		return pos_y_esq;
	}
	
	public int Pos_X_Dir() {
		return pos_x_dir;
	}
	
	public int Pos_Y_Dir() {
		return pos_y_dir;
	}
	
	public void aumentaEsq() {
		ptsEsq++;
	}
	
	public void aumentaDir() {
		ptsDir++;
	}
	
}
