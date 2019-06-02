/*
 * Main.java
 *
 * Created on June 10, 2007, 2:59 AM
 *
 * To change this template, choose Tools | Template Manager
 *
 and open the template in the editor.
 */

package pongTreino;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Mohamed Eldib
 */
public class PongTreino extends JFrame implements Runnable ,KeyListener ,MouseMotionListener,MouseListener {
    
    /** Creates a new instance of Main */
    
    private final int UP = 38;
    private final int DOWN = 40;
    private Player myPlayer;
    private Sound myS;
    private Computer myComp;
    private Ball myBall;
    private Image  dbImage;
    private Graphics dbg;
    private Thread th;
    private boolean GAME_STARTED;
    private boolean GAME_OPTION;
    private boolean GAME_OVER;
    private int CScore=0;
    private int PScore=0   ;
    private int TopScore=5;
    
    
    
    public PongTreino() {
        GAME_OPTION=true;
        setTitle("Sticker Game");
        setSize(400,400);
        setLocation(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myPlayer=new Player(20,125,10,50,Color.GREEN.darker());
        myComp=new Computer(370,124,10,50,Color.RED);
        myBall=new Ball(8,200,150,2,2,Color.blue,this);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        
        setVisible(true);
        
    }
    
    public void paint(Graphics g) {
        if(GAME_OPTION) {
            Image codemiles= new ImageIcon("TeleJogo/imagens/codemiles.gif").getImage();
            
            g.setColor(Color.GRAY);
            myS= new Sound("TeleJogo/audios/cheer.wav");
            
            g.fillRect(0,0,400,400);
            g.setColor(Color.white);
            
            g.drawString("Press Mouse Click To Start Sticker Ball",100,100);
            g.drawImage(codemiles,150,150,myBall.myBase);
            g.drawString("Visit us at <<http://codemiles.com/>> for more online games",35,250);
            
        } else if(GAME_STARTED) {
            g.fillRect(0,0,400,300);
            
            myPlayer.DrawStrick(g);
            myComp.DrawStrick(g);
            myBall.DrawBall(g);
            g.setColor(Color.YELLOW);
            g.drawLine(25,32,375,32);
            g.drawLine(25,292,375,292);
            g.setColor(Color.GRAY);
            g.fillRect(400,300,400,100);
            g.setColor(Color.BLACK);
            g.drawString("Player Score :"+Integer.toString(PScore),50,350);
            g.drawString("Computer Score :"+Integer.toString(CScore),250,350);
            
        }
    }
    
    public void update(Graphics g) {
        
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }
        
        
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        
        
        dbg.setColor(getForeground());
        paint(dbg);
        
        
        g.drawImage(dbImage, 0, 0, this);
    }
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        
        while (true) {
            
            repaint();
            
            
            myBall.move();
            
            
            myComp.ComputerMove(myBall);
            
            
            int  whosgoal = myBall.wheresBall();
            
            
            if (whosgoal != 0) {
                if(whosgoal==1) {
                    CScore++;
                    myS = new Sound("TeleJogo/audios/chime.wav");
                } else {
                    
                    PScore++;
                    myS = new Sound("TeleJogo/audios/chime.wav");
                }
                if(PScore==5) {
                    GAME_STARTED=false;
                    GAME_OVER=GAME_OPTION=true;
                }else if(CScore==5) {
                    GAME_STARTED=false;
                    GAME_OVER=GAME_OPTION=true;
                }
                
                myBall.x= 200;
                myBall.vx = 2;
                myBall.vy = -2;
                
                
            }
            
            
            
            
            
            if (myBall.vx < 0) {
                myBall.PCollision(myPlayer);
            } else if (myBall.vx > 0) {
                myBall.CCollision(myComp);
            }
            
            try {
                // Stoppen des Threads für 10 Millisekunden
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                break;
            }
            
            // Zurücksetzen der ThreadPriority auf Maximalwert
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
        
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==UP) {
            myPlayer.Move(-15);
        } else if(e.getKeyCode()==DOWN) {
            myPlayer.Move(15);
        }
        
        if(!GAME_STARTED)
            GAME_STARTED=true;
        
        
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
        int y=e.getY();
        if(y<250&&y>25)
            myPlayer.MoveByMouse(y);
        
    }
    
    public void mouseClicked(MouseEvent e) {
        
        if(!GAME_STARTED) {
            GAME_STARTED=true;
            GAME_OPTION=false;
            th=new Thread(this);
            th.start();
            
        }
    }
    
    public void mousePressed(MouseEvent e) {
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    
}
