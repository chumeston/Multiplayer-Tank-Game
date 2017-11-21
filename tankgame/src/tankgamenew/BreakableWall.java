package tankgamenew;

import java.awt.*;

public class BreakableWall extends GameObject {

    public double x,y;
    public BreakableWall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
        this.x= x ;
        this.y = y;
    }

    public void render(Graphics g) {

        g.drawImage(tex.breakWall, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}



