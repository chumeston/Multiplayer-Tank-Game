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

    Bullet tempBullet;
    Wall tempWall;



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
        // its check same array of bullet and wall position so it wont remove , null because remove all walllist
        //for loop walllist > walllist(i) intersect bulletlist get i, remove bullet
        // tempWall is null and temp bullet is null
        // no bullet size so not printing because space not pressed yet, space creates bullet
        // compares i with bullet i in same position but it wont work  -  bulletArrayList.get(i)  is null
        //if(tempBullet.getBounds().intersects(tempWall.getBounds())){  works because dont check  bulletList



        //collision tank
        if (game.p2.getBound().intersects(game.p.getBound())) {
            game.p.setVelX(-game.p.getVelX());
            game.p.setVelY(-game.p.getVelY());
            game.p2.setVelX(-game.p2.getVelX());
            game.p2.setVelY(-game.p2.getVelY());
        }
    }

    // BULLET -> WALL COLLISION

    public void render(Graphics g) {

        for (int i = 0; i < bulletList.size(); i++) {   //RENDER BULLET
            tempBullet = bulletList.get(i);
            tempBullet.render(g);

        }

        for (int i = 0; i < wallList.size(); i++) {     //RENDER WALL
            tempWall = wallList.get(i);
            tempWall.render(g);
        }
        for(Wall b : wallList){
            Rectangle r1 = b.getBounds();
            ListIterator<Bullet> bulletListIterator = bulletList.listIterator();
        while (bulletListIterator.hasNext()) {
            Rectangle r2 = bulletListIterator.next().getBounds();
            if(r1.intersects(r2)) {
                bulletListIterator.remove();
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

}
