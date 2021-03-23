package layoutHandler;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

import Dijkstra.Dijkstra;
import Dijkstra.Node;
import Managers.HotelManager;
import hotelCharacters.Guest;
import hotelObject.Cinema;
import hotelObject.Fitness;
import hotelObject.GameObject;
import hotelObject.Lift;
import hotelObject.Lobby;
import hotelObject.Restaurant;
import hotelObject.Roof;
import hotelObject.Room;
import hotelObject.Stairs;

public class JSONReader extends Canvas {
	private Node hotelNodeLayout[][];
	private HotelManager hotelManager;
	public ArrayList<Room> rooms = new ArrayList<Room>();
	public ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	public ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	public ArrayList<Fitness> fitness = new ArrayList<Fitness>();
	public ArrayList<Stairs> stairs = new ArrayList<Stairs>();
	public ArrayList<Roof> roof = new ArrayList<Roof>();
	public ArrayList<Lift> lift = new ArrayList<Lift>();
	public ArrayList<Lobby> lobby = new ArrayList<Lobby>();
	private int height;
	private int width;
	public HashMap<Integer, Guest> gasten = new HashMap<Integer, Guest>();
	// public HashMap<Integer, Maid> maids = new HashMap<Integer, Maid>();
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();// this is the list of all the objects in the
																		// grid
	// ArrayList<Character> characterObject = new ArrayList<Character>();

	private JSONReader reader;

	private int tSizeX = 0, tSizeY = 0;

	public String getReadAll() {
		String areaType = null, roomStar = null;
		int posX = 0, posY = 0, dimX = 0, dimY = 0, capacity = 0;

		JSONObject object = new JSONObject();

		try {
			String bluePrint = new String(Files.readAllBytes(Paths.get("./src/hotelLayOut/hotel4.layout")));

			JSONArray array = new JSONArray(bluePrint);
			for (int i = 0; i < array.length(); i++) {
				object = array.getJSONObject(i);

				posX = Integer.parseInt(object.getString("Position").split(", ")[0]);
				posY = Integer.parseInt(object.getString("Position").split(", ")[1]);
				dimX = Integer.parseInt(object.getString("Dimension").split(", ")[0]);
				dimY = Integer.parseInt(object.getString("Dimension").split(", ")[1]);

				if (posX + dimX > tSizeX)
					tSizeX= posX + dimX;
				if (posY + dimY > tSizeY)
					tSizeY = posY + dimY;

				areaType = object.getString("AreaType");
				if (areaType.equalsIgnoreCase("Room")) {
					roomStar = object.getString("Classification");
					if (roomStar.equalsIgnoreCase("1 Star")) {
						rooms.add(new Room(posX, posY, dimX, dimY, 1));
//						System.out.println("1 Star room created");

					}

					else if (roomStar.equalsIgnoreCase("2 Star")) {
						rooms.add(new Room(posX, posY, dimX, dimY, 2));
//						System.out.println("2 Star room created");

					}

					else if (roomStar.equalsIgnoreCase("3 Star")) {
						rooms.add(new Room(posX, posY, dimX, dimY, 3));
//						System.out.println("3 Star room created");

					} else if (roomStar.equalsIgnoreCase("4 Star")) {
						rooms.add(new Room(posX, posY, dimX, dimY, 4));
//						System.out.println("4 Star room created");
					} else if (roomStar.equalsIgnoreCase("5 Star")) {
						rooms.add(new Room(posX, posY, dimX, dimY, 5));
//						System.out.println("4 Star room created");
					}
				}

				else if (areaType.equalsIgnoreCase("Cinema")) {
					cinemas.add(new Cinema(posX, posY, dimX, dimY));
//					System.out.println("Cinema created");
				}

				else if (areaType.equalsIgnoreCase("Restaurant")) {
					capacity = object.getInt("Capacity");
					restaurants.add(new Restaurant(posX, posY, dimX, dimY, capacity));
//					System.out.println("Restaurant created");
				}

				else if (areaType.equalsIgnoreCase("Fitness")) {
					fitness.add(new Fitness(posX, posY, dimX, dimY));
//					System.out.println("Fitness created");
				}

			}

			createLift();
			createStairs();
			createLobby();
			createRoof();
			System.out.println(tSizeX + "_" + tSizeY);
			System.out.println((tSizeX + ", " + tSizeY));
			load();
			height = tSizeY;
			width = tSizeX+1;
			create2DGrid();
			return (tSizeX + ", " + tSizeY);
		} catch (FileNotFoundException e) {
			System.out.println("NOT FOUND:");
			return ("NOT FOUND");
		} catch (Exception e) {
			System.out.println(e);
			return ("EXCEPTION");
		}
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	public ArrayList<Fitness> getFitness() {
		return fitness;
	}

	public void createLift() {
		int posX = 0, posY = 0;
			lift.add(new Lift(posX, posY, 1,tSizeY ));
			posY += 1;
		}
	

	public void createStairs() {
		int posX = tSizeX-1, posY = 0;
			stairs.add(new Stairs(posX, posY, 1,tSizeY));
			posY += 1;
		}



//De lobby wordt 1 keer uitgelezen en vervolgens in een array geplaatst
	public void createLobby() {
		int posX = 1, posY = tSizeY;
		int sizeX = (tSizeX - 2), sizeY = 1;
		lobby.add(new Lobby(posX, posY, sizeX, sizeY));
	}

	public void createRoof() {
		int posX = 0, posY = 0;
		int sizeX = (tSizeX + 1), sizeY = 1;
		roof.add(new Roof(posX, posY, sizeX, sizeY));

	}

	public ArrayList<Lift> getLift() {
		return lift;

	}

	public ArrayList<Stairs> getStairs() {
		return stairs;
	}

	public ArrayList<Lobby> getLobby() {
		return lobby;
	}

	public ArrayList<Roof> getRoof() {
		return roof;
	}

	// private Handler handler=null;
	public void load() {

		for (GameObject object : getRooms()) {
			addObject(object);
		}

		for (GameObject object : getRestaurants()) {
			addObject(object);
		}

		for (GameObject object : getCinemas()) {
			addObject(object);
		}

		for (GameObject object : getFitness()) {
			addObject(object);
		}

		for (GameObject object : getLobby()) {
			addObject(object);
		}

		for (GameObject object : getLift()) {
			addObject(object);
		}

		for (GameObject object : getRoof()) {
			addObject(object);
		}

		for (GameObject object : getStairs()) {
			addObject(object);
		}
	}

	private void create2DGrid() {
		hotelNodeLayout = new Node[height][width];
		for (int length = 0; length < hotelNodeLayout.length; length++) {
			System.out.println("-----");
			System.out.println(hotelNodeLayout[length].length);
		}

		objects.forEach(grid -> {
			int currentwidth = grid.getPositionX();
			int dimensionWidth = grid.getDimX();
			int currentLength = grid.getPositionY();
			int dimensionLength = grid.getDimY();
//			System.out.println(currentwidth);
//			System.out.println(dimensionWidth);
			//System.out.println(currentLength);
//			System.out.println(dimensionLength);
			for (int width = currentwidth; width < (currentwidth + dimensionWidth); width++) {
				for (int length = currentLength; length < (currentLength + dimensionLength); length++) {
				System.out.println(width +"_"+ currentwidth);
				System.out.println(length+"_"+ currentLength);
				System.out.println("=====");
					hotelNodeLayout[length][width] = new Node(length + " - " + width);
					
				}
				
			}
			
		});
		
		

//		System.out.println(hotelNodeLayout.length);
		for (int length = 0; length < hotelNodeLayout.length; length++) {
			for (int width = 0; width < hotelNodeLayout[length].length; width++) {
					if (width == 0 || width == this.width - 1) {
						if (length >= 0) {
							int stairHTE = width == this.width - 1 ? Math.round(hotelManager.getStairsHte()) : 1;
							if (length != height - 1) {
								hotelNodeLayout[length][width].neighbours.put(hotelNodeLayout[length + 1][width],
										stairHTE);
							}
							if (length < height && length != 0) {
								hotelNodeLayout[length][width].neighbours.put(hotelNodeLayout[length - 1][width],
										stairHTE);
							}

							if (width == this.width - 1 && hotelNodeLayout[length][width - 1] != null) {
								hotelNodeLayout[length][width].neighbours.put(hotelNodeLayout[length][width - 1], 1);
							}
						}
					} else {

						if (hotelNodeLayout[length][width] != null) {
							hotelNodeLayout[length][width].neighbours.put(hotelNodeLayout[length][width - 1], 1);
						}
						if (hotelNodeLayout[length][width + 1] != null) {
							hotelNodeLayout[length][width].neighbours.put(hotelNodeLayout[length][width + 1], 1);
						}
					}
			}
		}
	
	}

	// object is added into the game with this method
	public void addObject(GameObject object) {
		objects.add(object);
		int width = object.getPositionX() + object.getDimX();
		int height = object.getPositionY() + object.getDimY();

		if (height > this.height) {
			this.height = height;
		}
		if (width > this.width) {
			this.width = width;
		}

	}

	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).tick(); // tick method for all the gameobjects is called
		}

	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g); // render method for all the objects is called
		}

		gasten.forEach((id, gast) -> gast.render(g));
		// maids.forEach((id,maid) -> maid.render(g));
	}

	public void getKortstePad(int startX, int startY, int endX, int endY, Guest guest) {

		// make instance of pathfinding-class
		Dijkstra _ds = new Dijkstra();
		System.out.println(startX); // <== startpositie 150 =/= 3
		System.out.println(startY);
		hotelNodeLayout[startY][startX].distance = 0;
		System.out.println(hotelNodeLayout);
		LinkedList<Node> path = _ds.dijkstra(hotelNodeLayout[startY][startX], hotelNodeLayout[endY][endX]);

		for (Node rows[] : hotelNodeLayout) {
			for (Node node : rows) {
				if (node != null) {
					node.distance = Integer.MAX_VALUE;
					node.latest = null;
				}
			}
		}
		guest.setPath(path);
	}

}
