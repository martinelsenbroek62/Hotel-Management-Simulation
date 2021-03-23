package Managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HteManager implements Runnable, HteListener {

	/**
	 * Checks if thread should continue executing
	 */
	private boolean stop = false;

	/**
	 * Checks if EventManager should be pauzed
	 */
	private boolean pause = false;

	private Thread thread;
	private String threadName = "hte_timer";
	private ArrayList<Icontrol> managerList = new ArrayList<Icontrol>();

	/**
	 * Counter to save at which HTE time the manager is Used for checking and
	 * pausing
	 */
	private int counterHTE;

	/**
	 * Amount of time the eventmanager will wait before going to the next HTE
	 */
	private double timer;

	/**
	 * Factor to alter the time the eventmanager will wait 1.0 is default speed.
	 * example: 0.5 is faster 2.0 is slower
	 */
	private double factor = 1D;

	public HteManager() {
		stop = false;
		pause = true;
		System.out.println(threadName + " has created its thread");
		factor = 1.0;
		counterHTE = 0;
		timer = 1000.0;
	}

	/**
	 * Helper to get the current time in one place, instead of 3 or more
	 * 
	 * @return long
	 */
	private long getCurrentTime() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
	}

	/**
	 * Starts a new thread and makes it run
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	/**
	 * Stops the thread Not able to continue to play this way
	 */
	public void stop() {
		stop = true;
	}

	/**
	 * Pauzes the EventManager Does not stop the thread, stops checking for events
	 * Able to unpause
	 */
	public void pause() {
		pause = !pause;
	}

	/**
	 * Changes the speed of the firing Allowed: > 0 && < 2 / all numbers between
	 * 
	 * @param factor
	 * @return if changed succesfull
	 */
	public boolean changeSpeed(double factor) {
		if (factor > 0 && factor < 2) {
			this.factor = factor;
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		Thread hotelEventManager = null;
		for (Thread t : Thread.getAllStackTraces().keySet()) {
			if (t.getName().equals("hotelEventManager")) {
				hotelEventManager = t;
			}
		}
		
		boolean cont;
		while (!stop) {
			long startTime = getCurrentTime();
			cont = true;
			
			while (cont) {
				cont = hotelEventManager.isAlive();
				long currenttTime = getCurrentTime();
				if (pause) {
					if (currenttTime >= startTime + (timer * factor)) {
						counterHTE++;
						System.out.println("HTE: " + counterHTE);
						notifyManager();
						startTime = getCurrentTime();
					}
				}
			}
			stop = true;
		}
		System.out.println(threadName + " has stopped");
	}

	/**
	 * registers an iManager
	 * 
	 * @param manager
	 * @return if manager is added successfully
	 */
	@Override
	public boolean registerManager(Icontrol manager) {
		if (managerList.contains(manager)) {
			return false;
		}
		managerList.add(manager); 
		return true;
	}

	/**
	 * deregisters an iManager that was registered
	 * 
	 * @param manager
	 * @return if manager is removed successfully
	 */
	@Override
	public boolean deregisterManager(Icontrol manager) {
		if (managerList.contains(manager)) {
			managerList.remove(manager); 
			return true;
		}
		return false;
	}

	/**
	 * Notifies all registered Managers
	 * 
	 * @return if changed succesfull
	 */
	@Override
	public void notifyManager() {
		for(Icontrol manager : managerList) {
			manager.Notify();
		}
	}
}