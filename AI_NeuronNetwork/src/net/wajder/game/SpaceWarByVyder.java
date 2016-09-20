package net.wajder.game;

import java.awt.EventQueue;
import javax.swing.JFrame;

import net.wajder.network.TrainedNetwork;

public class SpaceWarByVyder extends JFrame {

	private static final long serialVersionUID = -6422686963415957523L;

	public SpaceWarByVyder() {
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("SpaceWar");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SpaceWarByVyder ex = new SpaceWarByVyder();
                ex.setVisible(true);
            }
        });
    }
}
