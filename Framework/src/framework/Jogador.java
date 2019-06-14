package framework;

public class Jogador extends Obstaculo{
    
    protected int vel;
	
    private int limiteCima;
    private int limiteBaixo;
    private int limiteDireita;
    private int limiteEsquerda;

    public Jogador() {
    	
    	vel = 8;
    	altura = 50;
    	largura = 50;
    	this.definePosicao(CENTRO_Y, CENTRO_X); //Centraliza o jogador verticalmente e horizontalmente
    	
    	limiteCima = 0;
        limiteBaixo = Jogo.Altura() - altura;
        limiteDireita = Jogo.Largura() - largura;
        limiteEsquerda = 0;
    	
    }
    
    public void defineVel(int v) {
    	vel = v;
    }
    
    public void defineLimitesHoriz(int a, int b) {
    	limiteEsquerda = a;
    	limiteDireita = b;
    }
    
    public void defineLimitesVert(int a, int b) {
    	limiteCima = a;
    	limiteBaixo = b;
    }
    
    public void moveCima(){
        if(pos_y > limiteCima)
            pos_y -= vel;
    }

    public void moveBaixo(){
        if(pos_y + altura < limiteBaixo)
        	pos_y += vel;
        
    }
	
    public void moveEsquerda(){
        if(pos_x > limiteEsquerda)
            pos_x -= vel;
    }

    public void moveDireita(){
        if(pos_x < limiteDireita)
        	pos_x += vel;
    }
    
}
