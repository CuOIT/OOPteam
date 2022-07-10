package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu {


	BufferedImage image;
	public Menu(GamePanel gp){
	try {
	image=ImageIO.read(getClass().getResourceAsStream("/menu/menu_background.png"));
	image=UtilityTool.scaledImage(image,gp.TILE_SIZE*10,gp.TILE_SIZE*12);
	}catch(IOException e) {
		e.printStackTrace();
	}
}
}