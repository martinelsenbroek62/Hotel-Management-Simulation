package Managers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import hotel.Hotel;
import layoutHandler.JSONReader;

public class LayoutManager extends Manager{
	private JSONReader reader;
	private Hotel hotel;
	public LayoutManager(JSONReader reader, Hotel hotel) {
		super(reader);
		this.reader = reader;
		this.hotel = hotel;
	}
	
	public void Notify() {
		hotel.render();
	}

}
