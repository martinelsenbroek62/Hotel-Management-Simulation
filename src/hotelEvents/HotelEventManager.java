package hotelEvents;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class HotelEventManager implements Runnable {
    /**
     * List of listeners to notify of a new HotelEvent
     */
    private ArrayList<HotelEventListener> listeners;

    /**
     * List of events happening in the .jar
     */
    private ArrayList<HotelEvent> events;

    /**
     * Checks if thread should continue executing
     */
    private boolean stop;

    /**
     * Checks if EventManager should be pauzed
     */
    private boolean pause;

    private Thread t;
    private String threadName;

    /**
     * Counter to save at which HTE time the manager is
     * Used for checking and pausing
     */
    private int counterHTE;

    /**
     * Amount of time the eventmanager will wait before going to the next HTE
     */
    private double fireEventTimer;

    /**
     * Factor to alter the time the eventmanager will wait
     * 1.0 is default speed.
     * example: 0.5 is faster
     * 2.0 is slower
     */
    private double fireEventFactor;

    public HotelEventManager() {
        listeners = new ArrayList<HotelEventListener>();
        events = new ArrayList<HotelEvent>();
        stop = false;
        pause = true;

        threadName = "hotelEventManager";
        System.out.println(threadName + " has created its thread");

        fireEventTimer = 1000.0;
        fireEventFactor = 1.0;
        counterHTE = 0;

        //EVENTS
        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 2, new HashMap<String, String>() {{
            put("Guest: 1", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 20, new HashMap<String, String>() {{
            put("Guest", "1");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 2, new HashMap<String, String>() {{
            put("Guest: 2", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 30, new HashMap<String, String>() {{
            put("Guest", "2");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 4, new HashMap<String, String>() {{
            put("Guest: 3", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 25, new HashMap<String, String>() {{
            put("Guest", "3");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 50, new HashMap<String, String>() {{
            put("Guest", "3");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 5, new HashMap<String, String>() {{
            put("Guest: 4", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_FITNESS, "", 25, new HashMap<String, String>() {{
            put("Guest: 4", "10 HTE");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 5, new HashMap<String, String>() {{
            put("Guest: 5", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 26, new HashMap<String, String>() {{
            put("Guest", "5");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 8, new HashMap<String, String>() {{
            put("Guest: 6", "3 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 28, new HashMap<String, String>() {{
            put("Guest", "6");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 8, new HashMap<String, String>() {{
            put("Guest: 7", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 45, new HashMap<String, String>() {{
            put("Guest", "7");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 10, new HashMap<String, String>() {{
            put("Guest: 8", "3 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 25, new HashMap<String, String>() {{
            put("Guest", "8");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 45, new HashMap<String, String>() {{
            put("Guest", "8");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 12, new HashMap<String, String>() {{
            put("Guest: 9", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 35, new HashMap<String, String>() {{
            put("Guest", "9");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 13, new HashMap<String, String>() {{
            put("Guest: 10", "2 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 30, new HashMap<String, String>() {{
            put("Guest", "10");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 70, new HashMap<String, String>() {{
            put("Guest", "10");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 14, new HashMap<String, String>() {{
            put("Guest: 11", "5 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_FITNESS, "", 30, new HashMap<String, String>() {{
            put("Guest: 11", "20 HTE");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 14, new HashMap<String, String>() {{
            put("Guest: 12", "4 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 65, new HashMap<String, String>() {{
            put("Guest", "12");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 14, new HashMap<String, String>() {{
            put("Guest: 13", "5 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CLEANING_EMERGENCY, "", 35, new HashMap<String, String>() {{
            put("Guest", "13");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 20, new HashMap<String, String>() {{
            put("Guest: 14", "4 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_FITNESS, "", 25, new HashMap<String, String>() {{
            put("Guest: 14", "15 HTE");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 20, new HashMap<String, String>() {{
            put("Guest: 15", "4 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 35, new HashMap<String, String>() {{
            put("Guest", "15");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 55, new HashMap<String, String>() {{
            put("Guest", "15");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 22, new HashMap<String, String>() {{
            put("Guest: 16", "4 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 90, new HashMap<String, String>() {{
            put("Guest", "16");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 22, new HashMap<String, String>() {{
            put("Guest: 17", "3 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 50, new HashMap<String, String>() {{
            put("Guest", "17");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 55, new HashMap<String, String>() {{
            put("Guest", "17");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 24, new HashMap<String, String>() {{
            put("Guest: 18", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 50, new HashMap<String, String>() {{
            put("Guest", "18");
        }}));
        events.add(new HotelEvent(HotelEventType.CHECK_OUT, "", 90, new HashMap<String, String>() {{
            put("Guest", "18");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 26, new HashMap<String, String>() {{
            put("Guest: 19", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_FITNESS, "", 70, new HashMap<String, String>() {{
            put("Guest: 19", "25 HTE");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 20", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_CINEMA, "", 80, new HashMap<String, String>() {{
            put("Guest", "9");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 21", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_CINEMA, "", 75, new HashMap<String, String>() {{
            put("Guest", "21");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 22", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_CINEMA, "", 55, new HashMap<String, String>() {{
            put("Guest", "22");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 23", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_CINEMA, "", 50, new HashMap<String, String>() {{
            put("Guest", "23");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 24", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 65, new HashMap<String, String>() {{
            put("Guest", "24");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 28, new HashMap<String, String>() {{
            put("Guest: 25", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 58, new HashMap<String, String>() {{
            put("Guest", "25");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 50, new HashMap<String, String>() {{
            put("Guest: 26", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.NEED_FOOD, "", 65, new HashMap<String, String>() {{
            put("Guest", "26");
        }}));

        events.add(new HotelEvent(HotelEventType.CHECK_IN, "", 55, new HashMap<String, String>() {{
            put("Guest: 27", "1 stars");
        }}));
        events.add(new HotelEvent(HotelEventType.GOTO_CINEMA, "", 65, new HashMap<String, String>() {{
            put("Guest", "27");
        }}));


        events.add(new HotelEvent(HotelEventType.START_CINEMA, "", 80, new HashMap<String, String>() {{
            put("ID", "1");
        }}));

        events.add(new HotelEvent(HotelEventType.EVACUATE, "Fire panic everyone out", 100, new HashMap<String, String>() {{
            put("FIRE EVERYWHERE", "GET YO ASS OUT OF HERE");
        }}));

        events.add(new HotelEvent(HotelEventType.GODZILLA, "", 142, new HashMap<String, String>() {{
            put("BOW DOWN TO YOUR GOD", "WHAT YOU GONNA DO?");
        }}));

        events.sort(Comparator.comparingInt(x -> x.Time));

    }

    /**
     * Registers a HotelEventListener to the EventManager
     * @param listener
     * @return true = succesful adding
     */
    public boolean register(HotelEventListener listener) {
        if (listeners.contains(listener)) {
            return false;
        } else {
            listeners.add(listener);
            return true;
        }
    }

    /**
     * Deregisters a existing HotelEventListener from the EventManager
     *
     * @param listener
     * @return true = succesfull removal
     */
    public boolean deregister(HotelEventListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        System.out.println(threadName + " is running its thread");

        boolean cont;

        while (!stop) {
            long startTime = getCurrentTime();
            cont = true;

            while (cont) {
                long currenttTime = getCurrentTime();

                //If pauzed, keep running, but stop checking time and firing events.
                if(pause){
                    //check the difference between start and current time
                    //if the difference is more than the fireEventTimer, a new event is fired
                    if (currenttTime >= startTime + (fireEventTimer * fireEventFactor)) {
                        //cont = false;
                        counterHTE++;

//                        System.out.println("Event fired at time: " + counterHTE);

                        cont = fireEvent();

                        //Reset the start time, so the check will have to fail untill the right amount of time has passed
                        startTime = getCurrentTime();
                    }
                }
            }
            stop = true;
        }
        System.out.println(threadName + " has stopped");
    }

    private boolean fireEvent() {

        if (events.isEmpty()) {
            return false;
        }

        //get the first event from the list
        HotelEvent event = events.get(0);

        //check if that event is suppose to be fired at that moment
        if (event.Time == counterHTE) {
            System.out.println(event.toString());

            //notify all listeners of event
            for (HotelEventListener listener : listeners
                    ) {
                listener.Notify(event);
            }

            //event is fired, thus useless. Remove from event lists
            events.remove(0);

            //Recursion to check if there are multiple events at the same time
            fireEvent();

            return true;
        } else if (event.Time > counterHTE) {
            //No event right now, but in the future
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper to get the current time in one place, instead of 3 or more
     * @return long
     */
    private long getCurrentTime() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    /**
     * Starts a new thread and makes it run
     */
    public void start() {
        System.out.println(threadName + " has started its thread");
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    /**
     * Stops the thread
     * Not able to continue to play this way
     */
    public void stop() {
        stop = true;
    }

    /**
     * Pauzes the EventManager
     * Does not stop the thread, stops checking for events
     * Able to unpause
     */
    public void pause() {
        pause = !pause;
    }

    /**
     * Changes the speed of the firing
     * Allowed: > 0 && < 2 / all numbers between
     * @param factor
     * @return if changed succesfull
     */
    public boolean changeSpeed(double factor){
        if(factor > 0 && factor < 2){
            fireEventFactor = factor;
            return true;
        } else {
            return false;
        }
    }

}

