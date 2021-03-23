package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Roof extends GameObject {

	public Roof(int x, int y, int dimX, int dimY) {
		super(x, y, dimX, dimY);
		// TODO Auto-generated constructor stub
		img=loader.loadImage("/images/dak.PNG");
	}

	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
