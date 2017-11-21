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

        if (facing == 1) {
            y -= 10;
        } else if (facing == 2) {
            x += 10;
        } else if (facing == 3) {
            x -= 10;
        } else if (facing == 4) {
            y += 10;
        } else if (facing == 5) {
            x += 10;
            y -= 10;
        } else if (facing == 6) {
            x -= 10;
            y -= 10;
        } else if (facing == 7) {
            x += 10;
            y += 10;
        } else if (facing == 8) {
            x -= 10;
            y += 10;
        }
    }


    public void render(Graphics g) {
        g.drawImage(game.bullet, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x + 8, (int) y + 7, 10, 10);
    }


}