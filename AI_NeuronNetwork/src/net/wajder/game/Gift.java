package net.wajder.game;

import net.wajder.network.Constants;

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

//        loadImage("d://game_images/star.png");
    	loadImage(Constants.IMAGE_PATH + "\\star.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = (int) ((Math.random() * 500) + 500);
        	y = (int) (((Math.random() * 250) / Constants.Y_DISTANCE) * Constants.Y_DISTANCE);
        	passed = true;
        }

        x -= SPEED;
    }
}
