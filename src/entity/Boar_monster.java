package entity;
import main.GamePanel;
import java.util.Random;


public class Boar_monster extends Entity{
	GamePanel gp;
	public Boar_monster(GamePanel gp) {
        super(gp);
		this.gp = gp;
    type = monsterType;
	name = "Boar_Monster";
    speed= 1;
    maxLife= 4;
    life=maxLife;

    solidArea.x = 3; 
    solidArea.y= 18;
    solidArea.width = 42;
    solidArea.height = 30;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    getImage();
    }
    public void getImage(){
        up1=setup("/monster/Boar_Left_1", gp.tileSize, gp.tileSize);
		up2=setup("/monster/Boar_Left_2", gp.tileSize, gp.tileSize);
		down1=setup("/monster/Boar_Right_1", gp.tileSize, gp.tileSize);
		down2=setup("/monster/Boar_Right_2", gp.tileSize, gp.tileSize);
		left1=setup("/monster/Boar_Left_1", gp.tileSize, gp.tileSize);
		left2=setup("/monster/Boar_Left_2", gp.tileSize, gp.tileSize);
		right1=setup("/monster/Boar_Right_1", gp.tileSize, gp.tileSize);
		right2=setup("/monster/Boar_Right_2", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
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
public void damageReaction(){
	actionLockCounter = 0;
	direction = gp.player.direction;

}	
}


