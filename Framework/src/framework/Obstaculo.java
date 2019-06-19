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
    
    public String faceColisao(Bola b) { //retorna a face em que a bola colide
    	
    	//colisao esquerda
    	if(	b.Pos_Y() + b.Diametro() 	>	pos_y				&&
    		b.Pos_Y()					< 	pos_y + altura		&&
    		b.Pos_X() + b.Diametro() 	>=	pos_x				&&
    		b.CentroX()					<=	pos_x				) {
    			
    			return "esquerda";
    	}
    	
    	//colisao direita
    	if(	b.Pos_Y() + b.Diametro()	> 	pos_y				&&
    		b.Pos_Y()					<	pos_y + altura		&&
    		b.Pos_X()					<=	pos_x + largura		&&
    		b.CentroX()					>=	pos_x + largura				) {
    			return "direita";
    	}
    	
    	//colisao cima
    	if(	b.Pos_X() + b.Diametro() 	> 	pos_x			 	&&
    		b.Pos_X()					< 	pos_x + largura		&&
    		b.Pos_Y() + b.Diametro() 	>=	pos_y				&&
    		b.CentroY()					<=	pos_y				) { 
    			return "cima";
    	}
    	
    	//colisao baixo
    	if(	b.Pos_X() + b.Diametro()	> 	pos_x 				&&
    		b.Pos_X() 					< 	pos_x + largura 	&&
    		b.Pos_Y() 					<= 	pos_y + altura		&&
    		b.CentroY()					>=	pos_y + altura		) {
    			return "baixo";
    	}
    	
    	return "sem_colisao";
    	
    }
	
}
