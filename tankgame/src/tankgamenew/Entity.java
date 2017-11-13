package tankgamenew;

import java.awt.Graphics;

public interface Entity {
    public void tick();
    public void render(Graphics g);
    public double getX();
    public double getY();
    public void setX(double x);
    public void setY(double y);

    public double getVelX();
    public double getVelY();
    public void setVelX(double x);
    public void setVelY(double y);
}
