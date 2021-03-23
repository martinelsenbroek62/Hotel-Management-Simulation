package Managers;

import hotelCharacters.Guest;
import layoutHandler.JSONReader;

public class CinemaManager extends Manager {

	public CinemaManager(JSONReader reader) {
		super(reader);
		// TODO Auto-generated constructor stub
	}

	public void addToCinema(int id, Guest gast) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Notify() {
		// TODO Auto-generated method stub
		System.out.println("CINEMA HAS BEEN NOTIFIED");
	}

}
