package game.main;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.panel.*;
import game.assets.*;

public class Main {

	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame("Kingdom Clash Tactics");
		frame.setBounds(0, 0, 1366, 768);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBackground(new Color(141, 111, 60));
//		frame.setLayout(null);
		
//		JPanel contentPane = new JPanel();
//		contentPane.add(new GamePanel(),null);
//		frame.setContentPane(contentPane);
		
		frame.add(new MainMenuPanel(),null);
//		frame.add(new EndGamePanel(1));
//		
		
		//=========== final ==============
		frame.setVisible(true);
	}
	
	public Main() {
		// TODO Auto-generated constructor stub
	}

}
