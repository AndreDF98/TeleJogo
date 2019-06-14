package framework;

public class Bola {
	
	private int pos_x;
    private int pos_y;

    private int diametro;

    private int vel_x;
    private int vel_y;
    
    private double acel;
    
    public Bola() {
        vel_x = 5;
        vel_y = 5;
        acel = 1;
        diametro = 20;
        pos_x = Jogo.Largura()/2 - diametro/2;
        pos_y = Jogo.Altura()/2 - diametro/2;

    }

    public void move(){
        pos_x += vel_x * acel;
        pos_y += vel_y * acel;
    }

    public int Pos_X() {
        return pos_x;
    }

    public int Pos_Y() {
        return pos_y;
    }

    public int Diametro() {
        return diametro;
    }
    
    public int Vel_X() {
    	return vel_x;
    }
    
    public int Vel_Y() {
    	return vel_y;
    }
    
    public double Acel() {
    	return acel;
    }

    public void inverteVelX(){
        vel_x = -vel_x;
    }

    public void inverteVelY(){
        vel_y = -vel_y;
    }

    public void definePos_X(int x_pos) {
        this.pos_x = x_pos;
    }

    public void definePos_Y(int y_pos) {
        this.pos_y = y_pos;
    }
    
    public void defineVel(int vel) {
    	vel_x = vel;
    	vel_y = vel;
    }
    
    public void defineAcel(double a) {
    	acel = a;
    }
    
    public void aumentaAcel(double d) {
    	acel += d;
    }
	
}
