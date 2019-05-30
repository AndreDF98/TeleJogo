package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is the content pane of my JFrame.
 */
class PongPanel extends JPanel implements ActionListener,KeyListener{
    private Ball b;
    private Paddle left_pad;
    private Paddle right_pad;
    private Timer timer;
    private int DELAY = 10;
    private boolean keys[]; //Array the holds the activated keys.
    private int LEFT_UP = 0;
    private int LEFT_DOWN = 1;
    private int RIGHT_UP = 2;
    private int RIGHT_DOWN = 3;


    PongPanel(){
        setBackground(Color.BLACK);
        b = new Ball();
        left_pad = new Paddle("left");
        right_pad = new Paddle("right");
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(DELAY,this);
        timer.start();
        setDoubleBuffered(true);
        keys = new boolean[]{false,false,false,false};
    }
/*
Moving the code for moving paddles into actionPerformed make
the paddle movement super smooth.
 */
    //@Override
    public void actionPerformed(ActionEvent e) {
        b.move();
        checkCollision();
        if(keys[LEFT_UP]) left_pad.moveUp();
        if(keys[LEFT_DOWN]) left_pad.moveDown();
        if(keys[RIGHT_UP]) right_pad.moveUp();
        if(keys[RIGHT_DOWN]) right_pad.moveDown();
        repaint();
    }

    private void checkCollision() {
        if(b.getX_pos() < (left_pad.getX_pos() + left_pad.getWidth())) {
            if((b.getY_pos() > left_pad.getY_pos()) && (b.getY_pos() < (left_pad.getY_pos() + left_pad.getHeight())))
                b.revVelocityX();
            else {
                b.setX_pos(PongFrame.WIDTH / 2);
                b.setY_pos(PongFrame.HEIGHT / 2);
            }
        }
        if(b.getX_pos() > (right_pad.getX_pos() - right_pad.getWidth())){
            if((b.getY_pos() > right_pad.getY_pos()) && (b.getY_pos() < (right_pad.getY_pos() + right_pad.getHeight())))
                b.revVelocityX();
            else {
                b.setX_pos(PongFrame.WIDTH / 2);
                b.setY_pos(PongFrame.HEIGHT / 2);
            }

        }
        else if(b.getY_pos()<0 || b.getY_pos()>PongFrame.HEIGHT){
            b.revVelocityY();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
        drawPaddles(g);

    }

    private void drawPaddles(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(left_pad.getX_pos(),left_pad.getY_pos(),left_pad.getWidth(),left_pad.getHeight());
        g.fillRect(right_pad.getX_pos(),right_pad.getY_pos(),right_pad.getWidth(),right_pad.getHeight());
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(b.getX_pos(),b.getY_pos(),b.getDiameter(),b.getDiameter());
        /*
        The following line is super important.
        It smoothens the animation.
        Without it animation is chopyy in linux!
         */
        Toolkit.getDefaultToolkit().sync();
    }

    //@Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    //@Override
    public void keyPressed(KeyEvent e) {
        /*
        Controls for right paddle are the up and down arrow keys.
        Controls for left paddle are the w and s keys.

        Used the keys array to implement simultaneous movement of paddles.
        Movement code is shifted to the actionPerformed method.
        For eg. if keys[RIGHT_UP] = true,the action performed method will move
        the right paddle up.
         */
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            keys[RIGHT_UP] = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[RIGHT_DOWN] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            keys[LEFT_UP] = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keys[LEFT_DOWN] = true;
        }

    }

    //@Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            keys[RIGHT_UP] = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[RIGHT_DOWN] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            keys[LEFT_UP] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keys[LEFT_DOWN] = false;
        }
    }
}