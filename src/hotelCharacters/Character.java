
package hotelCharacters;

import java.awt.*;
import java.util.LinkedList;

import Dijkstra.Node;
import hotelObject.GameObject;
import hotelObject.Room;
import imageAdd.ImageLoader;
import layoutHandler.JSONReader;

public abstract class Character extends GameObject implements iCharacter {
	private int positionX = 1;
	private int positionY = 0;
	private int dimX;
	private int dimY;
	private GameObject obj;
	protected ImageLoader loader;
	protected Image img = null; // this image is assigned to object
	private JSONReader reader;
	private Room room = null;
	private LinkedList<Node> path = new LinkedList<Node>();

	public Character(JSONReader reader) {
		super(0, 1, 1, 1);
		this.reader = reader;
		loader = new ImageLoader();

	}

	public JSONReader getJSONReader() {
		return reader;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public GameObject getDestination() {
		return obj;
	}

	public void clearDestination() {
		obj = null;
	}

	public void setNewDestination(GameObject object) {
		reader.getKortstePad(positionX, positionY, obj.getPositionX(), obj.getPositionY(), (Guest) this);
		obj = object;
	}

	public void setNewPosition(int x, int y) {
		positionX = x;
		positionY = y;
	};

	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}

	public boolean hasPath() {
		return path.peekFirst() != null;
	}

	public boolean getVisible() {
		if (reader == null) {
			return true;
		} else if (positionX != reader.getX() && positionY != reader.getY()) {
			return true;
		}
		return false;
	}

	public boolean isInRoom() {
		if (room == null) {
			return false;
		}
		return positionX == room.getPositionX() && positionY == room.getPositionY();
	}

	public void update() {
		if (path.peekFirst() != null) {
			String position[] = path.getFirst().name.split(" - ");
			int x = Integer.parseInt(position[1]);
			int y = Integer.parseInt(position[0]);
			setNewPosition(x, y);
			path.removeFirst();
		}
	}

	public void tick() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		super.render(g);

//		g.drawImage(img,positionX,positionY,dimX,dimY,null);

	}

}
