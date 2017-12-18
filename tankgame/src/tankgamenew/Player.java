package tankgamenew;

import java.awt.*;
import java.util.ArrayList;

public class Player extends TickingObject {

    private double velX, velY = 0;
    private boolean canMoveRight = true, canMoveLeft= true, canMoveDown= true, canMoveUp= true;

    Animation animates;
    Animation animatesR;
    Animation animatesL;
    Animation animatesD;
    Animation animatesUR;
    Animation animatesUL;
    Animation animatesDR;
    Animation animatesDL;

    private Direction direction;

    public Player(double x, double y, GlobalTexture tex) {
        super(x, y, tex);

        direction = Direction.RIGHT;

        animates = new Animation(2, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
        animatesR = new Animation(2, tex.playerRight[0], tex.playerRight[1], tex.playerRight[2], tex.playerRight[3]);
        animatesL = new Animation(2, tex.playerLeft[0], tex.playerLeft[1], tex.playerLeft[2], tex.playerLeft[3]);
        animatesD = new Animation(2, tex.playerDown[0], tex.playerDown[1], tex.playerDown[2], tex.playerDown[3]);
        animatesUR = new Animation(2, tex.playerUpRight[0]);
        animatesUL = new Animation(2, tex.playerUpLeft[0]);
        animatesDR = new Animation(2, tex.playerDownRight[0]);
        animatesDL = new Animation(2, tex.playerDownLeft[0]);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if (direction == Direction.UP) {
            animates.animate();
        } else if (direction == Direction.RIGHT) {
            animatesR.animate();
        } else if (direction == Direction.LEFT) {
            animatesL.animate();
        } else if (direction == Direction.DOWN) {
            animatesD.animate();
        } else if (direction == Direction.UP_RIGHT) {
            animatesUR.animate();
        } else if (direction == Direction.UP_LEFT) {
            animatesUL.animate();
        } else if (direction == Direction.DOWN_RIGHT) {
            animatesDR.animate();
        } else if (direction == Direction.DOWN_LEFT) {
            animatesDL.animate();
        }
    }

    @Override
    public void render(Graphics g) {

        if (direction == Direction.UP) {
            animates.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.RIGHT) {
            animatesR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.LEFT) {
            animatesL.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN) {
            animatesD.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.UP_RIGHT) {
            animatesUR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.UP_LEFT) {
            animatesUL.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN_RIGHT) {
            animatesDR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN_LEFT) {
            animatesDL.drawAnimation(g, x, y, 0);
        }

        update();
    }

    public void update() {
        if (Game.p.getBound().intersects(Game.p2.getBound())) {
            if (Game.p.getX() > Game.p2.getX()) {
                Game.p.setX(Game.p.getX() + 5);
            } else if (Game.p.getX() < Game.p2.getX()) {
                Game.p.setX(Game.p.getX() - 5);
            }
            if (Game.p.getY() > Game.p2.getY()) {
                Game.p.setY(Game.p.getY() + 5);
            } else if (Game.p.getY() < Game.p2.getY()) {
                Game.p.setY(Game.p.getY() - 5);
            }
        }

        if (Game.p2.getBound().intersects(Game.p.getBound())) {

            if (Game.p2.getX() > Game.p.getX()) {
                Game.p2.setX(Game.p2.getX() + 5);
            } else if (Game.p2.getX() < Game.p.getX()) {
                Game.p2.setX(Game.p2.getX() - 5);
            }
            if (Game.p2.getY() > Game.p.getY()) {
                Game.p2.setY(Game.p2.getY() + 5);
            } else if (Game.p2.getY() < Game.p.getY()) {
                Game.p2.setY(Game.p2.getY() - 5);
            }
        }

    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rectangle getBound() {
        return new Rectangle((int) x + 12, (int) y + 12, 50, 40);
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getVelX() {
        return velX;
    }

    @Override
    public double getVelY() {
        return velY;
    }

    @Override
    public void setVelX(double velx) { this.velX = velx; }

    @Override
    public void setVelY(double vely) {
        this.velY = vely;
    }

    public boolean isCanMoveRight() { return canMoveRight; }

    public boolean isCanMoveLeft() { return canMoveLeft; }

    public boolean isCanMoveDown() { return canMoveDown; }

    public boolean isCanMoveUp() { return canMoveUp; }

}
