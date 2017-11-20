package tankgamenew;

import java.awt.*;

public class Ammo extends GameObject {

    public Ammo(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
    }

    public void render(Graphics g) {

        g.drawImage(tex.ammoCrate, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
