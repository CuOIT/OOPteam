package entity;

import java.util.Random;

import main.GamePanel;
import main.UtilityTool;

public class NPC_guard extends Human{
	public NPC_guard(GamePanel gp) {
		super(gp); 
		direction="down";
		collision=true;
		getImage();
		setDialogue();
	}
	public void getImage() {
		head=setup("/npc/guardHead",gp.TILE_SIZE*2,2*gp.TILE_SIZE);
		down2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
	}
	public void setDialogue() {
		dialogue[1][0]="Excuse me. Where am I?";
		dialogue[1][1]="You’re on an abandoned island called Skull Island.";
		dialogue[1][2]="Oh no. How can I get out of here?";
		dialogue[1][3]="Maybe our master could give you the best answer.";
		dialogue[1][4]="So let me see him";
		dialogue[1][5]="He won’t see who is not worthy enough.";
		dialogue[1][6]="Bring me 5 mysteriuos apples and I will let you in.";
		dialogue[1][7]="Let me try!";
		dialogue[2][0]="Here you are.";
		dialogue[2][1]="Impressive, I have a gift for you.";
		dialogue[2][2]="Thanks!!!";
		dialogue[2][3]="This may help you in your upcoming journey";
		dialogue[2][4]="Now go to the West South and meet our master";
	}

	
}
