
	package object;

	import entity.Entity;
	import main.GamePanel;
	public class OBJ_Apple extends Entity {
		public static int numberCollected=0;
		public OBJ_Apple(GamePanel gp) {
			super(gp);
			name="Apple";
			down1=setup("/objects/apple",gp.TILE_SIZE,gp.TILE_SIZE);
			description = "[" + name + "]\nGive them to the guard";
			stackable=true;
		}
		
	}

