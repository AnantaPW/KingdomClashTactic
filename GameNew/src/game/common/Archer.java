package game.common;

import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Archer extends Unit {

	public Archer() {
		super();
		super.unitClass = "Archer";
		super.setIcon("src/game/assets/archer.jpg");
		// TODO Auto-generated constructor stub
	}

	public Archer(String name, int hp, int attack, int defense, int range_atk, int movement, Point coor, int player) {
		super(name, hp, attack, defense, range_atk, movement, coor, player);
		super.unitClass = "Archer";
		super.setIcon("src/game/assets/archer/archer.png");
		try {
			sprite[0] = ImageIO.read(new File("src/game/assets/archer/1.png"));
			sprite[1] = ImageIO.read(new File("src/game/assets/archer/2.png"));
			sprite[2] = ImageIO.read(new File("src/game/assets/archer/3.png"));
			sprite[3] = ImageIO.read(new File("src/game/assets/archer/4.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public Archer(int movement, int x, int y) {
		super(movement, x, y);
		// TODO Auto-generated constructor stub
	}

	public Archer(int movement, Point coor) {
		super(movement, coor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useSkill() {
		// TODO Auto-generated method stub
		this.attack += 20;
		this.range_atk += 2;
		this.skillActive = true;
		this.skillLast = 4;
	}

	@Override
	public void skillControl() {
		// TODO Auto-generated method stub
		if (this.skillActive) {
			if (this.skillLast > 0) {
				this.skillLast--;
			} else if (this.skillLast == 0) {
				this.skillActive = false;
				this.attack -= 20;
				this.range_atk -= 2;
			}
		}
	}

}
