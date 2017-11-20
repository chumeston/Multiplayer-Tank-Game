package tankgamenew;

import java.awt.*;

public class Bullet extends TickingObject {

    Game game;
    int facing;

    public Bullet(double x, double y, GlobalTexture tex, Game game, int facing) {
        super(x, y, tex);
        this.game = game;
        this.facing = facing;
    }


    public void tick() {
        //BULLET DIRECTION
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


    }


    public void render(Graphics g) {
        g.drawImage(game.bullet, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x+8, (int) y+7, 10, 10);
    }


}