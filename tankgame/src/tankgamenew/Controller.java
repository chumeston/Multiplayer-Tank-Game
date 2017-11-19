package tankgamenew;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    Game game;
    GlobalTexture tex;


    private static ArrayList<Bullet> bulletList = new ArrayList<>();
    private static ArrayList<Wall> wallList = new ArrayList<>();

    Bullet tempBullet;
    Wall tempWall;
    Player tempPlayer;


    private Direction direction;

    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;

        //drawing walls

        for (int i = 0; i < 33; i++) {

            addWall(new Wall(1 + 40 * i, 1, tex));  // horizontal     1 start point , 50 spacing
            addWall(new Wall(1 + 40 * i, 1000, tex));

        }

        for (int i = 0; i < 10; i++) {
            addWall(new Wall(40 + 40 * i, 700, tex));
            addWall(new Wall(40 + 40 * i, 300, tex));
            addWall(new Wall(700, 100 + 35 * i, tex));
            addWall(new Wall(900, 100 + 35 * i, tex));
            addWall(new Wall(900, 600 + 35 * i, tex));
            addWall(new Wall(700, 600 + 35 * i, tex));
        }

        for (int i = 0; i < 27; i++) {
            addWall(new Wall(1300, 50 + 35 * i, tex));
            addWall(new Wall(1, 50 + 35 * i, tex));
        }
        System.out.println(wallList.size());

    }

    public void tick() {
<<<<<<< HEAD
        for(int i = 0; i < bulletList.size(); i++) {
=======

        for (int i = 0; i < bulletList.size(); i++) {
>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
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
                game.hp1-=20;

            } else if (game.p2.getBound().intersects(bulletList.get(i).getBounds())) {
                removeBullet(tempBullet);
                game.hp2 -=20;

            } else if (game.p2.getBound().intersects(game.p2.getBound())) {
                System.out.println("TANK INTERSECTS");
            }
<<<<<<< HEAD
=======


            if (tempBullet.getY() < 0) {
                removeBullet(tempBullet);
            } else if (tempBullet.getY() > game.getGH()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() > game.getGW()) {
                removeBullet(tempBullet);
            } else if (tempBullet.getX() < 0) {
                removeBullet(tempBullet);
            }

>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
            tempBullet.tick();
        }
    }

    // BULLET -> WALL COLLISION

    public void render(Graphics g) {

        for (int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);

            if (tempWall.getBounds().intersects(bulletList.get(i).getBounds())) {
                removeBullet(tempBullet);

            }

            tempBullet.render(g);
        }
<<<<<<< HEAD

        for(int i = 0; i < wallList.size(); i++) {
=======
        for (int i = 0; i < wallList.size(); i++) {
>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
            tempWall = wallList.get(i);
            tempWall.render(g);

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

<<<<<<< HEAD
    public static ArrayList<Bullet> getBullet() { return bulletList; }
}
=======
    public static ArrayList<Bullet> getBullet() {
        return bulletList;
    }
}
>>>>>>> 610ae3a251ad75864973e72e98714bcb769fb1b9
