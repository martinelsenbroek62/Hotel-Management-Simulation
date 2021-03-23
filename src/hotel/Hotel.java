package hotel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import Managers.HotelManager;
import Managers.HteManager;
import hotelView.MainScreen;
import layoutHandler.JSONReader;


public class Hotel extends Canvas {
	
	private JSONReader reader;
	private HotelManager hotelmanager;
	private HteManager hteManager;
	
	public int width, height;

	public Hotel(JSONReader reader, HteManager hteManager, HotelManager hotelManager) {
		try {
			this.reader = reader;
			this.hteManager = hteManager;
			this.hotelmanager = hotelManager;
					
			
			getWindowSize(reader);
			loadObjects();
			start();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


	public void start() {
		new MainScreen(width, height, "Hotel", this);
		render();
		hotelmanager.start();
	}

	// the tick and render method triggers on basis of game loop
	private void tick() {
		reader.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);

		g.fillRect(0, 0, width, height);
		reader.render(g);
		g.dispose();
		bs.show();
	}

	// load the objects into the game window...
	// objects mean all the items for example players,barricades keys etc
	public void getWindowSize(JSONReader reader) throws Exception {
		
		String dimensions = reader.getReadAll();
		System.out.println(dimensions);
		if (dimensions.equalsIgnoreCase("NOT FOUND") || dimensions.equalsIgnoreCase("EXCEPTION"))
			throw new Exception("CHECK STACK TRACE");
		this.width = Integer.parseInt(dimensions.split(", ")[0]) * 50 + 50;
		this.height = Integer.parseInt(dimensions.split(", ")[1]) * 50 + 70;// SIZE OF GRID
	}

	public void loadObjects() {
		reader.load();
	}

}
