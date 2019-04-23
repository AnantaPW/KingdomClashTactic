package game.panel;

import game.assets.*;
import game.main.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{
	
	private Image title;
	private JLabel play;
	private JLabel exit;
	private Clip clip;
	private boolean is_running = true;

	Background background = new Background("src/game/assets/bg1.jpg", 0, 0, 1366, 768);
	
	public MainMenuPanel() {
		setLayout(null);
		//title = new JLabel("Kingdom Clash", JLabel.CENTER);
		//title.setBounds(433, 144, 500, 50);
		//itle.setFont(new Font("Arial", Font.ITALIC, 36));
		//title.setForeground(Color.WHITE);
		//add(title);
		
		try {
			BufferedImage tiBuffer = ImageIO.read(new File ("src/game/assets/Title.png"));
			title = tiBuffer.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		play = new JLabel("Play", JLabel.CENTER);
		play.setBounds(433, 438, 500, 50);
		play.setFont(new Font("Arial", Font.ITALIC, 30));
		play.setForeground(Color.WHITE);
		play.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				play.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				play.setForeground(Color.YELLOW);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.frame.remove(MainMenuPanel.this);
				JPanel panel = new GamePanel();
				Main.frame.add(panel);
				panel.requestFocus(true);		//fokus ke scene berikutnya
				Main.frame.validate();
				is_running = false;				
			}
		});
		add(play);
		
		exit = new JLabel("Exit", JLabel.CENTER);
		exit.setBounds(433, 546, 500, 50);
		exit.setFont(new Font("Arial", Font.ITALIC, 30));
		exit.setForeground(Color.WHITE);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.YELLOW);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exit);
		
		//sound
				try {
					clip = AudioSystem.getClip();
					AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/game/assets/background_song.wav"));
					clip.open(stream);
					clip.start();
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException el) {
					el.printStackTrace();
				}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
		g.drawImage(title, 433, 144, null);
	}
}
