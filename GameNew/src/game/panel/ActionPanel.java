package game.panel;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.common.*;

public class ActionPanel extends JPanel{

	private int x;
	private int y;
	private int width;
	private int height;
	
	private Unit unit = null;
	
	//===== Border =====
	private Rectangle border;
	//===== Border =====
	
	public ActionPanel(int dx, int dy) {
		x = 10;
		y = dy + 10;
		
		height = (768 - y) - 10;
		width = dx;
		border = new Rectangle(x, y, width, height);
		setBounds(border);
		setBackground(Color.BLACK);
		setVisible(true);
		setLayout(null);
		
		initComponents();
	}
	
	public void setUnit(Unit u) {
		unit = u;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void initComponents() {
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void render(Graphics g) {
//		System.out.println("Height : "+height);
		g.setColor(new Color(134, 93, 22));
		g.fillRect(border.x, border.y, border.width, border.height);
	}
	
}
