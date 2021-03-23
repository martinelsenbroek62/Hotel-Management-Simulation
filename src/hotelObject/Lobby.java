package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Lobby extends GameObject {

	public Lobby(int x, int y, int dimX, int dimY) {
		super(x, y, dimX, dimY);
		// TODO Auto-generated constructor stub
		img=loader.loadImage("/images/lobby.png");
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
