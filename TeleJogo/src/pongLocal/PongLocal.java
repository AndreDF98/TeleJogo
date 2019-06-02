package pongLocal;

import java.awt.Color;

import javax.swing.*;  

public class PongLocal {
    static final int  HEIGHT=600;
    static final int WIDTH=800;
    private PongPanel panel;

    public PongLocal(){
        JFrame frame  = new JFrame("Pong");
        frame.setSize(WIDTH,HEIGHT+47);
        panel = new PongPanel();

        frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

}