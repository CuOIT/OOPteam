
	package object;

	import entity.Entity;
	import main.GamePanel;
	public class OBJ_Apple extends Entity {
		public OBJ_Apple(GamePanel gp) {
			super(gp);
			name="Apple";
			down1=setup("/objects/apple",gp.tileSize,gp.tileSize);
			description = "[" + name + "]Give them to the guard";
		}
		
	}

