package tankgamenew;

import java.awt.*;
import java.util.ArrayList;

public class Player extends TickingObject {

    private double velX, velY = 0;
    private ArrayList<Wall> wallArrayList = Controller.getWalls();
    private boolean canMoveRight, canMoveLeft, canMoveDown, canMoveUp, moving;

    Animation animates; // up
    Animation animatesR;
    Animation animatesL;
    Animation animatesD;

    private Direction direction;
    private Controller control;

    public Player(double x, double y, GlobalTexture tex) {
        super(x, y, tex);
        //velX = 3;
        //velY= 2;
        direction = Direction.RIGHT;  // starting angle position
        //speed 4 frame
        animates = new Animation(2, tex.player[0], tex.player[1], tex.player[2] ,tex.player[3]);
        animatesR = new Animation(2, tex.playerRight[0], tex.playerRight[1], tex.playerRight[2], tex.playerRight[3]);
        animatesL = new Animation(2, tex.playerLeft[0], tex.playerLeft[1], tex.playerLeft[2], tex.playerLeft[3]);
        animatesD = new Animation(2, tex.playerDown[0], tex.playerDown[1], tex.playerDown[2], tex.playerDown[3]);
    }

    @Override
    public void tick() {

        x+=velX;
        y+=velY;

        // TANK COLLISION SCREEN DETECTION
        if(x < 0) {
            x = 0;
        }
        if(x>Game.gameWidth-64) {
            x = Game.gameWidth - 64;
        }
        if(y> Game.gameHeight-64) {
            y=Game.gameHeight - 64; // -64
        }
        if(y <0 ) {
            y = 0;
        }

        //----
        //doCollision(wallArrayList); // if this method is in here not wall metal object collide with player fully

            if (direction == Direction.UP) {
                animates.animate();
            } else if (direction == Direction.RIGHT) {
                animatesR.animate();
            } else if (direction == Direction.LEFT) {
                animatesL.animate();
            } else if (direction == Direction.DOWN) {
                animatesD.animate();
            }
    }

    @Override
    public void render(Graphics g) {

        //----
        // collides with all wall if method inside render but gets stuck
        // maybe because of pixel clipping or keeps hitting rectangle settting velx and vely to 0


        doCollision(wallArrayList);

        if(direction == Direction.UP) {
            animates.drawAnimation(g,x,y,0);
        } else if (direction == Direction.RIGHT) { //right
            animatesR.drawAnimation(g,x,y,0);;
        } else if (direction == Direction.LEFT) { //left
            animatesL.drawAnimation(g,x,y,0);
        } else if (direction == Direction.DOWN) { //down
            animatesD.drawAnimation(g,x,y,0);
        }

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() { return direction; }

    // was set to 32 width and height which caused clipping inside wall and bugged collision , changed to 64
    public Rectangle getLeftBound() { return new Rectangle((int) x, (int) y, 64, 64); }
    public Rectangle getRightBound() { return new Rectangle((int) x, (int) y, 64, 64); }
    public Rectangle getUpBound() {return new Rectangle((int) x, (int) y, 64, 64); }
    public Rectangle getDownBound() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    //COLLISION WALL
    public void doCollision(ArrayList<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            if (getRightBound().intersects(walls.get(i).getBounds()) && direction == Direction.RIGHT) {
                velX = 0;
                canMoveRight = false;
            } else {
                canMoveRight = true;
            }
            if (getLeftBound().intersects(walls.get(i).getBounds()) && direction == Direction.LEFT) {
                velX = 0;
                canMoveLeft = false;
            } else {
                canMoveLeft = true;
            }
            if (getUpBound().intersects(walls.get(i).getBounds()) && direction == Direction.UP) {
                velY = 0;
                canMoveUp = false;
            } else {
                canMoveUp = true;
            }
            if (getDownBound().intersects(walls.get(i).getBounds()) && direction == Direction.DOWN) {
                velY = 0;
                canMoveDown = false;
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
