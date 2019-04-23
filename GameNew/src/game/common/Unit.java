package game.common;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Unit {
	protected String name;
	protected String unitClass;
	protected boolean turn;
	protected boolean isMove;
	protected boolean isAttack;
	protected boolean skillActive = false;
	protected int maxHp;
	protected int hp;
	protected int attack;
	protected int defense;
	protected int range_atk;
	protected int movement;
	protected int skillLast;
	protected int spriteIndex = 0;
	protected int player;
	protected Point coor;
	
	//===== image =====
	protected BufferedImage[] sprite = new BufferedImage[4];
	
	protected BufferedImage icon;
	//===== image =====
	
	

//	secondary
//	private int mp;

	public Unit() {
		// TODO Auto-generated constructor stub
	}

	public Unit(String name, int hp, int attack, int defense, int range_atk, int movement, Point coor,int player) {
		super();
		this.turn = true;
		this.isAttack = true;
		this.isMove = true;
		this.name = name;
		this.hp = hp;
		this.maxHp = hp;
		this.attack = attack;
		this.defense = defense;
		this.range_atk = range_atk;
		this.movement = movement;
		this.coor = coor;
		this.player = player;
	}

	public Unit(int movement, Point coor) {
		this.movement = movement;
		this.coor = coor;
	}

	public Unit(int movement, int x, int y) {
		this.movement = movement;
		coor = new Point(x, y);
	}

	// method
	public void attack(Unit enemy) {
		if (this.attack < enemy.getDefense()) return;
		else {
			enemy.setHp(enemy.getHp() - (this.attack - enemy.getDefense()));
		}
	}
	
	
	
	public int getSpriteIndex() {
		return spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Image getSprite(int width, int height) {
		return sprite[spriteIndex].getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public boolean isMove() {
		return isMove;
	}
	
	public void setMove(boolean move) {
		isMove = move;
	}
	
	public boolean isAttack() {
		return isAttack;
	}
	
	public void setAttack(boolean attack) {
		isAttack = attack;
	}

	abstract public void skillControl();
	
	abstract public void useSkill();
	// setter getter
	
	
	
	public void setIcon(String filepath) {
		try {
			icon = ImageIO.read(new File(filepath));			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isSkillActive() {
		return skillActive;
	}

	public int getSkillLast() {
		return skillLast;
	}

	public String getUnitClass() {
		return unitClass;
	}
	
	public Image getIcon(int width, int height) {
		return icon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public String getName() {
		return name;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getRange_atk() {
		return range_atk;
	}

	public void setRange_atk(int range_atk) {
		this.range_atk = range_atk;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnitClass(String unitClass) {
		this.unitClass = unitClass;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public Point getCoor() {
		return coor;
	}

	public void setCoor(Point coor) {
		this.coor = coor;
	}

	public boolean getTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
}