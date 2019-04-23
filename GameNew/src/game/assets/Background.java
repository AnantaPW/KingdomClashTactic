package game.assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {

	BufferedImage image;
	
	//===== attribute ======
	int x;
	int y;
	int width;
	int height;
	
	//===== attribute ======
	
	
	
	public Background(String filepath, int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
//		System.out.println("Filepath : "+filepath);
		
		try {
			image = ImageIO.read(new File(filepath));			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	public Image getImage() {
		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

}
