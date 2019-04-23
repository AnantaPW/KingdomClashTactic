package game.common;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Warrior extends Unit {
	
	public Warrior() {
		super();
		super.setIcon("src/game/assets/warrior.jpg");
		// TODO Auto-generated constructor stub
	}

	public Warrior(String name, int hp, int attack, int defense, int range_atk, int movement, Point coor, int player) {
		super(name, hp, attack, defense, range_atk, movement, coor, player);
		super.unitClass = "Warrior";
		super.setIcon("src/game/assets/warrior/warrior.png");
		try {
			
			sprite[0] = ImageIO.read(new File("src/game/assets/warrior/1.png"));
			sprite[1] = ImageIO.read(new File("src/game/assets/warrior/2.png"));
			sprite[2] = ImageIO.read(new File("src/game/assets/warrior/3.png"));
			sprite[3] = ImageIO.read(new File("src/game/assets/warrior/4.png"));
		}catch(Exception e) {			
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void useSkill() {
		this.attack+=50;
		this.skillLast = 2;
	}

	@Override
	public void skillControl() {
		// TODO Auto-generated method stub
		if(skillActive) {
			if(skillLast > 0) {
				skillLast--;
			}
			if(skillLast == 0) {
				skillActive = false;
				this.attack-=50;
			}
		}
	}

}
