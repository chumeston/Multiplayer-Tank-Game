import java.awt.*;

public class Button extends GameObject {

    private boolean clicked = false;
    private int type;


    public Button(double x, double y, GlobalTexture tex) {
        super(x, y, tex);
    }

    public void render(Graphics g) {
        //animate button clicked
        if(!clicked) {
            if (type == 1) {
                g.drawImage(tex.playbutton1, (int) x, (int) y, null);
            }
        }
            else if (clicked) {
                if (type == 1) {
                    g.drawImage(tex.playbutton2, (int) x, (int) y, null);
                }
            }

    }

    public Button setTyped(int type) {

        this.type = type;
        return this;
    }

    public void clickButton(Game game) {
        clicked = true;
        if(type == 1 ) {
            game.setGamestate(1);
        }
        if(type == 2) {
            game.setGamestate(2);
        }
    }

    public Rectangle getButtonBounds(){
        return new Rectangle((int)x,(int)y,200,50);
    }
}
