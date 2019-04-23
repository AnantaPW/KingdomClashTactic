package game.common;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Sage extends Unit {

	private final int skillRange = 5;
	private int mpCost = 20;
	private int mp;
	
	public Sage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sage(String name, int hp,int mp, int attack, int defense, int range_atk, int movement, Point coor, int player) {
		super(name, hp, attack, defense, range_atk, movement, coor, player);
		this.mp = mp;
		super.unitClass = "Sage";
		
		try {
			sprite[0] = ImageIO.read(new File("src/game/assets/sophia/1.png"));
			sprite[1] = ImageIO.read(new File("src/game/assets/sophia/2.png"));
			sprite[2] = ImageIO.read(new File("src/game/assets/sophia/3.png"));
			sprite[3] = ImageIO.read(new File("src/game/assets/sophia/4.png"));
		}catch(Exception e) {			
			e.printStackTrace();
		}		
		super.setIcon("src/game/assets/sophia/sophia.png");
	}
	

	public Sage(int movement, int x, int y) {
		super(movement, x, y);
	}

	public Sage(int movement, Point coor) {
		super(movement, coor);
	}

	@Override
	public void useSkill() {
		
	}
	
	public void use_skill(Unit target) {
		if(this.mp - this.mpCost >= 0) {
			this.mp-=this.mpCost;
			target.setHp(target.getHp()+20);
			if(target.getHp() > target.getMaxHp()) {
				target.setHp(target.getMaxHp());
			}			
		}
	}

	public int getMp() {
		return mp;
	}

	@Override
	public void skillControl() {
		// TODO Auto-generated method stub
		
	}
}
