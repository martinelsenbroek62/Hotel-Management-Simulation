package hotelCharacters;


import hotelObject.Room;
import layoutHandler.JSONReader;

public class Guest extends Character{

	//private Handler handler;
	private int id;
	private int star;
	private int HTECount;
	private boolean leaving = false;
	
	public Guest(JSONReader reader, int dimX, int dimY,int id, int star) {
		super(reader);
		this.id = id;
		this.star = star;
	
		//loading image into guest
		img=loader.loadImage("/images/character.png");
	}

	
	public int getId() {
		return id;
	}
	
	public int getStar() {
		return star;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public void goToRoom(){
		Room room = getRoom();
	this.getJSONReader().getKortstePad(getPositionX(),getPositionY(), room.getPositionX(), room.getPositionY(),
			this);
		this.clearDestination();
	}
	public boolean isAtDestination() {
		if (getDestination() == null) {
			return false;
		}
		return getPositionX() == getDestination().getPositionX() && getPositionY() == getDestination().getPositionY();
	}
	
	public void hteCountDown() {
		if (this.getPositionX() == getDestination().getPositionX()&& getPositionY() == getDestination().getPositionY()){
			HTECount -= 1;
			if (HTECount == 0) {
				this.goToRoom();
			}
		}
	}
	public void setHTECount(int hTECount) {
		HTECount = hTECount;
	}
		public void checkedOut() {
			leaving = true;
			getJSONReader().getKortstePad(getPositionX(), getPositionY(), 0, 9, this);
		}
		
		public boolean getCheckedOut() {
			return leaving;
		}
		

	public boolean hasLeft() {
		return leaving && getPositionX() == 0 && getPositionY() == 9;
	}

	
	@Override
	public void setRoom(Room availableRoom) {
		super.setRoom(availableRoom);
		goToRoom();
	}

	@Override
	public boolean getVisible() {
		return super.getVisible() && !isInRoom();
	}

	@Override
	public void update(){
		super.update();
		if (!isInRoom() && getDestination() != null){
			hteCountDown(); 
		}
	}


}
