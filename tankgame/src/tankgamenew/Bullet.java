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


    @Override
    public void tick() {

        switch (facing) {
            case 1:
                y -= 10;
                break;
            case 2:
                x += 10;
                break;
            case 3:
                x -= 10;
                break;
            case 4:
                y += 10;
                break;
            case 5:
                x += 10;
                y -= 10;
                break;
            case 6:
                x -= 10;
                y -= 10;
                break;
            case 7:
                x += 10;
                y += 10;
                break;
            case 8:
                x -= 10;
                y += 10;
                break;
            default:
                break;
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

    private void soundHit() { Game.sound.playSound("res/bullet.wav"); }

    public void removeOutside() {
        x =  10000;
        y = 10000;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(game.bullet, (int) x, (int) y, null);
        update();
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x + 8, (int) y + 7, 10, 10);
    }


}