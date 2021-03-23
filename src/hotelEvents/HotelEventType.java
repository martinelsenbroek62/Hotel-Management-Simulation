package hotelEvents;


public enum HotelEventType{
    NONE,        //empty event
    CHECK_IN,    //gastID, sterren
    CHECK_OUT,   //gastID
    CLEANING_EMERGENCY, //kamerID, tijd in HTE
    EVACUATE,    //.
    GODZILLA,    //.
    NEED_FOOD,   //gastID
    GOTO_CINEMA, //gastID
    GOTO_FITNESS,//gastID, HTE
    START_CINEMA //cinemaID
}
