import java.awt.*;

public class Player extends TickingObject {

    private double velX, velY = 0;

    Animation animates; // up
    Animation animatesR;
    Animation animatesL;
    Animation animatesD;

    private int direction;

    public Player(double x, double y, GlobalTexture tex) {
        super(x, y, tex);
        //velX = 3;
        //velY= 2;
        direction = 1;
        //speed 4 frame
        animates = new Animation(2,tex.player[0],tex.player[1],tex.player[2],tex.player[3]);
        animatesR = new Animation(2,tex.playerRight[0],tex.playerRight[1],tex.playerRight[2],tex.playerRight[3]);
        animatesL = new Animation(2,tex.playerLeft[0],tex.playerLeft[1],tex.playerLeft[2],tex.playerLeft[3]);
        animatesD = new Animation(2,tex.playerDown[0],tex.playerDown[1],tex.playerDown[2],tex.playerDown[3]);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

        if(x<0) {
            x=0;
        }
        if(x>Game.gameWidth-64) {
            x = Game.gameWidth - 64;
        }
        if(y> Game.gameHeight-64) {
            y=Game.gameHeight - 64; // -64
        }
        if(y <0 ) {
            y = 0;
        }

        if (direction == 1 ){
            animates.animate(); // up
        } else if (direction == 2) { //right
            animatesR.animate();
        } else if (direction == 3) { //left
            animatesL.animate();
        } else if (direction == 4) { //down
            animatesD.animate();
        }
    }

    @Override
    public void render(Graphics g) {

        if(direction ==1) {
            animates.drawAnimation(g,x,y,0);
        } else if (direction == 2) { //right
            animatesR.drawAnimation(g,x,y,0);;
        } else if (direction == 3) { //left
            animatesL.drawAnimation(g,x,y,0);
        } else if (direction == 4) { //down
            animatesD.drawAnimation(g,x,y,0);
        }


    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int  getDirection() {
        return direction;
    }


    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public void setX(double x) {
        this.x = x;
    }


    public void setY(double y) {
        this.y = y;
    }


    public double getVelX() {
        return velX;
    }


    public double getVelY() {
        return velY;
    }


    public void setVelX(double velx) {
        this.velX = velx;
    }


    public void setVelY(double vely) {
        this.velY = vely;
    }
}
