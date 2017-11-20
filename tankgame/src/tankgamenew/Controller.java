package tankgamenew;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Controller {

    Game game;
    GlobalTexture tex;

    private static ArrayList<Bullet> bulletList = new ArrayList<>();
    private static ArrayList<Wall> wallList = new ArrayList<>();
    private static ArrayList<BreakableWall> bwallList = new ArrayList<>();
    private static ArrayList<Ammo> ammoList = new ArrayList<>();
    private static ArrayList<Explosion> explodeList = new ArrayList<>();


    Bullet tempBullet;
    Wall tempWall;
    BreakableWall btempWall;
    Ammo ammoCrate;
    Explosion explodeBullet;


    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;


        //drawing walls
        addAmmo(new Ammo(750 , 100, tex));
        addAmmo(new Ammo(590 , 650, tex));

        for (int i = 0; i < 3; i++) {
            addBreakWall(new BreakableWall(558 + 32 * i, 220, tex));
            addBreakWall(new BreakableWall(558 + 32 * i, 695, tex));
            addBreakWall(new BreakableWall(698 + 32 * i, 220, tex));
            addBreakWall(new BreakableWall(698 + 32 * i, 695, tex));
            addBreakWall(new BreakableWall(795 + 32 * i, 500, tex));
            addBreakWall(new BreakableWall(432 + 32 * i, 500, tex));
        }

        for (int i = 0; i < 7; i++) {
            addWall(new Wall(565 + 32 * i, 500, tex));  // horizontal
            addWall(new Wall(100 + 32 * i, 500, tex));
            addWall(new Wall(990 + 32 * i, 500, tex));
            addWall(new Wall(532 + 32 * i, 500, tex));
            addWall(new Wall(660, 30 + 32 * i, tex)); //vertical
            addWall(new Wall(660, 700 + 32 * i, tex));
            addWall(new Wall(390, 405 + 32 * i, tex));
            addWall(new Wall(900, 405 + 32 * i, tex));

        }

        for (int i = 0; i < 42; i++) {
            addWall(new Wall(1 + 32 * i, 1, tex));  // horizontal     1 start point , 50 spacing
            addWall(new Wall(1 + 32 * i, 992, tex));
        }

        for (int i = 0; i < 7; i++) {
            addWall(new Wall(80 + 32 * i, 702, tex));
            addWall(new Wall(80 + 32 * i, 286, tex));
            addWall(new Wall(1000 + 32 * i, 702, tex));
            addWall(new Wall(1000 + 32 * i, 286, tex));
            addWall(new Wall(520, 100 + 32 * i, tex));
            addWall(new Wall(520, 600 + 32 * i, tex));
            addWall(new Wall(800, 100 + 32 * i, tex));
            addWall(new Wall(800, 600 + 32 * i, tex));
        }

        for (int i = 0; i < 30; i++) {
            addWall(new Wall(1312, 30 + 32 * i, tex)); //vertical
            addWall(new Wall(0, 30 + 32 * i, tex));
        }


    }

    public void tick() {

        for (int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);

            if (tempBullet.getY() < 0) {
                removeBullet(tempBullet);
            } else if (tempBullet.getY() > game.getGH()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() > game.getGW()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() < 0) {
                removeBullet(tempBullet);


            } else if (game.p.getBound().intersects(bulletList.get(i).getBounds())) {
                removeBullet(tempBullet);
                game.hp1 -= 20;

            } else if (game.p2.getBound().intersects(bulletList.get(i).getBounds())) {
                removeBullet(tempBullet);
                game.hp2 -= 20;
            }


            if (tempBullet.getY() < 0) {
                removeBullet(tempBullet);
            } else if (tempBullet.getY() > game.getGH()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() > game.getGW()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() < 0) {
                removeBullet(tempBullet);
            }

            tempBullet.tick();

        }

        //collision tank
        if (game.p2.getBound().intersects(game.p.getBound())) {
            game.p.setVelX(-game.p.getVelX());
            game.p.setVelY(-game.p.getVelY());
            game.p2.setVelX(-game.p2.getVelX());
            game.p2.setVelY(-game.p2.getVelY());
        }

        ListIterator<Ammo> ammoListIterator = ammoList.listIterator();
        while (ammoListIterator.hasNext()) {
            Rectangle r2 = ammoListIterator.next().getBounds();
            if (r2.intersects(game.p.getBound())) {
                ammoListIterator.remove();
                game.ammo1+=10;
            }
        }

    }

    // BULLET -> WALL COLLISION

    public void render(Graphics g) {

        //animates.drawAnimation(g, 460 , 460, 0);

        for (int i = 0; i < bulletList.size(); i++) {   //RENDER BULLET
            tempBullet = bulletList.get(i);
            tempBullet.render(g);

        }

        for (int i = 0; i < wallList.size(); i++) {     //RENDER WALL
            tempWall = wallList.get(i);
            tempWall.render(g);
        }

        for (int i = 0; i < ammoList.size(); i++) {     //RENDER WALL
            ammoCrate = ammoList.get(i);
            ammoCrate.render(g);
        }

        for (int i = 0; i < explodeList.size(); i++) {     //RENDER WALL
            explodeBullet = explodeList.get(i);
            explodeBullet.render(g);

        }
        for (int i = 0; i < bwallList.size(); i++) {     //RENDER BREAK WALL
            btempWall = bwallList.get(i);
            btempWall.render(g);


        }

        for (Wall b : wallList) {
            Rectangle r1 = b.getBounds();
            ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
            while (bulletListIterator.hasNext()) {
                Rectangle r2 = bulletListIterator.next().getBounds();
                if (r1.intersects(r2)) {

                    double x = b.getX();
                    double y = b.getY();
                    addExplode(new Explosion(x,y,tex));
                    bulletListIterator.remove();
                }
            }
        }

        for (Bullet bu : bulletList) {
            Rectangle r1 = bu.getBounds();

            ListIterator<BreakableWall> bwallListIterator = bwallList.listIterator();
            while (bwallListIterator.hasNext()) {
                Rectangle r2 = bwallListIterator.next().getBounds();
                if (r1.intersects(r2)) {

                    bwallListIterator.remove();
                }
            }
        }


    }

    public void addBullet(Bullet instance) {
        bulletList.add(instance);
    }

    public void removeBullet(Bullet instance) {
        bulletList.remove(instance);
    }

    public static ArrayList<Bullet> getBullets() {
        return bulletList;
    }

    public void addWall(Wall instance) {
        wallList.add(instance);
    }

    public void removeWall(Wall instance) {
        wallList.remove(instance);
    }

    public static ArrayList<Wall> getWalls() {
        return wallList;
    }

    public void addBreakWall(BreakableWall instance) {
        bwallList.add(instance);
    }

    public void removeBreakWall(BreakableWall instance) {
        bwallList.remove(instance);
    }

    public static ArrayList<BreakableWall> getBreakWalls() {
        return bwallList;
    }





    public void addAmmo(Ammo instance) {
        ammoList.add(instance);
    }

    public void removeAmmo(Ammo instance) {
        ammoList.remove(instance);
    }

    public static ArrayList<Ammo> getAmmo() {
        return ammoList;
    }


    public void addExplode(Explosion instance) {
        explodeList.add(instance);
    }

    public void removeExplode(Explosion instance) { explodeList.remove(instance); }

    public static ArrayList<Explosion> getExplosion() {
        return explodeList;
    }
}
