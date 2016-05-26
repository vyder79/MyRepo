package net.wajder.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Craft extends Sprite {

    private int dx;
    private int dy;
    private ArrayList<Missile> missiles;
    
    /** ilo�� rakiet na wyposa�eniu statku */
    private int missilesToUse = 5;
    
    private final int SPEED_X = 1;
    private final int SPEED_Y = 1;

    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("d://game_images/craft2.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        } else if (x > 570) {
        	x = 570;
        }

        if (y < 1) {
            y = 1;
        } else if (y > 250) {
        	y = 250;
        }
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = - SPEED_X;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED_X;
        }

        if (key == KeyEvent.VK_UP) {
            dy = - SPEED_Y;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = SPEED_Y;
        }
        
        if (key == KeyEvent.VK_SPACE) {
        	
        }
    }
    
    public void moveCalculatedByNeuralNetwork(double[] positions) {
    	
    	// askNeuralNetworkWhatToDo(positions);
    	
        if (positions[0] > 0.55) {
            dy = - SPEED_Y;
        } else if (positions[2] > 0.55) {
            dy = 0;
        } else if (positions[1] > 0.55) {
            dy = SPEED_Y;
        }
    }

    public void fire() {
    	if (missilesToUse > 0) {
    		missiles.add(new Missile(x + width, y));
    		missilesToUse--;
    	}
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

	public int getMissilesToUse() {
		return missilesToUse;
	}

	public void setMissilesToUse(int missilesToUse) {
		this.missilesToUse = missilesToUse;
	}
}