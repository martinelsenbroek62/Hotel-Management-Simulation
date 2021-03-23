package hotelObject;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Room extends GameObject {

	private int starValue;
	private boolean isAvailable = true;
	
	//Handler handler;
	String imgName=null;
	String roomStatus=null; //status means dirty, cleaning, occupied etc
	public Room(int x, int y,int dimX, int dimY,int star)
	{
		super(x, y,dimX, dimY );
		starValue=star;
		//this.handler=handler;
		
		if(star==1)
			imgName="room1star.png";
		if(star==2)
			imgName="room2star.png";
		if(star==3)
			imgName="room3star.png";
		if(star==4)
			imgName="room4star.png";
		if(star==5)
			imgName="room5star.png";
		img=loader.loadImage("/images/"+imgName);
		
		
	}

	
	public void setStarValue(int star)
	{
		starValue=star;
	}
	
	public int getStarValue()
	{
		return starValue;
	}
	
	public void setRoomStatus(String status)
	{
		this.roomStatus=status;
	}
	
	public String getRoomStatus()
	{
		return roomStatus;
	}
	public boolean getIsAvailable() {
		return this.isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
