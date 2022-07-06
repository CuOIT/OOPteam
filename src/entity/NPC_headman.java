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
		worldX=gp.TILE_SIZE*16;
		worldY=gp.TILE_SIZE*16;
	}
	
	

	public void getImage() {
		up1=setup("/npc/headman_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		up2=setup("/npc/headman_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down2=setup("/npc/headman_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/npc/headman_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		left1=setup("/npc/headman_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		left2=setup("/npc/headman_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		right1=setup("/npc/headman_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		right2=setup("/npc/headman_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
	}
	public void setDialogue() {
		dialogue[2][0]="You can't leave this island with only a normal wooden boat for sure. LOL!!!\nCause the distance from here to the nearest land is more than 100km\nI sure that you would be a yummy meal for the brutal sharks out there";
		dialogue[2][1]="How can I leave here??";
		dialogue[2][2]="I have an idea. Although it's very dangerous";
		dialogue[2][3]="There's an evil monster hide in the East cave.It's holding a powerful crystal which may help you leaving there.";
		dialogue[2][4]="In the past, there were many prirate want to have it but all of them were dead under its claws.";
		dialogue[2][5]="Go to kill the boar monster and take 5 tooth for us and we will\ngive you a powerful arrow to kill evil monster.";
		
		dialogue[3][0]="Nice try!!! Here is your arrow.";
		dialogue[3][1]="If you defeat the monster and bring the crystal to me. I will told the villagers make a boat with the energy of crystal.";
		dialogue[3][2]="Now go to the West and entry the cave, the evil monster is inside!";
	
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
