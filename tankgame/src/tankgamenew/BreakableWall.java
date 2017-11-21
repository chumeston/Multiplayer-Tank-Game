package tankgamenew;

import java.awt.*;

public class BreakableWall extends GameObject {

    public double x, y;

    public BreakableWall(double x, double y, GlobalTexture globalTexture) {
        super(x, y, globalTexture);
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {

        g.drawImage(tex.breakWall, (int) x, (int) y, null);
        update();
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void update() {

        if (Game.p.getBound().intersects(getBounds())) {
            if (Game.p.getX() > x)
                Game.p.setX(Game.p.getX() + 1);
            else if (Game.p.getX() < x)
                Game.p.setX(Game.p.getX() - 1);
            if (Game.p.getY() > y)
                Game.p.setY(Game.p.getY() + 1);
            else if (Game.p.getY() < y)
                Game.p.setY(Game.p.getY() - 1);
        }
        if (Game.p2.getBound().intersects(getBounds())) {
            if (Game.p2.getX() > x)
                Game.p2.setX(Game.p2.getX() + 1);
            else if (Game.p2.getX() < x)
                Game.p2.setX(Game.p2.getX() - 1);
            if (Game.p2.getY() > y)
                Game.p2.setY(Game.p2.getY() + 1);
            else if (Game.p2.getY() < y)
                Game.p2.setY(Game.p2.getY() - 1);
        }


    }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    public double getX() { return x; }

    public double getY() {
        return y;
    }

}



