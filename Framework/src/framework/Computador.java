package framework;

public class Computador extends Jogador {
	
	private int meio_y;
	private int meio_x;
	
	public Computador() {
		this.defineVel(6);
	}
	
	public void moveVertical(Bola b) {
		meio_y = pos_y + (altura / 2);
		
        if (b.Vel_X() > 0) {
        
            if ( meio_y != b.Pos_Y()) {
       
                if (b.Pos_Y() < meio_y) {
                	moveCima();
                }
      
                else if (b.Pos_Y() > meio_y) {
                	moveBaixo();
                }
            }
        }
    }
	
	public void moveHorizontal() {}
	
}
