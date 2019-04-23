package game.assets;

import java.io.BufferedReader;
import java.io.FileReader;

public class Tilemap {

	int[][] mapcode;
	
	public Tilemap(String filepath, int width, int height) {
		mapcode = new int[height][width];
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));	
			String separator = " ";
			for(int i = 0;i < height;i++) {
				String line = br.readLine();
				if(line != null) {
					String[] token = line.split(separator);					
					for(int j = 0; j < width;j++) {
						mapcode[j][i] = Integer.parseInt(token[j]);
					}
					System.out.println();
				}
			}
			
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Tilemap() {
		// TODO Auto-generated constructor stub
	}
	
	public int[][] getMapcode() {
		return mapcode;
	}
}
