package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_headman extends Entity{
	public NPC_headman(GamePanel gp) {
		super(gp);
		direction="down";
		speed=0;
		numberDialogue=0;
		getImage();
		setDialogue();
		collision=true;
	}
	public void setDefaultValues() {
		worldX=gp.tileSize*16;
		worldY=gp.tileSize*16;
	}
	
	

	public void getImage() {
		up1=setup("/npc/headman_Up1",gp.tileSize,gp.tileSize);
		up2=setup("/npc/headman_Up2",gp.tileSize,gp.tileSize);
		down2=setup("/npc/headman_Up2",gp.tileSize,gp.tileSize);
		down1=setup("/npc/headman_Up1",gp.tileSize,gp.tileSize);
		left1=setup("/npc/headman_Up1",gp.tileSize,gp.tileSize);
		left2=setup("/npc/headman_Up2",gp.tileSize,gp.tileSize);
		right1=setup("/npc/headman_Up1",gp.tileSize,gp.tileSize);
		right2=setup("/npc/headman_Up2",gp.tileSize,gp.tileSize);
	}
	public void setDialogue() {
		dialogue[0]="You can't leave this island with only a normal wooden boat for sure. LOL!!!";
		dialogue[1]="Cause the distance from here to the nearest land is more than 100km.";
		dialogue[2]="I sure that you would be a yummy meal for the brutal sharks out there";
		dialogue[3]="How can I leave here??";
		dialogue[4]="But i have an idea. Although it's very dangerous";
		dialogue[5]="There's an evil monster hide in the East cave.It's holding a powerful crystal which may help you leaving there.";
		dialogue[6]="In the past, there were many prirate want to have it but all of them were dead under its claws.";
		dialogue[7]="If you defeat the monster and bring the crystal to me. I will told the villagers make a boat with the energy of crystal.";
		dialogue[8]="Good luck!";
	
	}
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter==75) {
			Random random=new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}			if(i>25 && i<50)
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
