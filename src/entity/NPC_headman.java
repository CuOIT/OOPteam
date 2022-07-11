package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_headman extends Human{
	public NPC_headman(GamePanel gp) {
		super(gp);
		direction="down";
		speed=0;
		numberDialogue=0;
		getImage();
		setDialogue();
		collision=true;
	}
	public void getImage() {
		head=setup("/npc/headmanHead",gp.TILE_SIZE*2,gp.TILE_SIZE*2);
		down2=setup("/npc/headman_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/npc/headman_Up1",gp.TILE_SIZE,gp.TILE_SIZE);

	}
	public void setDialogue() {
		dialogue[2][0]="You can’t leave this island with your normal boat.";
		dialogue[2][1]="How can I leave this island, master?";
		dialogue[2][2]="At the end of this island, there is a monster hiding in its cave";
		dialogue[2][3]="It's holdin' an energy orb which could help you get out of here.";
		dialogue[2][4]="It’s mean I must kill this monster, right?";
		dialogue[2][5]="Maybe my guard gave you a sword. You will need another weapon.";
		dialogue[2][6]="Bring me 5 boar’s tooth and I will give you a powerful bow.";
		dialogue[3][0]="Nice try!!! Here is your arrow.";
		dialogue[3][1]="Thank you!!!";
		dialogue[3][2]="Now go to the South and entry the cave, the monster is inside!";
	
	
		
	}
	
}
