package Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hotelCharacters.Guest;
import hotelObject.Room;
import layoutHandler.JSONReader;


public class GuestManager extends Manager {

	public HashMap<Integer, Guest> gasten = new HashMap<Integer, Guest>();
	boolean hasCheckedOut = true;
    Character chacacter;
	public GuestManager(JSONReader reader) {
		super(reader);
	}

	public HashMap<Integer, Guest> getGasten() {
		return reader.gasten;
	}

	public Guest getGast(int key) {
		return reader.gasten.get(key);
	}

	/**
	 * Hierin wordt de data van de gast geconvert voor checkin
	 * 
	 * @param data
	 *            hashmap data met id en classificatie
	 */
	public void initiateCheckIn(Map<String, String> data) {
		Map.Entry<String, String> entry = convertData(data);
		int id = Integer.parseInt(entry.getKey().split(": ")[1]);
		int star = Integer.parseInt(entry.getValue().split(" ")[0]);
		System.out.println(id + "-- STAR:" + star);
		checkIn(id, star);
	}

	public boolean isValidCheckout(Map<String, String> data) {
		Map.Entry<String, String> entry = data.entrySet().iterator().next();
		String key = entry.getKey();
		boolean isCheckin = key.split(": ").length == 2;
		boolean guestRegistered = isCheckin && getGast(Integer.parseInt(key.split(": ")[1])) == null;

		boolean isValid = !guestRegistered;
		return isValid;
	}

	/**
	 * Hierin wordt de gast uit gechecked
	 * 
	 * @param data
	 *            hashmap data met id en classificatie
	 */
	public void checkOut(Map<String, String> data) {
		Map.Entry<String, String> entry = data.entrySet().iterator().next();
		String value = entry.getValue();
		int id = Integer.parseInt(value);
		Guest currentGuest = reader.gasten.get(id);
		if (currentGuest != null) {
//	nog doen	//	currentGuest.getRoom().setAvailable(true);
// nog doen		//	currentGuest.checkedOut();
			
		} else {
			System.out.println("404 GUEST NOT FOUND");
		}
	}

	/**
	 * Hierin worden de gasten aangemaakt en gekoppeld aan een kamer
	 * 
	 * @param id
	 *            de id van de gast
	 * @param star
	 *            de classification van de kamer
	 */
	public void checkIn(int id, int star) {
		if (reader.getRooms().size() > 0) {
			Room availableRoom = (Room) reader.getRooms().get(0);
			Guest guest = new Guest(reader, 1, 1,id, star);
			guest.setRoom(availableRoom);
			availableRoom.setAvailable(false);
			this.gasten.put(id, guest);
			guest.goToRoom();
		} else if (star <= 5) {
			checkIn(id, star + 1);
		} else {
			System.out.println("no room bye");
		}
	}


	/**
	 * Hierin gaat de gast naar een faciliteit m.b.v. het korstepad algoritme
	 * 
	 * @param destination
	 *            de faciliteit die bezocht moet worden
	 * @param gast
	 *            de gast die naar een faciliteit wilt
	 */
	public void goToDestination(String destination, Guest guest) {
		if (destination == "FOOD") {
			reader.getRestaurants().forEach(grid ->{
				//gast.setNewDestination(grid);
			});
		}
	}

	public void goToFitness(Map<String, String> data) { // ("Guest: 1", "8 HTE")
		Map.Entry<String, String> entry = convertData(data);
		int id = Integer.parseInt(entry.getKey().split(": ")[1]);
		int hte = Integer.parseInt(entry.getValue().split(" ")[0]);
		System.out.println("Fitness: " + id + " " + hte);
		reader.getFitness().forEach(grid -> {
			//this.gasten.get(id).setNewDestination(grid);
			reader.gasten.get(id).setHTECount(hte);
		});
	}

	@Override
	public void Notify() {
System.out.println("GUESTS HAVE BEEN NOTIFIED");

System.out.println(reader.gasten.size());
System.out.println("========");
		ArrayList<Integer> leftGuests = new ArrayList<Integer>();
		reader.gasten.forEach((key, gast) -> {
			gast.update();
			if(gast.hasLeft()) {
				leftGuests.add(key);
			}
		});
		
		leftGuests.forEach(id -> {
			reader.gasten.remove(id);
		});
	}

}
