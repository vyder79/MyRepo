package net.wajder.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

import net.wajder.network.Constants;
import net.wajder.network.TrainedNetwork;
import net.wajder.network.TreningPattern;

public class Board extends JPanel implements ActionListener {
	
	double cy = 0;
	long patternLimiter = 0;

    private Timer timer; 					// 45  -  2,5,0,1,2,9,2,0,3,1,4,6,4,10,2,10,11,0,0,2,7,0,1,6,3,1,0,7,1,8,5,2,1,0,1,0,5,0,2,2,8,0,6,11,5,1,0,0,2,1
    private Craft craft;					// 300 -  7,1,0,4,14,1,17,2,9,6,0,4,9,1,9,10,10,0,1,8,0,5,2,0,1,1,5,4,6,0,1,4,3,1,2,3,4,0,5,0,0,1,4,3,0,1,6,1,0,10
    private ArrayList<Alien> aliens;		// 800 -  0,1,7,9,2,2,2,3,0,0,0,2,0,3,1,2,1,0,3,0,1,0,1,1,0,4,1,1,14,8,0,1,3,7,0,4,1,2,0,0,8,0,0,2,1,7,0,0,1,1,4
    private ArrayList<Gift> gifts;			// 1000 - 3,9,0,3,0,1,0,0,6,3,0,0,7,6,1,5,0,1,0,15,1,0,0,0,5,0,0,4,0,0,2,5,5,0,0,4,0,0,15,3,5,0,1,1,11,7,2,0,6,4,4,1,12 
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 120;
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 300;
    private final int DELAY = 10;
    
    private int points = 0;
    private final int DESTROY_ALIEN_POINTS = 10;
    private final int CATCH_GIFT_POINTS = 100;
    private final int PASS_ALIEN_POINTS = 1;
    
    private double[] positions = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
    //private int initialRandomX = getRandX();
    private int initialRandomX = 240;

    private TrainedNetwork trainedNetwork = new TrainedNetwork();
    
    private final int[][] pos = {
        	{initialRandomX, getRandY()},{initialRandomX+Constants.X_DISTANCE, getRandY()}, {initialRandomX+Constants.X_DISTANCE*2, getRandY()},
        	{initialRandomX+Constants.X_DISTANCE*3, getRandY()},{initialRandomX+Constants.X_DISTANCE*4, getRandY()}, {initialRandomX+Constants.X_DISTANCE*5, getRandY()},
        	{initialRandomX+Constants.X_DISTANCE*6, getRandY()}, {initialRandomX+Constants.X_DISTANCE*7, getRandY()}
        };
    
//    private final int[][] pos = {
//    	{getRandX(), getRandY()},{getRandX(), getRandY()}, {getRandX(), getRandY()},
//    	{getRandX(), getRandY()},{getRandX(), getRandY()}, {getRandX(), getRandY()},
//    	{getRandX(), getRandY()}, {getRandX(), getRandY()}
//    };
    
    private final int[][] posG = {
        	{getRandXGift(), getRandY()}
        };
    
    private int getRandX() {
    	return (int) ((((Math.random() * 100)  + 100) / Constants.X_DISTANCE) * Constants.X_DISTANCE);
    }
    
    private int getRandXGift() {
    	return (int) (Math.random() * 10000 + 1000);
    }
    
    private int getRandY() {
    	return ((int)(((Math.random() *200)  +50) / Constants.Y_DISTANCE) *  Constants.Y_DISTANCE);
    }

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();
        initGifts();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }
    
    public void initGifts() {
        gifts = new ArrayList<>();

        for (int[] p : posG) {
            gifts.add(new Gift(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
            System.out.println("GAME OVER, points: " + points);
            //initBoard();
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
        
        for (Gift a : gifts) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens: " + aliens.size(), 5, 15);
        g.drawString("Points: " + points, 95, 15);
        //g.drawString("Missiles: " + craft.getMissilesToUse(), 185, 15);
        
        g.drawRect(0, 0, 400, 300);
    }

    private void drawGameOver(Graphics g) {

        String msg = "You have " + points + " points :)";
        Font small = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateCraft();
        updateMissiles();
        updateAliens();
        updateGifts();
        autoMoving();
        checkCollisions();
        addPointsWhenPassedAlien();
        repaint();
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateCraft() {

        if (craft.isVisible()) {
            craft.move();
        }
    }

    private void updateMissiles() {

        ArrayList<Missile> ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        if (aliens.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }
    
    private void updateGifts() {

        for (int i = 0; i < gifts.size(); i++) {

            Gift a = gifts.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                gifts.remove(i);
            	gifts.add(new Gift(2000, (int) Math.random() * 200 + 70));
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

        for (Alien alien : aliens) {
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }
        
        for (Gift gift : gifts) {
            Rectangle r4 = gift.getBounds();

            if (r3.intersects(r4)) {
                gift.setVisible(false);
                points += CATCH_GIFT_POINTS;
            }
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                    points += DESTROY_ALIEN_POINTS;
                }
            }
        }
    }

    private void addPointsWhenPassedAlien() {
		for (Alien alien : aliens) {
			if (alien.passed) {
				alien.passed = false;
				points += PASS_ALIEN_POINTS;
			}
		}
    }
    
    /**
     * Sterowanie za pomoc� sieci neuronowej.Na wej�ciu po�o�enie oraz
     * pr�dko�ci obiekt�w na planszy, na wyj�ciu sieci kierunek ruchu: 
     * 1 = w d�, -1 = w g�r�, 0 = brak ruchu.
     */
    private void autoMoving() {
    	
    	int i = 0;
    	double[] positions = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    	this.positions = positions;
    	positions[i] = craft.getX(); // 0
    	positions[++i] = craft.getY(); // 1
    	for (Sprite alien : aliens) {
    		positions[++i] = alien.getX(); // 2, 4, 6, ...
    		positions[++i] = alien.getY(); // 3, 5, 7, ...
    	}
    	
    	//i = 13;
    	for (Gift gift : gifts) {
    		positions[++i] = gift.getX(); // 14, 16, 18
    		positions[++i] = gift.getY(); // 15, 17, 19
    	}
    	
    	if (patternLimiter % 25 == 0) {
	    	//System.out.print("treningPatterns.add(new TreningPattern(new double[] {" + Arrays.toString(this.positions) + ", new double[] ");
    		System.out.print("treningPatterns.add(new TreningPattern(new double[] {" + arrayToString() + "}, new double[] ");
    	
	    	trainedNetwork.getNet().work(positions);
	    	
	    	double y = craft.getY();
	    	
	    	final double[] cOutUp = {1.0, 0.0, 0.0};
	    	final double[] cOutStay = {0.0, 1.0, 0.0};
	    	final double[] cOutDown = {0.0, 0.0, 1.0};
	    	
	    	double[] cOut;
	    	String stringCout = "";
	    	
	    	if (cy < y) {
	    		cy = y;
	    		cOut = cOutDown;
	    		stringCout = "{0.0, 0.0, 1.0}";
	    	} else if (cy > y) {
	    		cy = y;
	    		cOut = cOutUp;
	    		stringCout = "{1.0, 0.0, 0.0}";
	    	} else {
	    		cy = y;
	    		cOut = cOutStay;
	    		stringCout = "{0.0, 1.0, 0.0}";
	    	}
	    	
//	    	System.out.println(Arrays.toString(cOut) + "}));");
	    	System.out.println(stringCout + "));");
    	}
    	patternLimiter++;
    	
    	//double moveX = 0.0;
    	//double moveY = Math.random() * 2 - 1;
    	//System.out.println(moveY);
    	//if (craft.getY() > 270) moveY = 0.0;
    	if (!Constants.LEARN) {
    		craft.moveCalculatedByNeuralNetwork(trainedNetwork.getNet().calculatedOut());
    	}
    	
    	/**
    	[40, 51, 230, 552, 371, 134, 350, 525, 219, 57, 596, 240, 6, 142, 1714, 70, 94, 60, 1550, 39] 0.5
    	[40, 149, 436, 553, 577, 150, 556, 515, 425, 54, 1, 96, 212, 161, 2126, 70, 506, 60, 1962, 39] 0
    	[40, 195, 567, 558, 708, 153, 687, 507, 556, 60, 132, 100, 343, 144, 2388, 70, 768, 60, 2224, 39] 1
    	*/
    }
    
    private String arrayToString() {
    	String out = "";
    	
    	for (double p : this.positions) {
    		out += p + ", ";
    	}
    	
    	return  out.substring(0, out.length() - 2);
    }
    
//    public void keyPressed(KeyEvent e) {
//
//        int key = e.getKeyCode();
//        
//        if (key == KeyEvent.VK_BACK_SPACE) {
//        	System.out.println("__" + Arrays.toString(this.positions));
//        }
//    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}
