package paredao;
/* Base code for CS4344 Assignment 1, Semester 1 07/08 
 *
 * Acknowledgement: this program is inspired by many implementations 
 * Pong found on the Web, especially the simplicity of Knute Johnson's 
 * code (http://www.frazmtn.com/~knute/Pong.java)
 *
 * v1.0 ooiwt Sun Aug 26 17:53:29 SGT 2007 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class Paredao extends Panel implements Runnable {

    // size of the game area
    static final int WIDTH = 500;
    static final int HEIGHT = 500;

    // num of frames per second
    final int FRAME_RATE = 40;

    Thread thread;
    Image img;
    Graphics g;

    Ball ball;
    Paddle paddle;
    
    public void init() {
        ball = new Ball();
        paddle = new Paddle();
        img = createImage(WIDTH,HEIGHT);
        g = img.getGraphics();

        enableEvents(MouseEvent.MOUSE_MOVED | MouseEvent.MOUSE_PRESSED);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void processMouseMotionEvent(MouseEvent e) {
        // move the paddle if the mouse is moved.
        paddle.move(e.getX());
    }

    public void processMouseEvent(MouseEvent e) {
        // move the ball if mouse is clicked.
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            ball.start_moving();
        }
    }
    
    public void run() {
        try 
        {
            while (true) 
            {
                ball.move_one_step(paddle);
                g.setColor(Color.black);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                ball.draw(g);
                paddle.draw(g);
                repaint();
                Thread.sleep(1000/FRAME_RATE);
            }
        } 
        catch (InterruptedException ie) 
        { 
            System.err.print("Interrupted!\n" + ie);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public Paredao() {
    	JFrame frame = new JFrame();
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        frame.add(this);
        frame.pack();
        frame.setSize(WIDTH+16, HEIGHT+39);
        frame.setVisible(true);
        this.init();
        this.start();
    }
    
}