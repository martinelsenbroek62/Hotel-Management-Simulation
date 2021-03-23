package hotelEvents;

import java.util.HashMap;
import java.util.Map;

//Event that is passed on to the listeners
public class HotelEvent {
    public HotelEventType Type;

    public String Message;

    public int Time;

    //Data is GuestID + General type data such as Number of Stars
    public Map<String, String> Data;

    HotelEvent(HotelEventType _type, String _message, int _time, HashMap _data){
        Type = _type;
        Message = _message;
        Time = _time;
        Data = _data;

    }

    @Override
    public String toString(){
        return "Event: " + Type + ", HTE: " + Time;
    }
}

