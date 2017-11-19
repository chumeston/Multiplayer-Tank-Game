package tankgamenew;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {

    //fullscreen
    static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int gameWidth = (int) screenSize.getWidth();
    public static int gameHeight = (int) screenSize.getHeight();

    public static final int width = gameWidth;
    public static final int height = gameHeight;

    public static boolean running = false;

    public int ammo1 = 15;
    public int ammo2 = 15;

    boolean keyW = false;
    boolean keyA = false;
    boolean keyD = false;
    boolean keyS = false;

    boolean keyLeft = false;
    boolean keyRight = false;
    boolean keyDown = false;
    boolean keyUp = false;

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
    Direction direction;

    public int hp1 = 100;
    public int hp2 = 100;

    //game state
    private int gamestate;

    private void init() {

        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));

        loader = new BufferImageLoader();
        try {

            gameBackground = loader.loadImage("res/menu.png");
            button = loader.loadImage("res/spritesheetbutton.png");
            metalwall = loader.loadImage("res/wallspritesheet.png");
            spritesheet = loader.loadImage("res/tankspritesheet.png");
            bullet = loader.loadImage("res/bluebullet.png");
            walls = loader.loadImage("res/wall.png");
            playBackground = loader.loadImage("res/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        tex = new GlobalTexture(this);
        controls = new Controller(this, tex); // bullet
        menu = new Menu();
        playButton = new Button(300, 350, tex).setTyped(1);

        p = new Player(width / 2 + 100, height / 2 - 300, tex);  // position of player 1
        p2 = new Player(gameWidth / 2 - 800, gameHeight / 2 - 100, tex);  // position of player 2
    }

    private synchronized void start() {
        if (running) {
            return;     //true, get out of method
        }
        running = true;
        thread = new Thread(this);  // creating instance of game "this
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
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
        if (bs == null) {
            this.createBufferStrategy(4);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //play menu
        if (gamestate == 0) {
            menu.createMenu(g, gameBackground, playButton, null, null); // play button
        } else if (gamestate == 1) {

            g.drawImage(playBackground, 0, 0, null);

            p.render(g);
            p2.render(g);

            controls.render(g);


            if (hp1 != 0) {
                g.setColor(Color.blue);
                g.setFont(new Font("Calibri", Font.BOLD, 16));
                g.drawString("Ammo: " + ammo1, gameWidth / 2 - 820, 50);
                g.setColor(Color.gray);
                g.fillRect(2, 5, 200, 32);
                g.setColor(Color.blue);
                g.fillRect(2, 5, hp1 * 2, 32);
                g.setColor(Color.black);
                g.drawRect(2, 5, 200, 32);
            } else {
                Font font = new Font("Serif", Font.PLAIN, 36);
                g.setFont(font);
                g.setColor(Color.red);
                g.drawString("TANK RED WINS", 500, 500);
                p = null;
            }
            if (hp2 != 0) {
                g.setColor(Color.red);
                g.setFont(new Font("Calibri", Font.BOLD, 16));
                g.drawString("Ammo: " + ammo2, gameWidth / 2 - 820, gameHeight / 2 - 430);
                g.setColor(Color.gray);
                g.fillRect(2, 60, 200, 32);
                g.setColor(Color.red);
                g.fillRect(2, 60, hp2 * 2, 32);
                g.setColor(Color.black);
                g.drawRect(2, 60, 200, 32);

            } else {

                Font font = new Font("Serif", Font.PLAIN, 36);
                g.setFont(font);
                g.setColor(Color.blue);
                g.drawString("TANK BLUE WINS", 500, 500);
                p2 = null;

            }


        } else if (gamestate == 2) {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, gameWidth, gameHeight);
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
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
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

    public BufferedImage getButtons() {
        return button;
    }

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

        //tank 1 movement
        if (key == KeyEvent.VK_W) {
            if (p.isCanMoveUp()) {
                p.setVelY(-5);
                p.setDirection(Direction.UP);
                keyW = true;
            }
        }
        if (key == KeyEvent.VK_A) {
            if (p.isCanMoveLeft()) {
                p.setVelX(-5);
                p.setDirection(Direction.LEFT);
                keyA = true;
            }
        }
        if (key == KeyEvent.VK_S) {
            if (p.isCanMoveDown()) {
                p.setVelY(5);
                p.setDirection(Direction.DOWN);
                keyS = true;
            }
        }
        if (key == KeyEvent.VK_D) {
            if (p.isCanMoveRight()) {
                p.setVelX(5);
                p.setDirection(Direction.RIGHT);
                keyD = true;
            }
        }
        if (keyW == true && keyD == true) {
            if (p.isCanMoveRight()) {
                p.setVelX(5);
                p.setDirection(Direction.UP_RIGHT);

            }
        }
        if (keyW == true && keyA == true) {
            if (p.isCanMoveLeft()) {
                p.setVelX(-5);
                p.setDirection(Direction.UP_LEFT);
            }
        }
        if (keyS == true && keyD == true) {
            if (p.isCanMoveRight()) {
                p.setVelX(5);
                p.setDirection(Direction.DOWN_RIGHT);
            }
        }
        if (keyS == true && keyA == true) {
            if (p.isCanMoveLeft()) {
                p.setVelX(-5);
                p.setDirection(Direction.DOWN_LEFT);

            }
        }

        // tank 2
        if (key == KeyEvent.VK_UP) {
            if (p2.isCanMoveUp()) {
                p2.setVelY(-5);
                p2.setDirection(Direction.UP);
                keyUp = true;
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            if (p2.isCanMoveLeft()) {
                p2.setVelX(-5);
                p2.setDirection(Direction.LEFT);
                keyLeft = true;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            if (p2.isCanMoveDown()) {
                p2.setVelY(5);
                p2.setDirection(Direction.DOWN);
                keyDown = true;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (p2.isCanMoveRight()) {
                p2.setVelX(5);
                p2.setDirection(Direction.RIGHT);
                keyRight = true;
            }
        }
        if (keyUp == true && keyRight == true) {
            if (p2.isCanMoveRight()) {
                p2.setVelX(5);
                p2.setDirection(Direction.UP_RIGHT);

            }
        }
        if (keyUp == true && keyLeft == true) {
            if (p2.isCanMoveLeft()) {
                p2.setVelX(-5);
                p2.setDirection(Direction.UP_LEFT);
            }
        }
        if (keyDown == true && keyRight == true) {
            if (p2.isCanMoveRight()) {
                p2.setVelX(5);
                p2.setDirection(Direction.DOWN_RIGHT);
            }
        }
        if (keyDown == true && keyLeft == true) {
            if (p2.isCanMoveLeft()) {
                p2.setVelX(-5);
                p2.setDirection(Direction.DOWN_LEFT);

            }
        }


        if (key == KeyEvent.VK_ESCAPE) // escape only in play game
        {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //tank 1 movement
        if (key == KeyEvent.VK_W) {
            p.setVelY(0);
            keyW = false;
        } else if (key == KeyEvent.VK_A) {
            p.setVelX(0);
            keyA = false;
        } else if (key == KeyEvent.VK_S) {
            p.setVelY(0);
            keyS = false;
        } else if (key == KeyEvent.VK_D) {
            p.setVelX(0);
            keyD = false;
        }


        //tank 2 movement
        else if (key == KeyEvent.VK_LEFT) {
            p2.setVelX(0);
            keyLeft = false;
        } else if (key == KeyEvent.VK_DOWN) {
            p2.setVelY(0);
            keyDown = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            p2.setVelX(0);
            keyRight = false;
        } else if (key == KeyEvent.VK_UP) {
            p2.setVelY(0);
            keyUp = false;
        }

        //tank 1 shoot && position of bullet when shooting
        if (ammo1 >= 1) {
            if (key == KeyEvent.VK_SPACE) {

                if (p.getDirection() == Direction.DOWN) {
                    controls.addBullet(new Bullet(p.getX() + 20, p.getY() + 70, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.UP) {
                    controls.addBullet(new Bullet(p.getX() + 20, p.getY() - 30, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.RIGHT) {
                    controls.addBullet(new Bullet(p.getX() + 70, p.getY() + 20, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.LEFT) {
                    controls.addBullet(new Bullet(p.getX() - 30, p.getY() + 20, tex, this));
                    ammo1--;
                    //------------------------------------------------- POSITION OF BULLET TO SPAWN NEAR TANK
                } else if (p.getDirection() == Direction.UP_RIGHT) {
                    controls.addBullet(new Bullet(p.getX() + 70, p.getY() - 30, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.UP_LEFT) {
                    controls.addBullet(new Bullet(p.getX() - 25, p.getY() - 25, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.DOWN_LEFT) {
                    controls.addBullet(new Bullet(p.getX() - 10, p.getY() + 65, tex, this));
                    ammo1--;
                } else if (p.getDirection() == Direction.DOWN_RIGHT) {
                    controls.addBullet(new Bullet(p.getX() + 60, p.getY() + 65, tex, this));
                    ammo1--;
                }
                //-------------------------------------------------

                //tank 2 shoot && position of bullet when shooting
            }
        }
        if (ammo2 >= 1) {
            if (key == KeyEvent.VK_L) {

                if (p2.getDirection() == Direction.DOWN) {
                    controls.addBullet(new Bullet(p2.getX() + 20, p2.getY() + 60, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.UP) {
                    controls.addBullet(new Bullet(p2.getX() + 20, p2.getY() - 30, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.RIGHT) {
                    controls.addBullet(new Bullet(p2.getX() + 70, p2.getY() + 20, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.LEFT) {
                    controls.addBullet(new Bullet(p2.getX() - 30, p2.getY() + 20, tex, this));
                    ammo2--;
                    //------------------------------------------------- POSITION OF BULLET TO SPAWN NEAR TANK
                } else if (p2.getDirection() == Direction.UP_RIGHT) {
                    controls.addBullet(new Bullet(p2.getX() + 70, p2.getY() - 30, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.UP_LEFT) {
                    controls.addBullet(new Bullet(p2.getX() - 20, p2.getY() - 20, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.DOWN_LEFT) {
                    controls.addBullet(new Bullet(p2.getX() - 10, p2.getY() + 55, tex, this));
                    ammo2--;
                } else if (p2.getDirection() == Direction.DOWN_RIGHT) {
                    controls.addBullet(new Bullet(p2.getX() + 60, p2.getY() + 65, tex, this));
                    ammo2--;
                }
            }
        }

    }


    public void mouseClicked(MouseEvent e) {
        int mouse = e.getButton();

        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouse == MouseEvent.BUTTON1) {
            if (r.intersects(playButton.getButtonBounds())) {
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
