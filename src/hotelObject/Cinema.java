package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Cinema extends GameObject {

	//private Handler handler;

	public Cinema(int x, int y, int dimX, int dimY) {
		// TODO Auto-generated constructor stub
		
		super(x, y, dimX, dimY);
		//this.handler = handler;
		img=loader.loadImage("/images/cinema.jpg");
	}


	public void tick() {
		// TODO Auto-generated method stub
		
		// Loop door alle cinemas heen
			// Tel af zodat elke HTE een uur is van de cinema
			// Enzo....
	}


}
