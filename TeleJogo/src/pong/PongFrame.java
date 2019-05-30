package pong;

import javax.swing.*;  

class PongFrame {
    static final int  HEIGHT=600;
    static final int WIDTH=800;
    private PongPanel panel;

    private PongFrame(){
        JFrame frame  = new JFrame("Pong");
        frame.setSize(WIDTH,HEIGHT);
        panel = new PongPanel();

        frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            //@Override
            public void run() {
                new PongFrame();
            }
        });
    }

}