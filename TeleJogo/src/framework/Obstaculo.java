package framework;

public class Obstaculo {
	
	protected int altura;
	protected int largura;
    protected int pos_x;
    protected int pos_y;
    
    public int CENTRO_X = (Jogo.Largura() / 2) - (largura / 2);
    public int CENTRO_Y = (Jogo.Altura() / 2) - (altura / 2);

    public Obstaculo() {
    	
    	altura = 50;
    	largura = 50;
    	this.definePosicao(CENTRO_X, CENTRO_Y); //Centraliza o obstaculo verticalmente e horizontalmente
    	
    }

    public int Altura() {
        return altura;
    }

    public int Largura() {
        return largura;
    }

    public int Pos_X() {
        return pos_x;
    }

    public int Pos_Y() {
        return pos_y;
    }
    
    public void defineTamanho(int alt, int larg) {
    	altura = alt;
    	largura = larg;
    }
    
    public void definePosicao(int y, int x) {
    	pos_x = x;
    	pos_y = y;
    }
	
}
