package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_guard extends Human{
	public NPC_guard(GamePanel gp) {
		super(gp);
		direction="down";
		collision=true;
		getImage();
		setDialogue();
	}
	public void getImage() {
		down2=setup("/npc/guard_Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/npc/guard_Up1",gp.TILE_SIZE,gp.TILE_SIZE);
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

	
}
