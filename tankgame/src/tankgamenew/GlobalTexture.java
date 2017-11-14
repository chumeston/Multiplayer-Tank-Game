package tankgamenew;

import java.awt.image.BufferedImage;

public class GlobalTexture {

    public BufferedImage[] player = new BufferedImage[4]; //up
    public BufferedImage[] playerLeft = new BufferedImage[4]; //left
    public BufferedImage[] playerRight = new BufferedImage[4]; //right
    public BufferedImage[] playerDown = new BufferedImage[4]; //down

    public BufferedImage playbutton1 , playbutton2, brickWall;

    private SpriteSheetButton button;
    private SpriteSheet spriteSheet1;
    private SpriteSheet wallSpriteSheet;


    public GlobalTexture(Game game) {
        button = new SpriteSheetButton(game.getButtons());
        spriteSheet1 = new SpriteSheet(game.getSpritesheet());
        wallSpriteSheet = new SpriteSheet(game.getWalls());
        getSprites();
    }
    public void getSprites() {
        playbutton1 = button.getSprite(1, 1,200,50); // w and h of image
        playbutton2 = button.getSprite(1,2,200,50);

        //controls

        // exit

        //player  , animation , spritesheet has array form
        player[0] = spriteSheet1.getSprite(1,1,64,64);
        player[1] = spriteSheet1.getSprite(2,1,64,64);
        player[2] = spriteSheet1.getSprite(3,1,64,64);
        player[3] = spriteSheet1.getSprite(4,1,64,64);

        playerLeft[0] = spriteSheet1.getSprite(1,4,64,64);
        playerLeft[1] = spriteSheet1.getSprite(2,4,64,64);
        playerLeft[2] = spriteSheet1.getSprite(3,4,64,64);
        playerLeft[3] = spriteSheet1.getSprite(4,4,64,64);

        playerRight[0] = spriteSheet1.getSprite(1,2,64,64);
        playerRight[1] = spriteSheet1.getSprite(2,2,64,64);
        playerRight[2] = spriteSheet1.getSprite(3,2,64,64);
        playerRight[3] = spriteSheet1.getSprite(4,2,64,64);

        playerDown[0] = spriteSheet1.getSprite(1,3,64,64);
        playerDown[1] = spriteSheet1.getSprite(2,3,64,64);
        playerDown[2] = spriteSheet1.getSprite(3,3,64,64);
        playerDown[3] = spriteSheet1.getSprite(4,3,64,64);

        brickWall = wallSpriteSheet.getSprite(1, 1, 32, 32);
    }
}
