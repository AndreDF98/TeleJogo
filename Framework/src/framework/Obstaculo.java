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
    	this.definePosicao(CENTRO_X - largura, CENTRO_Y - altura); //Centraliza o obstaculo verticalmente e horizontalmente
    	
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
    
    public boolean colide(Bola b) {
    	
    	if(colideCima(b) || colideBaixo(b)) {
    		b.inverteVelY();
    		return true;
    	}
    	
    	if(colideEsquerda(b) || colideDireita(b)) {
    		b.inverteVelX();
    		return true;
    	}
    	
    	
    	return false;
    }
    
    public boolean colideCima(Bola b) {
    	if(	b.Pos_X() + b.Diametro() 	> 	pos_x			 	&&
    		b.Pos_X()					< 	pos_x + largura		&&
    		b.Pos_Y() + b.Diametro() 	>=	pos_y				&&
    		b.CentroY()					<=	pos_y				) { 
    			return true;
    	}
    	
    	return false;
    }
    
    public boolean colideBaixo(Bola b) {
    	if(	b.Pos_X() + b.Diametro()	> 	pos_x 				&&
        		b.Pos_X() 					< 	pos_x + largura 	&&
        		b.Pos_Y() 					<= 	pos_y + altura		&&
        		b.CentroY()					>=	pos_y + altura		) {
        			return true;
        	}
    	
    	return false;
    }
    
    public boolean colideEsquerda(Bola b) {
    	if(	b.Pos_Y() + b.Diametro() 	>	pos_y				&&
    		b.Pos_Y()					< 	pos_y + altura		&&
    		b.Pos_X() + b.Diametro() 	>=	pos_x				&&
    		b.CentroX()					<=	pos_x				) {
    			
    			return true;
    	}
    	
    	return false;
    }
    
    public boolean colideDireita(Bola b) {
    	if(	b.Pos_Y() + b.Diametro()	> 	pos_y				&&
    		b.Pos_Y()					<	pos_y + altura		&&
    		b.Pos_X()					<=	pos_x + largura		&&
    		b.CentroX()					>=	pos_x + largura				) {
    			return true;
    	}
    	
    	return false;
    }
	
}
