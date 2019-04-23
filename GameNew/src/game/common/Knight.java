package game.common;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Knight extends Unit {
	
	public Knight() {
		super();
		super.unitClass = "Knight";
		super.setIcon("src/game/assets/knight.jpg");
		// TODO Auto-generated constructor stub
	}

	public Knight(String name, int hp, int attack, int defense, int range_atk, int movement, Point coor, int player) {
		super(name, hp, attack, defense, range_atk, movement, coor,player);
		super.setIcon("src/game/assets/knight/knight.png");
		try {
			
			sprite[0] = ImageIO.read(new File("src/game/assets/knight/1.png"));
			sprite[1] = ImageIO.read(new File("src/game/assets/knight/1.png"));
			sprite[2] = ImageIO.read(new File("src/game/assets/knight/1.png"));
			sprite[3] = ImageIO.read(new File("src/game/assets/knight/1.png"));
		}catch(Exception e) {			
			e.printStackTrace();
		}	
		// TODO Auto-generated constructor stub
	}

	public Knight(int movement, int x, int y) {
		super(movement, x, y);
		// TODO Auto-generated constructor stub
	}

	public Knight(int movement, Point coor) {
		super(movement, coor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useSkill() {
		// TODO Auto-generated method stub
		this.defense+=20;
		this.maxHp+=(this.maxHp/2);
		this.hp+=(this.hp/2);
		this.skillActive = true;
		this.skillLast = 2;
	}

	
	public void skillControl() {
		// TODO Auto-generated method stub
		if(skillActive) {
			if(skillLast > 0)
			{
				skillLast--;
			}else if(skillLast == 0) {
				skillActive = false;
				this.hp/=2;
				this.maxHp/=2;
			}
		}
	}

}
