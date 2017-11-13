import java.awt.*;

public abstract class TickingObject implements Entity {

    protected double x,y,velX,velY;
    protected GlobalTexture tex;
    protected boolean goingUpDown;
    protected boolean goingLeftRight;

    public abstract void tick();

    public TickingObject(double x, double y, GlobalTexture tex) {
        this.x = y;
        this.y = y;
        this.tex = tex;
    }

    public double getX(){
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

    public void setVelX(double velX) {
        this.velX = velX;
        goingLeftRight = true;
        goingUpDown = false;
    }

    public void setVelY(double velY) {
        this.velY = velY;
        goingLeftRight = false;
        goingUpDown = true;
    }
}
