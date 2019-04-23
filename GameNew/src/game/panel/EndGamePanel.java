package game.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.main.Main;

public class EndGamePanel extends JPanel {

	private JLabel label;

	private KeyListener kl = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			Main.frame.remove(EndGamePanel.this);
			JPanel panel = new MainMenuPanel();
			Main.frame.add(panel);
			panel.requestFocus(true); // fokus ke scene berikutnya
			Main.frame.validate();
		}
	};

	public EndGamePanel(int win) {
		setLayout(null);
//		setBackground(Color.WHITE);
		if (win == 1) {
			label = new JLabel("Player 1 win!!!");
			label.setIcon(new ImageIcon("src/games/assets/player_win/p1.png"));
		} else if (win == 2) {
			label = new JLabel("Player 2 win!!!");
			label.setIcon(new ImageIcon("src/games/assets/player_win/p2.png"));
		}
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
//		label.setOpaque(true);
		label.setForeground(Color.BLACK);
		label.setFocusable(true);
//		label.setIcon(new ImageIcon("C:\\Users\\Abednego Frami\\Desktop\\button.png"));
		label.setBounds(10, 11, 1366/2 - 200, 768/2-100);
		label.setVisible(true);
		add(label);
		setVisible(true);
		addKeyListener(kl);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(134, 93, 22));
		g.fillRect(0, 0, 1366, 768);
		
		super.paint(g);
	}
}
