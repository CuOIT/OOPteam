package object;



import entity.Entity;
import main.GamePanel;
public class OBJ_Sword extends Entity{
	public OBJ_Sword(GamePanel gp) {
	super(gp);
	
	attack = 2;
	type = swordType;//them dong code nay
	name="Sword";
	down1=setup("/objects/sword",gp.TILE_SIZE,gp.TILE_SIZE);
	description = "[" + name + "]\nA powerful sword.";
	knockBackPower = 10;
	stackable=true;//them dong code nay

}
}

