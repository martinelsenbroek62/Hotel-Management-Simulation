package Managers;
import java.util.Map;

import hotelCharacters.Guest;
import hotelEvents.HotelEvent;
import hotelEvents.HotelEventListener;

public class Notifyer implements HotelEventListener {
	GuestManager guestManager;
    MaidManager  maidManager;
	CinemaManager cinemaManager;
	
	public Notifyer(GuestManager gastManager, MaidManager cleanerManager, CinemaManager cinemaManager) {
		this.guestManager = gastManager;
		this.maidManager = cleanerManager;
		this.cinemaManager = cinemaManager;
	}

	@Override
	public void Notify(HotelEvent event) {
		Map.Entry<String, String> data = event.Data.entrySet().iterator().next();
		
		if (event.Type.name().equals("CHECK_OUT")) {		
			if (guestManager.isValidCheckout(event.Data)) {
				int id = Integer.parseInt(data.getValue());
				guestManager.getGast(id).getRoom();
				maidManager.addCheckout(guestManager.getGast(id).getRoom());
				guestManager.checkOut(event.Data);
			} else {
				guestManager.initiateCheckIn(event.Data);
			}
			
		} else if (event.Type.name().equals("CHECK_IN")) {
			guestManager.initiateCheckIn(event.Data);
			
		} else if (event.Type.name().equals("CLEANING_EMERGENCY")) { // ("Guest", "11")
			int id = Integer.parseInt(data.getValue());
			Guest gast = guestManager.getGast(id);
			maidManager.addCleaningEmergency(gast.getRoom());
			
		} else if (event.Type.name().equals("EVACUATE")) {
			// Manager.SOS_EVAC_RUN_OMFG();
		} else if (event.Type.name().equals("NEED_FOOD")) { // ("Guest", "3");
			int id = Integer.parseInt(data.getValue());
			Guest gast = guestManager.getGast(id);
			guestManager.goToDestination("FOOD", gast);
			
		} else if (event.Type.name().equals("GOTO_CINEMA")) { // ("Guest", "2");
			int id = Integer.parseInt(data.getValue());
			Guest gast = guestManager.getGast(id);
			guestManager.goToDestination("CINEMA", gast);
			cinemaManager.addToCinema(id, gast);;
			
		} else if (event.Type.name().equals("GOTO_FITNESS")) { // ("Guest: 1", "8 HTE")
			guestManager.goToFitness(event.Data);
			
		} else if (event.Type.name().equals("START_CINEMA")) {
			String name = data.getValue();
			//cinemaManager.start(name);
		}
	}

}
