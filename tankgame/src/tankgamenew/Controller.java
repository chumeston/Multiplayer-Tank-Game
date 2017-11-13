package tankgamenew;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    Game game;
    GlobalTexture tex;

    private static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

    Bullet tempBullet;

    public Controller(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;

    }

    public void tick(){

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

}
