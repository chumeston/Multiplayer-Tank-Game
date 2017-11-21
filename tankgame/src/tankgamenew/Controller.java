package tankgamenew;

import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;


public class Controller extends Component {

    Game game;
    GlobalTexture tex;

    private static ArrayList<Bullet> bulletList = new ArrayList<>();
    private static ArrayList<Wall> wallList = new ArrayList<>();
    private static ArrayList<BreakableWall> bwallList = new ArrayList<>();
    private static ArrayList<Ammo> ammoList = new ArrayList<>();
    private static ArrayList<Explosion> explosions = new ArrayList<>();


    Bullet tempBullet;
    Wall tempWall;
    BreakableWall btempWall;
    Ammo ammoCrate;

    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;


        addAmmo(new Ammo(750, 100, tex));
        addAmmo(new Ammo(590, 650, tex));

        for (int i = 0; i < 3; i++) {
            addBreakWall(new BreakableWall(558 + 32 * i, 220, tex));
            addBreakWall(new BreakableWall(558 + 32 * i, 695, tex));
            addBreakWall(new BreakableWall(698 + 32 * i, 220, tex));
            addBreakWall(new BreakableWall(698 + 32 * i, 695, tex));
            addBreakWall(new BreakableWall(795 + 32 * i, 500, tex));
            addBreakWall(new BreakableWall(432 + 32 * i, 500, tex));
        }

        for (int i = 0; i < 7; i++) {
            addWall(new Wall(565 + 32 * i, 500, tex));
            addWall(new Wall(100 + 32 * i, 500, tex));
            addWall(new Wall(990 + 32 * i, 500, tex));
            addWall(new Wall(532 + 32 * i, 500, tex));
            addWall(new Wall(660, 30 + 32 * i, tex));
            addWall(new Wall(660, 700 + 32 * i, tex));
            addWall(new Wall(390, 405 + 32 * i, tex));
            addWall(new Wall(900, 405 + 32 * i, tex));

        }

        for (int i = 0; i < 42; i++) {
            addWall(new Wall(1 + 32 * i, 1, tex));
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
            addWall(new Wall(1312, 30 + 32 * i, tex));
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
                soundHit();
                game.hp1 -= 20;
                explosions.add(new Explosion(game.p.getX()+30,game.p.getY()+25,4,4+50,Color.red));

            } else if (game.p2.getBound().intersects(bulletList.get(i).getBounds())) {
                removeBullet(tempBullet);
                soundHit();
                explosions.add(new Explosion(game.p2.getX()+30,game.p2.getY()+25,4,4+50,Color.blue));
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
                soundAmmo();
                ammoListIterator.remove();
                game.ammo1+=10;

            }
            if (r2.intersects(game.p2.getBound())) {
                soundAmmo();
                ammoListIterator.remove();
                game.ammo2+=10;
            }
        }

        for(int i = 0 ; i < explosions.size();i++) {
            boolean remove = explosions.get(i).update();
            if(remove) {
                explosions.remove(i);
                i--;
            }
        }
    }

    public void render(Graphics g) {

        for(int i = 0; i < explosions.size();i++) {
            explosions.get(i).render(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);
            tempBullet.render(g);
        }

        for (int i = 0; i < wallList.size(); i++) {
            tempWall = wallList.get(i);
            tempWall.render(g);
        }

        for (int i = 0; i < ammoList.size(); i++) {
            ammoCrate = ammoList.get(i);
            ammoCrate.render(g);
        }

        for (int i = 0; i < bwallList.size(); i++) {
            btempWall = bwallList.get(i);
            btempWall.render(g);
        }

        for (Wall b : wallList) {
            Rectangle r1 = b.getBounds();
            ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
            while (bulletListIterator.hasNext()) {
                Rectangle r2 = bulletListIterator.next().getBounds();
                if (r1.intersects(r2)) {
                    soundHit();
                    double x = b.getX();
                    double y = b.getY();
                    explosions.add(new Explosion(b.x,b.y,2,4+80,Color.white));
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
                    soundHit();
                    bwallListIterator.remove();
                    explosions.add(new Explosion(bu.x,bu.getY(),4,3+50,Color.white));
                }
            }
        }


    }

    private void soundHit() { game.sound.playSound("res/bullet.wav"); }

    private void soundAmmo() { game.sound.playSound("res/ammoup.wav"); }

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

    public void removeAmmo(Ammo instance) { ammoList.remove(instance); }

    public static ArrayList<Ammo> getAmmo() {
        return ammoList;
    }

}
