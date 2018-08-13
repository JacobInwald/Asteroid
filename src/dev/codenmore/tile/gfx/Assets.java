package dev.codenmore.tile.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	private static final int numberWidth = 8, numberHeight = 8;
	private static final int uiObjectWidth = 64, uiObjectHeight = 64;
	

	public static BufferedImage player, enemyRocketShip, enemyShip, starMap_Tile1, starMap_Tile2,  starMap_Tile3, starMap_Tile4, starMap_Tile5, starMap_Tile6, starMap_Tile7, starMap_Tile8, Asteroid_Logo, bullet, gameOver, no1, no2, no3, no4, no5, no6, no7, no8, no9, no0;
	public static BufferedImage[] player_up, player_right, player_left, player_down;
	public static BufferedImage[] enemy_up, enemy_right, enemy_left, enemy_down;
	public static BufferedImage[] explosion;
	public static BufferedImage [] btnStart, mainMenu;
	
	public static void init() {
		//Creatures
		 
		 SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Creature_SpriteSheet.png"));
		 SpriteSheet ui_Images = new SpriteSheet(ImageLoader.loadImage("/textures/UI_Pictures.png"));
		 //SpriteSheet numbers = new SpriteSheet(ImageLoader.loadImage("/textures/8bit_Capital_Letters.png"));
		 SpriteSheet player_anims = new SpriteSheet(ImageLoader.loadImage("/textures/player_anims.png"));
		 SpriteSheet enemy_anims = new SpriteSheet(ImageLoader.loadImage("/textures/enemy_anims.png"));
		//UI Images
		 btnStart = new BufferedImage[2];
		 btnStart[0] = ui_Images.crop(0, 0, 64, 64);
		 btnStart[1] = ui_Images.crop(uiObjectWidth, 0, 64, 64);
		 
		 mainMenu = new BufferedImage[2];
		 mainMenu[0] = ui_Images.crop(uiObjectWidth * 4, 0, 64, 64);
		 mainMenu[1] = ui_Images.crop(uiObjectWidth * 5, 0, 64, 64);
		 
		 Asteroid_Logo = ui_Images.crop(uiObjectWidth * 2, 0, 64, 64);
		 gameOver = ui_Images.crop(uiObjectWidth * 3, 0, 64, 64);
		 //Numbers
		/* no0 = numbers.crop(0 , numberHeight * 5, numberWidth, numberHeight);
		 no1 = numbers.crop(numberWidth * 3 , numberHeight * 5, numberWidth, numberHeight);
		 no2 = numbers.crop(numberWidth * 5 , numberHeight * 5, numberWidth, numberHeight);
		 no3 = numbers.crop(numberWidth * 2, numberHeight * 4, numberWidth, numberHeight);
		 no4 = numbers.crop(numberWidth * 3 , numberHeight * 4, numberWidth, numberHeight);
		 no5 = numbers.crop(numberWidth * 4, numberHeight * 4, numberWidth, numberHeight);
		 no6 = numbers.crop(numberWidth * 5, numberHeight * 4, numberWidth, numberHeight);
		 no7 = numbers.crop(numberWidth * 4, numberHeight * 5, numberWidth, numberHeight);
		 no8 = numbers.crop(numberWidth * 2, numberHeight * 5, numberWidth, numberHeight);
		 no9 = numbers.crop(numberWidth, numberHeight * 5, numberWidth, numberHeight);
		 */
		 //Ranged Attacks
		 
		 bullet = sheet.crop(width * 3, 0, width, height);
		 
		 //Player Animation
		 player_up = new BufferedImage[5];
		 player_right = new BufferedImage[5];
		 player_left = new BufferedImage[5];
		 player_down = new BufferedImage[5];
		 
		 player_up[0] = player_anims.crop(0, 0, width, height);
		 player_up[1] = player_anims.crop(width, 0, width, height);
		 player_up[2] = player_anims.crop(width * 2, 0, width, height);
		 player_up[3] = player_anims.crop(width * 3, 0, width, height);
		 player_up[4] = player_anims.crop(width * 4, 0, width, height);
		 
		 player_right[0] = player_anims.crop(0, height, width, height);
		 player_right[1] = player_anims.crop(width, height, width, height);
		 player_right[2] = player_anims.crop(width * 2, height, width, height);
		 player_right[3] = player_anims.crop(width * 3, height, width, height);
		 player_right[4] = player_anims.crop(width * 4, height, width, height);
		 
		 player_left[0] = player_anims.crop(0, height * 3, width, height);
		 player_left[1] = player_anims.crop(width, height * 3, width, height);
		 player_left[2] = player_anims.crop(width * 2, height * 3, width, height);
		 player_left[3] = player_anims.crop(width * 3, height * 3, width, height);
		 player_left[4] = player_anims.crop(width * 4, height * 3, width, height);
		 
		 player_down[0] = player_anims.crop(0, height * 2, width, height);
		 player_down[1] = player_anims.crop(width, height * 2, width, height);
		 player_down[2] = player_anims.crop(width * 2, height * 2, width, height);
		 player_down[3] = player_anims.crop(width * 3, height * 2, width, height);
		 player_down[4] = player_anims.crop(width * 4, height * 2, width, height);
		 
		 //enemy animation
		 enemy_up = new BufferedImage[5];
		 enemy_right = new BufferedImage[5];
		 enemy_left = new BufferedImage[5];
		 enemy_down = new BufferedImage[5];
		 
		 enemy_up[0] = enemy_anims.crop(0, 0, width, height);
		 enemy_up[1] = enemy_anims.crop(width, 0, width, height);
		 enemy_up[2] = enemy_anims.crop(width * 2, 0, width, height);
		 enemy_up[3] = enemy_anims.crop(width * 3, 0, width, height);
		 enemy_up[4] = enemy_anims.crop(width * 4, 0, width, height);
		 
		 enemy_right[0] = enemy_anims.crop(0, height, width, height);
		 enemy_right[1] = enemy_anims.crop(width, height, width, height);
		 enemy_right[2] = enemy_anims.crop(width * 2, height, width, height);
		 enemy_right[3] = enemy_anims.crop(width * 3, height, width, height);
		 enemy_right[4] = enemy_anims.crop(width * 4, height, width, height);
		 
		 enemy_left[0] = enemy_anims.crop(0, height * 3, width, height);
		 enemy_left[1] = enemy_anims.crop(width, height * 3, width, height);
		 enemy_left[2] = enemy_anims.crop(width * 2, height * 3, width, height);
		 enemy_left[3] = enemy_anims.crop(width * 3, height * 3, width, height);
		 enemy_left[4] = enemy_anims.crop(width * 4, height * 3, width, height);
		 
		 enemy_down[0] = enemy_anims.crop(0, height * 2, width, height);
		 enemy_down[1] = enemy_anims.crop(width, height * 2, width, height);
		 enemy_down[2] = enemy_anims.crop(width * 2, height * 2, width, height);
		 enemy_down[3] = enemy_anims.crop(width * 3, height * 2, width, height);
		 enemy_down[4] = enemy_anims.crop(width * 4, height * 2, width, height);
		 
		 //Explosion Animation
		 explosion = new BufferedImage[5];
		 
		 explosion[0] = player_anims.crop(0, height * 4, width, height);
		 explosion[1] = player_anims.crop(width, height * 4, width, height);
		 explosion[2] = player_anims.crop(width * 2, height * 4, width, height);
		 explosion[3] = player_anims.crop(width * 3, height * 4, width, height);
		 explosion[4] = player_anims.crop(width * 4, height * 4, width, height);
		 
		 //Creatures
		 
		  player = sheet.crop(0, 0, width, height);
		  enemyShip = sheet.crop(width * 2, 0, width, height);
		  //Tiles
		  SpriteSheet starMapSheet = new SpriteSheet(ImageLoader.loadImage("/textures/StarMap_SpriteSheet.png"));
		  
		  starMap_Tile1 = starMapSheet.crop(0, 0, width, height);
		  starMap_Tile2 = starMapSheet.crop(width, 0, width, height);
		  starMap_Tile3 = starMapSheet.crop(width * 2, 0, width, height);
		  starMap_Tile4 = starMapSheet.crop(width * 3, 0, width, height);
		  starMap_Tile5 = starMapSheet.crop(0, height, width, height);
		  starMap_Tile6 = starMapSheet.crop(width, height, width, height);
		  starMap_Tile7 = starMapSheet.crop(width * 2, height, width, height);
		  starMap_Tile8 = starMapSheet.crop(width * 3, height, width, height);
	}
	
}
