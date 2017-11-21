package tankgamenew;

import java.awt.*;
import java.util.ArrayList;

public class Player extends TickingObject {

    private double velX, velY = 0;
    private ArrayList<Wall> wallArrayList = Controller.getWalls();
    private ArrayList<BreakableWall> bwallArrayList = Controller.getBreakWalls();
    private boolean canMoveRight, canMoveLeft, canMoveDown, canMoveUp,
            canMoveUpRight, canMoveUpLeft, canMoveDownRight, canMoveDownLeft;

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

        if (x < 0) {
            x = 0;
        }
        if (x > Game.gameWidth - 64) {
            x = Game.gameWidth - 64;
        }
        if (y > Game.gameHeight - 64) {
            y = Game.gameHeight - 64;
        }
        if (y < 0) {
            y = 0;
        }


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

        g.setColor(Color.green);

        doCollision(wallArrayList);
        doCollisionBreakable(bwallArrayList);
        if (direction == Direction.UP) {
            animates.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.RIGHT) { //right
            animatesR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.LEFT) { //left
            animatesL.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN) { //down
            animatesD.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.UP_RIGHT) { //right
            animatesUR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.UP_LEFT) { //left
            animatesUL.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN_RIGHT) { //down
            animatesDR.drawAnimation(g, x, y, 0);
        } else if (direction == Direction.DOWN_LEFT) { //down
            animatesDL.drawAnimation(g, x, y, 0);
        }

    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rectangle getLeftBound() {
        return new Rectangle((int) x + 5, (int) y + 15, 4, 31);
    }

    public Rectangle getRightBound() {
        return new Rectangle((int) x + 53, (int) y + 15, 4, 31);
    }

    public Rectangle getDownBound() {
        return new Rectangle((int) x + 16, (int) y + 50, 31, 4);
    }

    public Rectangle getUpBound() {
        return new Rectangle((int) x + 16, (int) y + 10, 31, 4);
    }


    public Rectangle getBound() {
        return new Rectangle((int) x+12, (int) y+12, 50, 40);
    }

    public void doCollision(ArrayList<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            if (getRightBound().intersects(walls.get(i).getBounds()) && direction == Direction.RIGHT) {
                velX -= velX;
                canMoveRight = false;
            } else {
                canMoveRight = true;
            }
            if (getLeftBound().intersects(walls.get(i).getBounds()) && direction == Direction.LEFT) {
                velX -= velX;

                canMoveLeft = false;
            } else {
                canMoveLeft = true;
            }
            if (getUpBound().intersects(walls.get(i).getBounds()) && direction == Direction.UP) {
                velY -=velY;
                velY -= velY;

                canMoveUp = false;
            } else {
                canMoveUp = true;
            }
            if (getDownBound().intersects(walls.get(i).getBounds()) && direction == Direction.DOWN) {
                velY -=velY;
                velY -= velY;

                canMoveDown = false;
            } else {
                canMoveDown = true;
            }

            if (getRightBound().intersects(walls.get(i).getBounds()) && (direction == Direction.UP_RIGHT || direction == Direction.DOWN_RIGHT)) {
                velX -= velX;
                canMoveRight = false;
                canMoveUpRight = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveRight = true;
                canMoveUpRight = true;
            }
            if (getLeftBound().intersects(walls.get(i).getBounds()) && (direction == Direction.UP_LEFT || direction == Direction.DOWN_LEFT)) {
                velX -= velX;

                canMoveLeft = false;
                canMoveUpLeft = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveLeft = true;
                canMoveUpLeft = true;
                canMoveLeft = true;
            }
            if (getUpBound().intersects(walls.get(i).getBounds()) && (direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT)) {
                velY -= velY;

                canMoveUp = false;
                canMoveUpRight = false;
                canMoveUpLeft = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveUp = true;
                canMoveUpRight = true;
                canMoveUpLeft = true;
            }
            if (getDownBound().intersects(walls.get(i).getBounds()) && (direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT)) {
                velY -= velY;

                canMoveDown = false;
                canMoveDownLeft = false;
                canMoveDownRight = false;
                canMoveUpRight = false;
                canMoveUpLeft = false;
            } else {
                canMoveDown = true;
            }
        }
    }


    public void doCollisionBreakable(ArrayList<BreakableWall> bwalls) {
        for (int i = 0; i < bwalls.size(); i++) {
            if (getRightBound().intersects(bwalls.get(i).getBounds()) && direction == Direction.RIGHT) {
                velX -= velX;
                canMoveRight = false;
            } else {
                canMoveRight = true;
            }
            if (getLeftBound().intersects(bwalls.get(i).getBounds()) && direction == Direction.LEFT) {
                velX -= velX;

                canMoveLeft = false;
            } else {
                canMoveLeft = true;
            }
            if (getUpBound().intersects(bwalls.get(i).getBounds()) && direction == Direction.UP) {
                velY -=velY;
                velY -= velY;

                canMoveUp = false;
            } else {
                canMoveUp = true;
            }
            if (getDownBound().intersects(bwalls.get(i).getBounds()) && direction == Direction.DOWN) {
                velY -=velY;
                velY -= velY;

                canMoveDown = false;
            } else {
                canMoveDown = true;
            }

            if (getRightBound().intersects(bwalls.get(i).getBounds()) && (direction == Direction.UP_RIGHT || direction == Direction.DOWN_RIGHT)) {
                velX -= velX;
                canMoveRight = false;
                canMoveUpRight = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveRight = true;
                canMoveUpRight = true;
            }
            if (getLeftBound().intersects(bwalls.get(i).getBounds()) && (direction == Direction.UP_LEFT || direction == Direction.DOWN_LEFT)) {
                velX -= velX;

                canMoveLeft = false;
                canMoveUpLeft = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveLeft = true;
                canMoveUpLeft = true;
                canMoveLeft = true;
            }
            if (getUpBound().intersects(bwalls.get(i).getBounds()) && (direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT)) {
                velY -= velY;

                canMoveUp = false;
                canMoveUpRight = false;
                canMoveUpLeft = false;
                canMoveDownRight = false;
                canMoveDownLeft = false;
            } else {
                canMoveUp = true;
                canMoveUpRight = true;
                canMoveUpLeft = true;
            }
            if (getDownBound().intersects(bwalls.get(i).getBounds()) && (direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT)) {
                velY -= velY;

                canMoveDown = false;
                canMoveDownLeft = false;
                canMoveDownRight = false;
                canMoveUpRight = false;
                canMoveUpLeft = false;
            } else {
                canMoveDown = true;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double velx) {
        this.velX = velx;
    }

    public void setVelY(double vely) {
        this.velY = vely;
    }

    public boolean isCanMoveRight() {
        return canMoveRight;
    }

    public boolean isCanMoveLeft() {
        return canMoveLeft;
    }

    public boolean isCanMoveDown() {
        return canMoveDown;
    }

    public boolean isCanMoveUp() {
        return canMoveUp;
    }

}