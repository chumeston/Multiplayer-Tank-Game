package tankgamenew;

import java.awt.*;

public class Bullet extends TickingObject {

    Game game;

    int facing;


    public Bullet(double x, double y, GlobalTexture tex, Game game, int facing) {
        super(x, y, tex);
        this.game = game;
<<<<<<< HEAD
        this.facing = facing;
=======

        // shooting and direction of bullet player 1

        if (game.p.getDirection() == Direction.UP) {
            facing = 1;
        } else if (game.p.getDirection() == Direction.RIGHT) {
            facing = 2;
        } else if (game.p.getDirection() == Direction.LEFT) {
            facing = 3;
        } else if (game.p.getDirection() == Direction.DOWN) {
            facing = 4;
        } else if (game.p.getDirection() == Direction.UP_RIGHT) {
            facing = 5;
        } else if (game.p.getDirection() == Direction.UP_LEFT) {
            facing = 6;
        } else if (game.p.getDirection() == Direction.DOWN_RIGHT) {
            facing = 7;
        } else if (game.p.getDirection() == Direction.DOWN_LEFT) {
            facing = 8;
        }

        // shooter and direction of bullet  player 2

        else if (game.p2.getDirection() == Direction.UP) {
            facing = 1;
        } else if (game.p2.getDirection() == Direction.RIGHT) {
            facing = 2;
        } else if (game.p2.getDirection() == Direction.LEFT) {
            facing = 3;
        } else if (game.p2.getDirection() == Direction.DOWN) {
            facing = 4;
        } else if (game.p2.getDirection() == Direction.UP_RIGHT) {
            facing = 5;
        } else if (game.p2.getDirection() == Direction.UP_LEFT) {
            facing = 6;
        } else if (game.p2.getDirection() == Direction.DOWN_RIGHT) {
            facing = 7;
        } else if (game.p2.getDirection() == Direction.DOWN_LEFT) {
            facing = 8;
        }

>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
    }

    public void tick() {
<<<<<<< HEAD
=======
        //BULLET DIRECTION
>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
        if (facing == 1) {  // 1 is up , x & y and speed
            y -= 10;
        } else if (facing == 2) { //2 is right
            x += 10;
        } else if (facing == 3) {  // 3 is left
            x -= 10;
        } else if (facing == 4) {  // 4 is down
            y += 10;
        } else if (facing == 5) { //2 is UP right
            x += 10;
            y -= 10;
        } else if (facing == 6) {  // 3 is  UP left
            x -= 10;
            y -= 10;
        } else if (facing == 7) {  // 4 is down right
            x += 10;
            y += 10;
        } else if (facing == 8) {  // 4 is down left
            x -= 10;
            y += 10;
        }

<<<<<<< HEAD
=======

>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
    }

    public void render(Graphics g) {
        g.drawImage(game.bullet, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 25, 25);
    }

}
