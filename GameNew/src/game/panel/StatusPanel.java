package game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import game.common.Sage;
import game.common.Unit;

public class StatusPanel extends JPanel {

	private int currPlayer = 1;

	private Unit unit;
	// ===== border =====
	private Rectangle border;
	private int x;
	private int y;
	private int pWidth;
	private int pHeight;
	
	private Rectangle iconBorder;
	private int ix;
	private int iy;
	private int iWidth;
	private int iHeight;
	
	private Rectangle statusBorder;
	private int sx;
	private int sy;
	private int sWidth;
	private int sHeight;
//	Color borderColor = new Color(165, 78, 34, 1);

	// ===== border =====

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Unit getUnit() {
		return unit;
	}

	private int tx;
	private int ty;

	public StatusPanel(int mpWidth, int mpHeight) {
		// ===== init component =====

		x = mpWidth + 20;
		y = 10;
		pWidth = 1366 - 10 - x;
		pHeight = 768 - 20;

		ix = x + 10;
		iy = y + 50;
		iWidth = pWidth - 20;
		iHeight = 400;

		sx = ix;
		sy = iy + iHeight + 30;
		sWidth = iWidth;
		sHeight = 768 - 20 - sy;

		// =---= draw border =---=
		border = new Rectangle(x, y, pWidth, pHeight);
		iconBorder = new Rectangle(ix, iy, iWidth, iHeight);
		statusBorder = new Rectangle(sx, sy, sWidth, sHeight);
		// =---= draw border =---=

		tx = 1130;
		ty = y + 30;

//		setBounds(border);
	}

	public int getCurrPlayer() {
		return currPlayer;
	}

	public void setCurrPlayer(int currPlayer) {
		this.currPlayer = currPlayer;
	}

	public void render(Graphics g) {
//		System.out.println("render called");	
		statusBorder.y = sy;
		g.setColor(new Color(134, 93, 22));
		g.fillRect(border.x, border.y, border.width, border.height);
		g.setColor(Color.BLACK);
//		System.out.println(x + " " + y);

		g.drawString("Player " + currPlayer + "'s turn", tx, ty);

		g.setColor(new Color(205, 146, 44));
//		g.setColor(Color.BLACK);

		// =---= draw icon here =---=
//		System.out.println(iconBorder);
		g.fillRect(iconBorder.x, iconBorder.y, iconBorder.width, iconBorder.height);
		g.setColor(Color.YELLOW);
		g.drawRect(ix + 5, iy + 5, iWidth - 10, iHeight - 10);

		g.setColor(new Color(205, 146, 44));
		if (unit != null) {
			g.drawImage(unit.getIcon(iWidth - 10, iHeight - 10), ix + 5, iy + 5, null);
		}
		// =---= draw icon here =---=

		// =---= draw status here =---=
		g.fillRect(statusBorder.x, statusBorder.y, statusBorder.width, statusBorder.height);
		g.setColor(Color.BLACK);
		if (unit != null) {
			// ======================
			int cy = statusBorder.y += 30;
			g.drawString("Name  : " + unit.getName(), statusBorder.x + 10, cy);
			cy += 20;
			g.drawString("Class  : " + unit.getUnitClass(), statusBorder.x + 10, cy);
			cy += 20;
			g.drawString("HP       : " + unit.getHp(), statusBorder.x + 10, cy);
			cy += 20;
			if (unit instanceof Sage) {
				g.drawString("MP       : " + ((Sage) unit).getMp(), statusBorder.x + 10, cy);
				cy += 20;
			}
			g.drawString("Atk      : " + unit.getAttack(), statusBorder.x + 10, cy);
			cy += 20;
			g.drawString("Def      : " + unit.getDefense(), statusBorder.x + 10, cy);
			cy += 50;
			g.drawString("Attack Range : " + unit.getRange_atk() + " Movement : " + unit.getMovement(),
					statusBorder.x + 10, cy);
		}
		// =---= draw status here =---=
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		render(g);
	}
}
