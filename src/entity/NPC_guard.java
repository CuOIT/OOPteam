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
		worldX=gp.tileSize*44;
		worldY=gp.tileSize*44;
	}
	
	

	public void getImage() {
		up1=setup("/npc/guard_Up1",gp.tileSize,gp.tileSize);
		up2=setup("/npc/guard_Up2",gp.tileSize,gp.tileSize);
		down2=setup("/npc/guard_Up2",gp.tileSize,gp.tileSize);
		down1=setup("/npc/guard_Up1",gp.tileSize,gp.tileSize);
		left1=setup("/npc/guard_Up1",gp.tileSize,gp.tileSize);
		left2=setup("/npc/guard_Up2",gp.tileSize,gp.tileSize);
		right1=setup("/npc/guard_Up1",gp.tileSize,gp.tileSize);
		right2=setup("/npc/guard_Up2",gp.tileSize,gp.tileSize);
	}
	public void setDialogue() {
		dialogue[0]="Where is this???";
		dialogue[1]="You're in Mystery Island.";
		dialogue[2]="How can I get out there?";
		dialogue[3]="Let's meet our headman. He will give you the solution.";
		dialogue[4]="Where can I find him?";
		dialogue[5]="Firstly, you have to find 5 apple in the jungle in order to prove that you're worthy to meet him";
		dialogue[6]="After that, I will let you come to meet him.";
		dialogue[7]="Careful with the boars. They're stronger than you think.";
		dialogue[10]="You don't have enough apples!";
		dialogue[11]="Niceee.. Go to the East and you will see the headman!";
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
