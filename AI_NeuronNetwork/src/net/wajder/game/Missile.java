package net.wajder.game;

import net.wajder.network.Constants;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 790;
    private final int MISSILE_SPEED = 4;

    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
//        loadImage("d://game_images/missile2.png");
    	loadImage(Constants.IMAGE_PATH + "\\missile2.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            vis = false;
    }
}
