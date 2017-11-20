package tankgamenew;

import java.awt.*;

public class Explosion extends GameObject {

    public Explosion(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
    }

    public void render(Graphics g) {

        g.drawImage(tex.exploded, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}