package tankgamenew;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
    }

    public void render(Graphics g) {

        g.drawImage(tex.brickWall, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
