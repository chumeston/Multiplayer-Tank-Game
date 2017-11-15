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

    private Direction direction;

    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;

        // drawing the walls ,   BUG - multiple walls cant detect collision
        addWall(new Wall(1200, 550, tex));
        addWall(new Wall(700, 550, tex));
        addWall(new Wall(1000, 450, tex));
        addWall(new Wall(1000, 650, tex));

        System.out.println(wallList.size());

    }

    public void tick() {

        for(int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);

            if(tempBullet.getY() < 0){
                removeBullet(tempBullet);
            } else if(tempBullet.getY() > game.getGH()) {
                removeBullet(tempBullet);
            } else if(tempBullet.getX() > game.getGW()) {
                removeBullet(tempBullet);
            } else if(tempBullet.getX() < 0) {
                removeBullet(tempBullet);
            }

            if(tempBullet.getY() < 0){
                removeBullet(tempBullet);
            } else if(tempBullet.getY() > game.getGH()) {
                removeBullet(tempBullet);
            } else if(tempBullet.getX() > game.getGW()) {
                removeBullet(tempBullet);
            } else if(tempBullet.getX() < 0) {
                removeBullet(tempBullet);
            }

            tempBullet.tick();

        }
    }

    // this only works in one wall object , removes bullet
    public void doCollisionBullet(ArrayList<Bullet> bull) {
        for (int i = 0; i < bull.size(); i++) {
            if (tempWall.getBounds().intersects(bull.get(i).getBounds())) {
                removeBullet(tempBullet);
                //removeWall(tempWall);    --  removes wall
            }
        }
    }


    public void render(Graphics g){
        for(int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);
            tempBullet.render(g);
        }
        for(int i = 0; i < wallList.size(); i++) {
            tempWall = wallList.get(i);
            tempWall.render(g);                 //renders wall
            doCollisionBullet(bulletList);      //bullet collision with metal wall removes bullet
            ;
        }
    }

    public void addBullet(Bullet instance) {
        bulletList.add(instance);
    }

    public void removeBullet(Bullet instance) {
        bulletList.remove(instance);
    }

    public static ArrayList<Bullet> getBullets(){
        return bulletList;
    }

    public void addWall(Wall instance) {
        wallList.add(instance);
    }

    public void removeWall(Wall instance) {
        wallList.remove(instance);
    }

    public static ArrayList<Wall> getWalls() { return wallList; }

    public static ArrayList<Bullet> getBullet() { return bulletList; }

}
