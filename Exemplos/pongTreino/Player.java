/*
 * Player.java
 *
 * Created on June 10, 2007, 3:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pongTreino;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mohamed Eldib
 */
public class Player {
    public int x;
    public int y;
    public int sizex;
    public int sizey;
    public int vy;
    private Color StickColor;
    
    /** Creates a new instance of Player */
    public Player(int x,int y,int sizex,int sizey,Color StickColor) {
        this.x=x;
        this.y=y;
        
        this.sizex=sizex;
        this.sizey=sizey;
        this.StickColor=StickColor;
        
    }
    public void Move(int dy) {
        y+=dy;
    }
    public void MoveByMouse(int newy)
    {
        y=newy;
    }
    
    public void DrawStrick(Graphics g) {
        g.setColor(StickColor);
        g.fillRect(x, y, sizex, sizey);
    }
}
