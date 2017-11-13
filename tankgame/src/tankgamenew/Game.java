import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {


    //fullscreen
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int gameWidth = (int) screenSize.getWidth();
    public static int gameHeight = (int) screenSize.getHeight();

    public static final int width = gameWidth;
    public static final int height = gameHeight;

    public static boolean running = false;

    private Thread thread;

    private BufferedImage gameBackground, button, spritesheet;      // game menu , button

    private BufferImageLoader loader;   // loader

    public BufferedImage bullet;

    Controller controls;

    Button playButton;
    GlobalTexture tex;
    Menu menu;
    Player p;




    //game state
    private int gamestate;

    private void init() {



        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));

        loader = new BufferImageLoader();
        try {

            gameBackground = loader.loadImage("res/menu.png");
            button = loader.loadImage("res/spritesheetbutton.png");
            spritesheet = loader.loadImage("res/tankspritesheet.png");
            bullet = loader.loadImage("res/bluebullet.png");
        } catch ( IOException e) {
            e.printStackTrace();
        }

        tex = new GlobalTexture(this);
        controls = new Controller(this,tex); // bullet
        menu = new Menu();
        playButton = new Button(300, 350, tex).setTyped(1);

        p = new Player(gameWidth/2,gameHeight/2, tex);  // position of player
    }

    private synchronized void start(){
        if(running) {
            return;     //we true get out of method
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
        controls.tick();

    }

    public void render() { // BUFFER STRATEGY , load 2 images at time , load images faster, flickering of image blackwhite // does all graphics

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //play menu
        if(gamestate == 0) {
            menu.createMenu(g, gameBackground, playButton, null, null); // play button
        } else if (gamestate == 1) {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,gameWidth,gameHeight);
            p.render(g);
            controls.render(g);
        } else if (gamestate == 2 ) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,gameWidth,gameHeight);
        }
        //

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
        frame.setSize(gameWidth,gameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
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
        if(key == KeyEvent.VK_W){
            p.setVelY(-4);
            p.setDirection(1);
        }
        if(key == KeyEvent.VK_A){
            p.setVelX(-4);
            p.setDirection(3);
        }
        if(key == KeyEvent.VK_S){
            p.setVelY(4);
            p.setDirection(4);
        }
        if(key == KeyEvent.VK_D){
            p.setVelX(4);
            p.setDirection(2);
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
            if(p.getDirection() == 4 || p.getDirection() == 1) {
                controls.addBullet(new Bullet(p.getX() + 25, p.getY() + 10, tex, this));
            } else if (p.getDirection() == 2) {
                controls.addBullet(new Bullet(p.getX() + 20, p.getY() + 20, tex, this));
            } else if (p.getDirection() == 3) {
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
}
