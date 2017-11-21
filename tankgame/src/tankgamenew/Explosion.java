package tankgamenew;

import java.awt.*;

public class Explosion {

    private double x;
    private double y ;
    private int  r;
    private int maxRadius;
    private Color color;

    public Explosion(double x, double y, int r ,int max,Color color ){
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        maxRadius = max;
    }

    public boolean update() {
        r++;
        if(r >= maxRadius){       //remove if it reach max radius
            return true;
        }
        return false;
    }
    public void render(Graphics g) {

        g.setColor(color);
        g.drawOval((int)(x-r),(int) (y-r),2*r,2*r);;

    }
}