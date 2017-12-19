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

    public void update() {

        for (int i = 0; i < Controller.getWalls().size(); i++) {
            if (Controller.getWalls().get(i).getBounds().intersects(this.getBounds())) {
                soundHit();
                removeOutside();
            }
        }
        for (int i = 0; i < Controller.getBreakWalls().size(); i++) {
            if (Controller.getBreakWalls().get(i).getBounds().intersects(this.getBounds())) {
                soundHit();
                removeOutside();
            }
        }
    }

    private void soundHit() { game.sound.playSound("res/bullet.wav"); }

    public void removeOutside() {
        x =  10000;
        y = 10000;
    }

    public void render(Graphics g)
    {
        g.drawImage(game.bulletTank1, (int) x, (int) y, null);
        update();
    }

    public void renderTankTwo(Graphics g) {
        g.drawImage(game.bulletTank2, (int) x, (int) y, null);
        update();
    }




    public Rectangle getBounds() {
        return new Rectangle((int) x + 8, (int) y + 7, 10, 10);
    }


}