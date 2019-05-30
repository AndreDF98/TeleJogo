package paredao;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    // size of the ball 
    final static int WIDTH = 10;
    final static int HEIGHT = 10;
    
    // how fast to move up and down
    final static int VERTICAL_VELOCITY = 7;

    // x and y are the position of the center of the ball
    int x, y;

    // vx and vy are the x and y component of the velocity of the ball.
    int vx, vy;

    Ball()
    {
        x = Pong.WIDTH/2;
        y = Ball.HEIGHT/2;
        vx = 0;
        vy = 0;
    }

    // draw the white ball on the screen.
    void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(x - Ball.WIDTH/2, y - Ball.HEIGHT/2, 
                Ball.WIDTH, Ball.HEIGHT);
    }

    // called when user first click the mouse.  Change the velocity so that
    // the ball starts moving.
    void start_moving()
    {
        vx = 0;
        vy = VERTICAL_VELOCITY;
    }

    // calculate the next position of the ball.  The velocity
    // might change due to collision with paddle or boundary
    // of the game area.
    void move_one_step(Paddle paddle)
    {
        // new position
        x += vx;
        y += vy;

        // check for bouncing
        if (x <= Ball.WIDTH/2 || x >= Pong.WIDTH - Ball.WIDTH/2)
        {
            // bounds off horizontally
            vx = -vx;
        }
        if (y <= Ball.HEIGHT/2)
        {
            // bounds off horizontally
            vy = -vy;
        } 
        else if (y > Pong.HEIGHT - Ball.HEIGHT/2) 
        {
            // goes out of bound! loose point and restart.
            x = Pong.WIDTH/2;
            y = Ball.HEIGHT/2;
            vx = 0;
            vy = 0;
        }
        else if (y + Ball.HEIGHT/2 > Pong.HEIGHT - Paddle.HEIGHT) 
        {
            int px = paddle.x;
            int py = paddle.y;
            // Ball collides with paddle.  Change the direction (vx) depending
            // on the collision point between the ball and the paddle.
            if (x >= px - Paddle.R1 && x <= px + Paddle.R1) 
            {
                vy = -vy;
            } 
            else if (x >= px - Paddle.R2 && x <= px + Paddle.R2) 
            {
                vx+= (x > px? 1 : -1);
                vy = -vy;
            } 
            else if (x >= px - Paddle.R3 && x <= px + Paddle.R3) 
            {
                vx+= (x > px? 2 : -2);
                vy = -vy;
            } 
            else if (x + Ball.WIDTH/2 >= px - Paddle.WIDTH/2 && x - Ball.WIDTH/2 <= px + Paddle.WIDTH/2) 
            {
                vx+= (x > px? 3 : -3);
                vy = -vy;
            }
        }
    }
}