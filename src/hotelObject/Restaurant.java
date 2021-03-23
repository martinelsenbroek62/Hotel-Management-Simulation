package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Restaurant extends GameObject {

	private int capacity;
	//private Handler handler;
	public Restaurant(int x, int y, int dimX, int dimY, int capacity) {
		super(x, y, dimX, dimY);
		this.capacity=capacity;
		//this.handler=handler;
		img=loader.loadImage("/images/restaurant.png");
		// TODO Auto-generated constructor stub
	}

	public void setCapacity(int capacity)
	{
		this.capacity=capacity;
	}
	
	public int getCapactiy()
	{
		return capacity;
	}


	public void tick() {
		// TODO Auto-generated method stub
		
	}


	
}
