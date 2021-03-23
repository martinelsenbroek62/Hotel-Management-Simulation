package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Stairs extends GameObject {

	public Stairs(int x, int y, int dimX, int dimY) {
		super(x, y, dimX, dimY);
		// TODO Auto-generated constructor stub
		img=loader.loadImage("/Images/stairs.png");
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}


}
