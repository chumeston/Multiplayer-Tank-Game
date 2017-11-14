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

    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;
        addWall(new Wall(100, 100, tex));
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

            tempBullet.tick();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < bulletList.size(); i++) {
            tempBullet = bulletList.get(i);
            tempBullet.render(g);
        }
        for(int i = 0; i < wallList.size(); i++) {
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

    public static ArrayList<Bullet> getBullets(){
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
