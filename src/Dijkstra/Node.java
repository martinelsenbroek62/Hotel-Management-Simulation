package Dijkstra;

import java.util.HashMap;

public class Node {

	// Hierin worden de Buren opgeslagen van een specifieke Node
    public HashMap<Node, Integer> neighbours;
    // Hierin wordt de afstand bepaald.
    public int distance;
    // Hierin wordt de vorige node opgeslagen waar wij mee checkte
    public Node latest;
    // Hier wordt de naam van de Node opgeslagen
    public String name;

    // In deze constructor worden de basis gegevens van de Node opgezet.
    public Node(String _name){
        neighbours = new HashMap<>();
        distance = Integer.MAX_VALUE;
        latest = null;
        name = _name;

    }

}
