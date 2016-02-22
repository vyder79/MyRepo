package net.wajder.game;

public class Gift extends Sprite {

    private final int INITIAL_X = 800;
    private final int SPEED = 2;
    
    /** true jeœli alien dotrze do lewej krawêdzi okna gry */
    boolean passed = false; 

    public Gift(int x, int y) {
        super(x, y);

        initGift();
    }

    private void initGift() {

        loadImage("d://game_images/star.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = (int) (Math.random() * 1200 + 3000);
        	y = (int) (Math.random() * 580 /10 *10);
        	passed = true;
        }

        x -= SPEED;
    }
}
