package tankgamenew;

import java.awt.*;

public class Wall extends GameObject {

    public double x, y;

    public Wall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {

        g.drawImage(tex.brickWall, (int) x, (int) y, null);
        update();
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void update() {

        if (Game.p.getBound().intersects(getBounds())) {
            if (Game.p.getX() > x) {
                Game.p.setX(Game.p.getX() + 7);
            } else if (Game.p.getX() < x) {
                Game.p.setX(Game.p.getX() - 7);
            }
            if (Game.p.getY() > y) {
                Game.p.setY(Game.p.getY() + 7);
            } else if (Game.p.getY() < y) {
                Game.p.setY(Game.p.getY() - 7);
            }
        }
        if (Game.p2.getBound().intersects(getBounds())) {
            if (Game.p2.getX() > x) {
                Game.p2.setX(Game.p2.getX() + 7);
            } else if (Game.p2.getX() < x) {
                Game.p2.setX(Game.p2.getX() - 7);
            }
            if (Game.p2.getY() > y) {
                Game.p2.setY(Game.p2.getY() + 7);
            } else if (Game.p2.getY() < y) {
                Game.p2.setY(Game.p2.getY() - 7);
            }
        }
    }

}
