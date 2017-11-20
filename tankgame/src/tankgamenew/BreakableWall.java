package tankgamenew;

import java.awt.*;

public class BreakableWall extends GameObject {

    public BreakableWall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
    }

    public void render(Graphics g) {

        g.drawImage(tex.breakWall, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}



