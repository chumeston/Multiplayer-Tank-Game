package tankgamenew;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;

    private BufferedImage currentImage;
    public Animation(int speed, BufferedImage img1) {
        this.speed = speed;
        this.img1 = img1;
        frames =1;
    }
    //4 frames
    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        frames =4;
    }
    //3 frames
    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3) {
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        frames =3;
    }

    public void animate() {
        index++;
        if(index>speed) {
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        //case 1 frame
        switch(frames) {
            case 1:
                if(count == 0) {
                    currentImage = img1;
                }
                count++;
                if(count > frames) {
                    count = 0;
                }
                break;

            case 3:
                if(count ==0 ){
                    currentImage = img1;
                }
                if(count ==1 ){
                    currentImage = img2;
                }
                if(count ==2 ){
                    currentImage = img3;
                }
                count++;
                if(count > frames) {
                    count = 0;
                }
                break;
            case 4:
                if(count ==0 ){
                    currentImage = img1;
                }
                if(count ==1 ){
                    currentImage = img2;
                }
                if(count ==2 ){
                    currentImage = img3;
                }
                if(count ==3 ){
                    currentImage = img4;
                }
                count++;
                if(count > frames) {
                    count = 0;
                }
                break;
        }
    }

    public void drawAnimation(Graphics g, double x, double y, int offset) {
        g.drawImage(currentImage,(int)x-offset,(int)y, null);
    }

}
