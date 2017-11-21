package tankgamenew;

import java.awt.*;

public class Wall extends GameObject {
    public double x,y;
    public Wall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
        this.x= x ;
        this.y = y;
    }

    public void render(Graphics g) {

        g.drawImage(tex.brickWall, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
