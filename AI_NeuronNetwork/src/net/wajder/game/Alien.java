package net.wajder.game;

public class Alien extends Sprite {

    private final int INITIAL_X = 800;
    private final int SPEED = 1;
    
    /** true jeœli alien dotrze do lewej krawêdzi okna gry */
    boolean passed = false; 

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

        loadImage("d://game_images/alien.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        	y = (int) (Math.random() * 580 /10 *10);
        	passed = true;
        }

        x -= SPEED;
    }
}
