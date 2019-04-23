package game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.assets.Background;
import game.assets.Tilemap;
import game.common.*;
import game.main.Main;

public class GamePanel extends JPanel {

	// ===== panel attribute =====
	private Unit selectUnit;

	private Thread animationThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(isRunning) {
				try {
					Thread.sleep(100);
					if(unitplayer1.isEmpty() == false) {
						for(Unit u : unitplayer1) {
							System.out.println(u.getHp());
							if(u.getSpriteIndex() == 3) {
								u.setSpriteIndex(0);
							}else {
								u.setSpriteIndex(u.getSpriteIndex()+1);					
							}
						}						
					}
					if(unitplayer2.isEmpty() == false) {
						for(Unit u : unitplayer2) {
							System.out.println(u.getHp());
							if(u.getSpriteIndex() == 3) {
								u.setSpriteIndex(0);
							}else {
								u.setSpriteIndex(u.getSpriteIndex()+1);
							}
						}						
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
	});
	
	private int winner = 0;
	
	private final int x = 10;
	private final int y = 10;
	private final int panelWidth = 1100 - (2 * x);
	private final int panelHeight = 650 - (2 * y);

	private boolean selectTargetSkill;
	// =---= Status Panel =---=
	private StatusPanel statusPanel = new StatusPanel(panelWidth, panelHeight);
	private int currTurn = 1;
	private int currPlay = 0;
	private boolean isRunning = true;

	private JLabel moveLabel;
	private JLabel attackLabel;
	private JLabel skillLabel;
	private JLabel waitLabel;

	private JLabel endTurnLabel;

	// =---= Label Control =---=

	private boolean isMoveSelected;
	private boolean isAttackSelected;
	private boolean isSkillSelected;
	private boolean isWaitSelected;
	private boolean isEndTurnSelected;

	private boolean isSelected;

	// =---= Label Control =---=

	// =---= Status Panel =---=

	// =---= Action Panel =---=
	private ActionPanel actionPanel = new ActionPanel(panelWidth, y + panelHeight);
	// =---= Action Panel =---=

	// ===== panel attribute =====

	// ===== assets control =====
	private Background gameBackground = new Background("src/game/assets/game_background.jpg", x, y, panelWidth, panelHeight);

	// =----= map =---=
//			BufferedImage wall;
//			BufferedImage grass;

	private ImageIcon hoverImage = new ImageIcon("src/game/assets/biru.png");
	private ImageIcon moveRange = new ImageIcon("src/game/assets/biru_tua.png");
	private ImageIcon attackRange = new ImageIcon("src/game/assets/merah.png");
	private ImageIcon skillRange = new ImageIcon("src/game/assets/hijau_tua.png");
	
	private ImageIcon wall = new ImageIcon("src/game/assets/wall.png");
	private ImageIcon grass = new ImageIcon("src/game/assets/grass4.png");

	private final int mapSize = panelHeight-20;
	private final int tileHeight = 10;
	private final int tileWidth = 15;
	private final int tileSize = mapSize / tileHeight;
	private Tilemap tilemap = new Tilemap("src/game/assets/map_code_01.txt", 30, 30);
	private final int mapX = 90;
	private final int mapY = y + (panelHeight - mapSize) / 2;
	private int[][] mapcode = tilemap.getMapcode().clone();
	// =----= map =---=
	

	// ===== assets control =====

	// ===== player attribute =====

	private Vector<Unit> unitplayer1 = new Vector<>();
	private Vector<Unit> unitplayer2 = new Vector<>();

	private void initPlayerOne() {
		Unit u = new Sage("Archmage", 80, 100, 30, 30, 4, 3, new Point(2, 3),1);
		u.setTurn(true);
		unitplayer1.add(u);
		u = new Warrior("Durrandal", 100, 30, 30, 1, 4, new Point(2, 2),1);
		u.setTurn(true);
		unitplayer1.add(u);
		u = new Knight("Arthas", 100, 20, 40, 1, 4, new Point(3, 2),1);
		u.setTurn(true);
		unitplayer1.add(u);
		u = new Archer("Legolas", 80, 20, 40, 4, 2, new Point(3, 3),1);
		u.setTurn(true);
		unitplayer1.add(u);
	}

	private void initPlayerTwo() {
		Unit u = new Sage("Archmage", 80, 100, 30, 30, 4, 3, new Point(27, 27),2);
		u.setTurn(true);
		unitplayer2.add(u);
		u = new Warrior("Durrandal", 100, 30, 30, 1, 4, new Point(26, 27),2);
		u.setTurn(true);
		unitplayer2.add(u);
		u = new Knight("Arthas", 100, 20, 40, 1, 4, new Point(26, 26),2);
		u.setTurn(true);
		unitplayer2.add(u);
		u = new Archer("Legolas", 80, 20, 40, 4, 2, new Point(27, 26),2);
		u.setTurn(true);
		unitplayer2.add(u);
	}

	private void initPlayerTest() {
		System.out.println("Sage");
		Unit u = new Sage("Archmage", 80, 100, 30, 30, 4, 3, new Point(2, 3),1);
		u.setTurn(true);
		unitplayer1.add(u);
		System.out.println("Warrior");
		u = new Warrior("Durrandal", 100, 1000, 30, 1, 4, new Point(2, 2),1);
		u.setTurn(true);
		unitplayer1.add(u);
		System.out.println("Knight");
		u = new Knight("Arthas", 100, 20, 40, 1, 4, new Point(3, 2),1);
		u.setTurn(true);
		unitplayer1.add(u);
		System.out.println("Archer");
		u = new Archer("Legolas", 80, 20, 40, 4, 2, new Point(3, 3),1);
		u.setTurn(true);
		unitplayer1.add(u);

		System.out.println("Sage");
		u = new Sage("Archmage", 80, 100, 30, 30, 4, 3, new Point(5, 2),2);
		u.setTurn(true);
		unitplayer2.add(u);
		System.out.println("Warrior");
		u = new Warrior("Durrandal", 100, 30, 30, 1, 4, new Point(6, 2),2);
		u.setTurn(true);
		unitplayer2.add(u);
		System.out.println("Knight");
		u = new Knight("Arthas", 100, 20, 40, 1, 4, new Point(5, 3),2);
		u.setTurn(true);
		unitplayer2.add(u);
		System.out.println("Archer");
		u = new Archer("Legolas", 80, 20, 40, 4, 2, new Point(6, 3),2);
		u.setTurn(true);
		unitplayer2.add(u);
	}

	private Unit isUnit(int x, int y) {
		Point p = new Point(x, y);
		for (Unit u : unitplayer1) {
//			System.out.println(u);
			if (p.equals(u.getCoor())) {
				currPlay = 1;
				return u;
			}
		}
		for (Unit u : unitplayer2) {
			if (p.equals(u.getCoor())) {
				currPlay = 2;
				return u;
			}
		}
		return null;
	}

	private Unit isUnit(Point p) {
		for (Unit u : unitplayer1) {
			if (p.equals(u.getCoor())) {
				currPlay = 1;
				return u;
			}
		}
		for (Unit u : unitplayer2) {
			if (p.equals(u.getCoor())) {
				currPlay = 2;
				return u;
			}
		}
		return null;
	}

	// ===== player attribute =====

	// ===== all about position =====

	private Rectangle Bound = new Rectangle(mapX, mapY, tileWidth * tileSize, tileHeight * tileSize);
	private Point hover = new Point(1, 1);

	private Rectangle camera = new Rectangle(0, 0, tileWidth, tileHeight);
	// ===== all about position =====

	// ===== listener =====
	private MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (SwingUtilities.isRightMouseButton(e)) {
				isMoveSelected = false;
				isAttackSelected = false;
				isWaitSelected = false;
				isSkillSelected = false;
				isSelected = false;
				selectUnit = null;
				setVisibleLabel(false);
				repaint();
			}
		}
	};

	private MouseMotionListener mouseMotionListener = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getX()+ " "+e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	private KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				if (mapcode[hover.x][hover.y - 1] == 0) {
					if (camera.y >= hover.y - 1) {
						camera.y--;
					}
					hover.y--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (mapcode[hover.x][hover.y + 1] == 0) {
					if (camera.y + camera.height - 1 <= hover.y + 1) {
						camera.y++;
					}
					hover.y++;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (mapcode[hover.x - 1][hover.y] == 0) {
					if (camera.x >= hover.x - 1) {
						camera.x--;
					}
					hover.x--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (mapcode[hover.x + 1][hover.y] == 0) {
					if (camera.x + camera.width - 1 <= hover.x + 1) {
						camera.x++;
					}
					hover.x++;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_J) {
				Unit hovU = isUnit(hover);

				if (selectUnit != null) {
					setVisibleLabel(true);
					if (isMoveSelected) {
						if ((Math.abs(hover.x - selectUnit.getCoor().x)
								+ Math.abs(hover.y - selectUnit.getCoor().y)) <= selectUnit.getMovement()) {
							if (hovU == null || hovU.equals(selectUnit)) {
								selectUnit.setMove(false);
								if (selectUnit.isAttack() == false) {
									selectUnit.setTurn(false);
								}
								selectUnit.setCoor((Point) hover.clone());
								selectUnit = null;
								isSelected = false;
								isMoveSelected = false;
								setVisibleLabel(false);
							}
						}
					} else if (isAttackSelected) {
						if ((Math.abs(hover.x - selectUnit.getCoor().x)
								+ Math.abs(hover.y - selectUnit.getCoor().y)) <= selectUnit.getRange_atk()) {
							if (hovU != null) {
								hovU.setHp(hovU.getHp() - (selectUnit.getAttack() - hovU.getDefense() / 4));
								selectUnit.setAttack(false);
								if (selectUnit.isMove() == false) {
									selectUnit.setTurn(false);
								}
								if(hovU.getHp() <= 0) {
									if(unitplayer1.contains(hovU)) {
										System.out.println("unit 1 is die");
										unitplayer1.remove(hovU);
										repaint();
									}else if(unitplayer2.contains(hovU)) {
										System.out.println("unit 2 is die");
										unitplayer2.remove(hovU);
										repaint();
									}
								}
								isSelected = false;
								isAttackSelected = false;
								selectUnit = null;
								setVisibleLabel(false);
							}
						}
					}else if(selectTargetSkill) {
						if(hovU != null) {
							if(selectUnit instanceof Sage) {
								((Sage) selectUnit).use_skill(hovU);
								selectTargetSkill = false;
								if(selectUnit.isMove()) {
									selectUnit.setTurn(false);
								}
								selectUnit.setAttack(false);
								isSelected = false;
								isSkillSelected = false;
								selectUnit = null;
								setVisibleLabel(false);
							}
						}
					}
				}else if (selectUnit == null && hovU != null && hovU.getTurn() && currPlay == currTurn) {
					System.out.println("unit selected!");
					selectUnit = hovU;
					statusPanel.setUnit(selectUnit);
					System.out.println(selectUnit);
					setVisibleLabel(true);
				}

			}
			// ====================
			repaint();
		}
	};

	// ===== listener =====

	private void setVisibleLabel(boolean flag) {
		moveLabel.setVisible(flag);
		attackLabel.setVisible(flag);
		skillLabel.setVisible(flag);
		waitLabel.setVisible(flag);
		endTurnLabel.setVisible(flag);
	}

	private void initLabel() {

		ImageIcon button = new ImageIcon("src/kingdom/button.png");

		int labelX = 40;
		int labelY = 580;

		int labelWidth = 800 / 5;
		int labelHeight = 180;

		moveLabel = new JLabel("Move", JLabel.CENTER);
		moveLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		moveLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		moveLabel.setForeground(Color.WHITE);
		moveLabel.setIcon(button);
		moveLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isMoveSelected == true) {
					moveLabel.setForeground(Color.YELLOW);
				} else {
					moveLabel.setForeground(Color.WHITE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (!isSelected) {
					moveLabel.setForeground(Color.YELLOW);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (isSelected == false && selectUnit.isMove()) {
						isMoveSelected = true;
						isSelected = true;
					}
				}

				if (SwingUtilities.isRightMouseButton(e)) {
					if (isMoveSelected) {
						isMoveSelected = false;
						isSelected = false;
					}
				}
				repaint();
			}
		});

		add(moveLabel);

		labelX += labelWidth + 25;

		attackLabel = new JLabel("Attack", JLabel.CENTER);
		attackLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		attackLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		attackLabel.setForeground(Color.WHITE);
		attackLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isAttackSelected == true) {
					attackLabel.setForeground(Color.YELLOW);
				} else {
					attackLabel.setForeground(Color.WHITE);
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (!isSelected) {
					attackLabel.setForeground(Color.YELLOW);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e) && selectUnit.isAttack()) {
//					System.out.println("left mouse button click");
					if (isSelected == false && selectUnit.isAttack()) {
						isAttackSelected = true;
						isSelected = true;
					}
				}

				if (SwingUtilities.isRightMouseButton(e)) {
					if (isAttackSelected) {
						isAttackSelected = false;
						isSelected = false;
					}
				}
				repaint();
			}
		});

		add(attackLabel);

		labelX += labelWidth + 25;

		skillLabel = new JLabel("Skill", JLabel.CENTER);
		skillLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		skillLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		skillLabel.setForeground(Color.WHITE);
		skillLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isSkillSelected == true) {
					skillLabel.setForeground(Color.YELLOW);
				} else {
					skillLabel.setForeground(Color.WHITE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!isSelected) {
					skillLabel.setForeground(Color.YELLOW);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (isSelected == false) {
						isSkillSelected = true;
						isSelected = true;
						if(selectUnit instanceof Warrior) {
							selectUnit.useSkill();
							if(selectUnit.isMove() == false) {
								selectUnit.setTurn(false);
							}
							selectUnit.setAttack(false);
							isSelected = false;
							isSkillSelected = false;
							selectUnit = null;
							setVisibleLabel(false);
						}else if(selectUnit instanceof Archer) {
							selectUnit.useSkill();
							if(selectUnit.isMove() == false) {
								selectUnit.setTurn(false);
							}
							selectUnit.setAttack(false);
							isSelected = false;
							isSkillSelected = false;
							selectUnit = null;
							setVisibleLabel(false);
						}else if(selectUnit instanceof Knight) {
							selectUnit.useSkill();
							selectUnit.setAttack(false);
							if(selectUnit.isMove() == false) {
								selectUnit.setTurn(false);
							}
							selectUnit.setAttack(false);
							isSelected = false;
							isSkillSelected = false;
							selectUnit = null;
							setVisibleLabel(false);
						}else if(selectUnit instanceof Sage) {
							selectTargetSkill = true;
						}
					}
				}

				if (SwingUtilities.isRightMouseButton(e)) {
					if (isSkillSelected) {
						isSkillSelected = false;
						isSelected = false;
					}
				}
				repaint();
			}
		});

		add(skillLabel);

		labelX += labelWidth + 25;

		waitLabel = new JLabel("Wait", JLabel.CENTER);
		waitLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		waitLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		waitLabel.setForeground(Color.WHITE);
		waitLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isWaitSelected == true) {
					waitLabel.setForeground(Color.YELLOW);
				} else {
					waitLabel.setForeground(Color.WHITE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!isSelected) {
					waitLabel.setForeground(Color.YELLOW);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (isSelected == false) {
						isWaitSelected = true;
						isSelected = true;
						selectUnit.setAttack(false);
						selectUnit.setMove(false);
						selectUnit.setTurn(false);
						selectUnit = null;
						isSelected = false;
						isWaitSelected = false;
						setVisibleLabel(false);
					}
				}

				if (SwingUtilities.isRightMouseButton(e)) {
					if (isWaitSelected) {
						isWaitSelected = false;
						isSelected = false;
					}
				}
				repaint();
			}
		});

		add(waitLabel);

		labelX += labelWidth + 25;

		endTurnLabel = new JLabel("End Turn", JLabel.CENTER);
		endTurnLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
		endTurnLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		endTurnLabel.setForeground(Color.WHITE);
		endTurnLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isEndTurnSelected == true) {
					endTurnLabel.setForeground(Color.RED);
				} else {
					endTurnLabel.setForeground(Color.WHITE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!isSelected) {
					endTurnLabel.setForeground(Color.RED);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (isSelected == false) {
						isEndTurnSelected = true;
						isSelected = true;
						if(currTurn == 1) {
							for(Unit u : unitplayer1) {
								u.skillControl();
								u.setAttack(false);
								u.setMove(false);
								u.setTurn(false);
							}
							isSelected = false;
							isEndTurnSelected = false;
							selectUnit = null;
							setVisibleLabel(false);
						} else if(currTurn == 2) {
							for(Unit u : unitplayer2) {
								u.skillControl();
								u.setAttack(false);
								u.setMove(false);
								u.setTurn(false);
							}
							isSelected = false;
							isEndTurnSelected = false;
							selectUnit = null;
							setVisibleLabel(false);
						}
					}
				}
				repaint();
			}
		});

		add(endTurnLabel);
		setVisibleLabel(false);
	}

	
	public GamePanel() {
		
		animationThread.start();

		setLayout(null);

		setBounds(x, y, panelWidth, panelHeight);
		this.setFocusable(true);
		addMouseListener(mouseListener);
		addKeyListener(keyListener);
		addMouseMotionListener(mouseMotionListener);
		setBackground(new Color(141, 111, 60));

		
		
		initPlayerOne();
		initPlayerTwo();
//		initPlayerTest();

		initLabel();

		isSelected = false;
	}

	public void conditionCheck() {
		System.out.println(" ================================ ");
		System.out.println("currPlay = "+currPlay);
		System.out.println("currTurn = "+currTurn);
		if (currTurn == 1) {
			if(unitplayer2.isEmpty()) {
				isRunning = false;
				System.out.println("player 1 win");
				Main.frame.remove(GamePanel.this);
				JPanel panel = new EndGamePanel(1);
				Main.frame.add(panel);
				panel.requestFocus(true);		//fokus ke scene berikutnya
				Main.frame.validate();
			}else {
				boolean cek = true;
				for (Unit u : unitplayer1) {
					if (u.getTurn()) {
						cek = false;
					}
				}
				if (cek) {
					for(Unit u : unitplayer1) {
						u.skillControl();
					}
					for (Unit u : unitplayer2) {
						u.setTurn(true);
						u.setAttack(true);
						u.setMove(true);
					}
					statusPanel.setCurrPlayer(2);
					currTurn = 2;
				}				
			}
		} else if (currTurn == 2) {
			if(unitplayer1.isEmpty()) {
				isRunning = false;
				Main.frame.remove(GamePanel.this);
				JPanel panel = new EndGamePanel(2);
				Main.frame.add(panel);
				panel.requestFocus(true);		//fokus ke scene berikutnya
				Main.frame.validate();
			}else {
				boolean cek = true;
				for (Unit u : unitplayer2) {
					if (u.getTurn()) {
						cek = false;
					}
				}
				if (cek) {
					for(Unit u : unitplayer2) {
						u.skillControl();
					}
					for (Unit u : unitplayer1) {
						u.setTurn(true);
						u.setAttack(true);
						u.setMove(true);
					}
					statusPanel.setCurrPlayer(1);
					currTurn = 1;
				}	
			}
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		conditionCheck();
		Unit hov = isUnit(hover);
		if (hov != null) {
			statusPanel.setUnit(hov);
		}
		if (hov == null && selectUnit == null) {
			statusPanel.setUnit(null);
		}
		if (selectUnit != null) {
			setVisibleLabel(true);
			statusPanel.setUnit(selectUnit);
		}
		g.clearRect(0, 0, 1366, 768);

		super.paintComponent(g);

		g.drawImage(gameBackground.getImage(), x, y, panelWidth, panelHeight, null);
		statusPanel.render(g);
		actionPanel.render(g);

		// ===== drawing map =====
		for (int i = camera.x; i < camera.x + camera.width; i++) {
			for (int j = camera.y; j < camera.y + camera.height; j++) {

				
				if (mapcode[i][j] == 1) {
					g.drawImage(wall.getImage(), mapX + ((i - camera.x) * tileSize), mapY + ((j - camera.y) * tileSize),
							tileSize, tileSize, null);
				} else if (mapcode[i][j] == 0) {
					g.drawImage(grass.getImage(), mapX + ((i - camera.x) * tileSize), mapY + ((j - camera.y) * tileSize),
							tileSize, tileSize, null);
				}

				 
				if (selectTargetSkill && selectUnit != null) {
					if (Math.abs(i - selectUnit.getCoor().x) + Math.abs(j - selectUnit.getCoor().y) <= selectUnit
							.getRange_atk() && mapcode[i][j] == 0) {
						g.drawImage(skillRange.getImage(), mapX + ((i - camera.x) * tileSize),
								mapY + ((j - camera.y) * tileSize), tileSize, tileSize, null);
					}
				}

				if (isMoveSelected == true && selectUnit != null) {
					if (Math.abs(i - selectUnit.getCoor().x) + Math.abs(j - selectUnit.getCoor().y) <= selectUnit
							.getMovement() && mapcode[i][j] == 0) {
						g.drawImage(moveRange.getImage(), mapX + ((i - camera.x) * tileSize),
								mapY + ((j - camera.y) * tileSize), tileSize, tileSize, null);
					}
				}
				
				
				if (isAttackSelected == true && selectUnit != null) {
					if (Math.abs(i - selectUnit.getCoor().x) + Math.abs(j - selectUnit.getCoor().y) <= selectUnit
							.getRange_atk() && mapcode[i][j] == 0) {
						g.drawImage(attackRange.getImage(), mapX + ((i - camera.x) * tileSize),
								mapY + ((j - camera.y) * tileSize), tileSize, tileSize, null);
					}
				}

				Unit nowU = isUnit(i,j);
				
				if (nowU != null) {	
					g.drawImage(nowU.getSprite(tileSize, tileSize), mapX + ((i - camera.x) * tileSize),
							mapY + ((j - camera.y) * tileSize), tileSize, tileSize, null);
//					g.fillOval(mapX + ((i - camera.x) * tileSize), mapY + ((j - camera.y) * tileSize), tileSize, tileSize);
				}
			}
		}
		if (hover != null) {
			Color col = new Color(0, 0, 243);
			g.setColor(col);
			g.drawImage(hoverImage.getImage(), mapX + ((
					hover.x - camera.x) * tileSize),
					mapY + ((hover.y - camera.y) * tileSize), tileSize, tileSize, null);
		}

		g.setColor(Color.YELLOW);
		g.drawRect(Bound.x, Bound.y, Bound.width, Bound.height);
		// ===== drawing map =====
		
//		this.requestFocus(true);


	}

}
