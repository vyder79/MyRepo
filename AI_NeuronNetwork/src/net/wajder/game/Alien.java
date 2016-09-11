package net.wajder.game;

import net.wajder.network.Constants;

public class Alien extends Sprite {

    private final int INITIAL_X = 400;
    private final int SPEED = 1;
    
    /** true jeœli alien dotrze do lewej krawêdzi okna gry */
    boolean passed = false; 

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

//        loadImage("d://game_images/alien2.png");
    	loadImage(Constants.IMAGE_PATH + "\\alien2.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            //x = ((int)(((Math.random() *200) +350) / Constants.X_DISTANCE) * Constants.X_DISTANCE);
        	x = 400;
        	y =  ((int)(((Math.random() *220) +50) / Constants.Y_DISTANCE) * Constants.Y_DISTANCE);
        	
        	passed = true;
        }

        x -= SPEED;
//        double move = Math.random();
//        if (move < 0.2) {
//        	y -= SPEED;
//        } else if (move > 0.8) {
//        	y += SPEED;
//        } else {
//        	// nie zmieniamy y
//        }
 
    }
}
