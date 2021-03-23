package Managers;

import hotelEvents.HotelEventListener;
import hotelEvents.HotelEventManager;
import layoutHandler.JSONReader;

public class HotelManager {
	private HotelEventManager hotelEventManager;
	private HteManager hteManager;
	private Double globalHTE = 1D;
	private JSONReader reader;
	private int stairsHTE = 1;
	private int cleanerHTE = 1;
	private int movieHTE = 1;
	
	public HotelManager(JSONReader reader, HteManager hteManager, HotelEventManager hem) {
		this.reader = reader;
		this.hteManager = hteManager;
		this.hotelEventManager = hem;
		// TODO Auto-generated constructor stub
	}

	public void setHTETimers(Double g, int s, int c, int m){
		globalHTE = g;
		stairsHTE = s;
		cleanerHTE = c;
		movieHTE = m;
	}
	
	public boolean register(HotelEventListener listener) {
		return hotelEventManager.register(listener);
	};
	
	public boolean registerManager(Icontrol manager) {
		return hteManager.registerManager(manager);
	}
	
	public Double getGlobalHte() {
		return globalHTE;
	}

	public int getStairsHte() {
		return stairsHTE;
	}

	public int getCleanerHte() {
		return cleanerHTE;
	}

	// get Movie hte
	public int getMovieHte() {
		return movieHTE;
	}

	// Starting hteCounter and hotelEventManager timers
	public void start() {
		hotelEventManager.start();
		hteManager.start();
	}
	
	// Stopping hteCounter and hotelEventManager timers
	public void stop() {
		hteManager.stop();
		hotelEventManager.stop();
	}
	
	// Paused hteCounter and hotelEventManager timers
	public void pause() {
		hteManager.pause();
		hotelEventManager.pause();
	}
	
	// Setting HTE speed
	public void changeSpeed() {
		hotelEventManager.changeSpeed(globalHTE);
		hteManager.changeSpeed(globalHTE);
	}
}
