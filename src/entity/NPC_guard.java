package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_guard extends Entity{
	public NPC_guard(GamePanel gp) {
		super(gp);
		direction="down";
		numberDialogue=0;
		speed=0;
		getImage();
		collision=true;
		setDialogue();
	}
	public void setDefaultValues() {
		worldX=gp.TILE_SIZE*44;
		worldY=gp.TILE_SIZE*44;
	}
	
	

	public void getImage() {
		up1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		up2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		left1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		left2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		right1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		right2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
	}
	public void setDialogue() {
		dialogue[1][0]="Where is this???";
		dialogue[1][1]="You're in Mystery Island.";
		dialogue[1][2]="How can I get out there?";
		dialogue[1][3]="Let's meet our headman. He will give you the solution.";
		dialogue[1][4]="Where can I find him?";
		dialogue[1][5]="Firstly, you have to find 5 apple in the jungle in order to prove that you're worthy to meet him";
		dialogue[2][0]="Niceee..I have a gift for you. It will be helpful soon";
		dialogue[2][1]="And now, go to the East and you will see the headman!";
		
	}
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter==75) {
			Random random=new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}
			if(i>25 && i<50)
			{
				direction="down";
			}
			if(i>50 && i<=75) {
				direction="left";
			}
			if(i>75 && i<=100) {
				direction = "right";
			}
			actionLockCounter=0;
		}
		
	}
	
}
