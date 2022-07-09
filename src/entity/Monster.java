package entity;

import main.GamePanel;

public class Monster extends Entity{
	
	public int actionLockCounter=0;
	public int shotAvailableCounter = 0;
	
	public Monster(GamePanel gp) {
		super(gp);
	}
}
