package tankgamenew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    //fullscreen
    static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int gameWidth = (int) screenSize.getWidth();
    public static int gameHeight = (int) screenSize.getHeight();

    public static final int width = gameWidth;
    public static final int height = gameHeight;

    public static boolean running = false;

    private Thread thread;

    private BufferedImage gameBackground, walls, button, spritesheet, playBackground;      // game menu , button

    private BufferImageLoader loader;   // loader

    private BufferedImage metalwall;

    public BufferedImage bullet;

    Controller controls;

    Button playButton;
    GlobalTexture tex;
    Menu menu;
    Player p;
    Player p2;



    //game state
    private int gamestate;

    private void init() {

        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));

        loader = new BufferImageLoader();
        try {

            gameBackground = loader.loadImage("res/menu.png");
            button = loader.loadImage("res/spritesheetbutton.png");
            walls = loader.loadImage("res/wallspritesheet.png");
            spritesheet = loader.loadImage("res/tankspritesheet.png");
            bullet = loader.loadImage("res/bluebullet.png");
            metalwall = loader.loadImage("res/wall.png");
            playBackground = loader.loadImage("res/background.png");
        } catch ( IOException e) {
            e.printStackTrace();
        }

        tex = new GlobalTexture(this);
        controls = new Controller(this,tex); // bullet
        menu = new Menu();
        playButton = new Button(300, 350, tex).setTyped(1);

        p = new Player(width/2,height/2, tex);  // position of player
        p2 = new Player(width/2 - 40,height/2 - 40, tex);  // position of player 2 not displayed
    }

    private synchronized void start(){
        if(running) {
            return;     //true, get out of method
        }
        running = true;
        thread = new Thread(this);  // creating instance of game "this
        thread.start();
    }
    private synchronized void stop(){
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);

    }

    public void tick() { // update collision and movement
        p.tick();
        p2.tick();    // player 2 not displayed
        controls.tick();

    }

    public void render() { // BUFFER STRATEGY , load 2 images at time , load images faster, flickering of image blackwhite // does all graphics

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(4);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //play menu
        if(gamestate == 0) {
            menu.createMenu(g, gameBackground, playButton, null, null); // play button
        } else if (gamestate == 1) {

            g.drawImage(playBackground, 0 , 0, null);


            //render
            p.render(g);
            p2.render(g);
            controls.render(g);
        } else if (gamestate == 2 ) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,gameWidth,gameHeight);
        }

        g.dispose();
        bs.show();

    }



    @Override
    public void run() {
        // SHOW FPS AND TICKRATE
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //frames = 0;
                System.out.println(updates + " tick : fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("TankGame");
        Game game = new Game();
        frame.add(game);
        frame.setFocusable(true);
        frame.setUndecorated(true);

        try {
            graphicsDevice.setFullScreenWindow(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        game.start();
    }

    public BufferedImage getButtons() { return button; }
    public BufferedImage getSpritesheet() {
        return spritesheet;
    }

    public int getGW() {
        return gameWidth;
    }
    public int getGH() {
        return gameHeight;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            if (p.isCanMoveUp()) {
                p.setVelY(-5);
                p.setDirection(Direction.UP);
            }
        }
        if(key == KeyEvent.VK_A){
            if (p.isCanMoveLeft()) {
                p.setVelX(-5);
                p.setDirection(Direction.LEFT);
            }
        }
        if(key == KeyEvent.VK_S) {
            if (p.isCanMoveDown()) {
                p.setVelY(5);
                p.setDirection(Direction.DOWN);
            }
        }
        if(key == KeyEvent.VK_D) {
            if (p.isCanMoveRight()) {
                p.setVelX(5);
                p.setDirection(Direction.RIGHT);
            }
        }
        if(key == KeyEvent.VK_ESCAPE) // escape only in play game
        {
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_A){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_S){
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_D){
            p.setVelX(0);
        }
        else if (key == KeyEvent.VK_SPACE) {
            if(p.getDirection() == Direction.DOWN || p.getDirection() == Direction.UP) {
                controls.addBullet(new Bullet(p.getX() + 25, p.getY() + 10, tex, this));
            } else if (p.getDirection() == Direction.RIGHT) {
                controls.addBullet(new Bullet(p.getX() + 20, p.getY() + 20, tex, this));
            } else if (p.getDirection() == Direction.LEFT) {
                controls.addBullet(new Bullet(p.getX(), p.getY() + 20, tex, this));
            }
        }


    }


    public void mouseClicked(MouseEvent e) {
        int mouse = e.getButton();

        Rectangle r = new Rectangle(e.getX(),e.getY(),1,1);
        if( mouse == MouseEvent.BUTTON1) {
            if(r.intersects(playButton.getButtonBounds())) {
                playButton.clickButton(this);
            }
        }
    }

    public int getGamestate() {
        return gamestate;
    }

    public void setGamestate(int gamestate) {
        this.gamestate = gamestate;
    }

    public BufferedImage getWalls() {
        return walls;
    }

    public void setWalls(BufferedImage walls) {
        this.walls = walls;
    }
}
