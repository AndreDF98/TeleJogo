package pongLocal;

//import com.sun.xml.internal.bind.v2.TODO;
class Paddle {
    private int height;
    private int width;
    private int x_pos;
    private int y_pos;
    private int vel;
//    private int accel; //TODO : Implement acceleration.


    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    int getX_pos() {
        return x_pos;
    }

    int getY_pos() {
        return y_pos;
    }
/*
The string parameter will tell if it is the left paddle
 or the right one.
 */
Paddle(String s) {
        vel = 10;
//        accel = 2;
        height = 150;
        width = 10;
        y_pos = (PongLocal.HEIGHT / 2) - (height / 2); //Centers the paddle
        if(s.equals("left")){
            x_pos = 10;
        }
        if(s.equals("right")){
            x_pos = PongLocal.WIDTH - 20;
        }
    }

    void moveUp(){
        if(y_pos > 0)
            y_pos-=vel;
    }

    void moveDown(){
        if(y_pos < (PongLocal.HEIGHT - height))
            y_pos +=vel;
    }
}