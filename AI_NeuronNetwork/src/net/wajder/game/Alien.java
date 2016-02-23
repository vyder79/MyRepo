package net.wajder.game;

public class Alien extends Sprite {

    private final int INITIAL_X = 800;
    private final int SPEED = 1;
    
    /** true je�li alien dotrze do lewej kraw�dzi okna gry */
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
        	y =  ((int)(Math.random() *20) *30);
        	passed = true;
        }

        x -= SPEED;
        double move = Math.random();
        if (move < 0.2) {
        	y -= SPEED+1;
        } else if (move > 0.8) {
        	y += SPEED+1;
        } else {
        	// nie zmieniamy y
        }
 
    }
}
