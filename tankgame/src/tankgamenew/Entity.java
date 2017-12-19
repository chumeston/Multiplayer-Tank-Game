package tankgamenew;

import java.awt.Graphics;

public interface Entity {
    void tick();
    void render(Graphics g);
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);

    double getVelX();
    double getVelY();
    void setVelX(double x);
    void setVelY(double y);
}
