
package hotelObject;

import java.awt.*;

import imageAdd.ImageLoader;

public abstract class GameObject {
	// x and y are X and Y coordinates of any object in the game
	public int PositionX;
	public int PositionY;
	// dimX and DimY are dimensions of the hotel room
	public int dimX;
	public int dimY;
	protected ImageLoader loader;
	protected Image img; // this image is assigned to object

	public GameObject(int x, int y, int dimX, int dimY) {
		img = null;
		loader = new ImageLoader();
		this.PositionX = x;
		this.PositionY = y;
		this.dimX = dimX;
		this.dimY = dimY;
	}

	public abstract void tick();

	// this method is called when object is created.. its graphics are rendered
	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		System.out.println(x + "-" + y + "-"+ dimX +" - " + dimY );
//		System.out.println("RENDERING!!!");
		g.drawImage(img, PositionX * 50, PositionY * 50, dimX * 50, dimY * 50, null);

	}

	// this method returns current coordinates of any game object.. used in
	// collision detection
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public int height() {
		return dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public int getDimX() {
		return dimX;
	}

	public int getPositionX() {
		return PositionX;
	}

	public int getPositionY() {
		return PositionY;
	}

}
