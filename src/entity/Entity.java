package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
public abstract class Entity {
	public GamePanel gp;
	 public String name;
	 public int screenX,screenY;
	 public int worldX,worldY;	
	 public int speed;
	
	
	//action
	 public int spriteCounter=0;
	 public int spriteNum=1;
	 public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	 public BufferedImage attackup1, attackup2, attackdown1, attackdown2, attackleft1, attackleft2, attackright1, attackright2;
	 public String direction="down";
	
	//Collision
	public boolean collisionOn;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	
	
	//HP
	public int maxLife;
	public int life;
	public int maxHp;
	public int hp;
	
	
	public boolean attacking = false;
	public int attack;
	public int actionLockCounter;
	public boolean takingDamage=false;
	public int takingDamageCounter = 0;//  creating invincible time(DANG)
	public boolean collision=true;
	
	public int width;
	public int height;
	
	//State
	
	
	public boolean knockBack = false;
	public int knockBackCounter;
	public int knockBackPower;

	
	
	
	public Entity(GamePanel gp) {
		this.gp=gp;
	}

	public abstract void update();
	
	public abstract void draw(Graphics2D g2);

	public BufferedImage setup(String imagePath,int width,int height) {
		this.width=width;
		this.height=height;
		BufferedImage image=null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image=UtilityTool.scaledImage(image, width, height);
		}catch(IOException e) {
			//e.printStackTrace();
			System.out.println(imagePath);
		}
		return image;
		
	}

	}
