package Dijkstra;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class Dijkstra {

    private LinkedList<Node> open;

    public Dijkstra(){
        open = new LinkedList<>();
    }

    /*
    Calculates shortest path between 2 nodes
    @par Node start = starting point
    @par Node end = goal to reach
     */
    public LinkedList<Node> dijkstra(Node start, Node end){

        Node toCheck = start;

        while(!visit(toCheck, end)){

            //Get the best option from the to-visit list
            Comparator<Node> comp = (n1, n2) -> Integer.compare(n1.distance, n2.distance);
            toCheck = open.stream().min(comp).get();
        }


        return makePath(end);
    }

    public  boolean visit(Node check, Node end){
        //check of emd
        if(check == end){
            return true;
        }

       
        if(open.contains(check)){
            open.remove(check);
        }

        //check neighbours
        for (Map.Entry<Node, Integer> entry : check.neighbours.entrySet()
             ) {

            //Distance to node if current node is followed
            int newDistance = check.distance + entry.getValue();

            //If the newly calculated distance is shorter than the current distance
            if(newDistance < entry.getKey().distance){

                //if so, we replace or add the distance
                entry.getKey().distance = newDistance;
                entry.getKey().latest = check;

                //check if we have seen the Node before, or if we have to add it to our to-visit list
                if(!open.contains(entry.getKey())){
                    open.add(entry.getKey());
                }
            }
        }

        return false;
    }


   
    private LinkedList<Node> makePath(Node end){
        boolean cont = true;
        LinkedList<Node> path = new LinkedList<Node>();
        Node current = end;

        while(cont){
            path.addFirst(current);
            if(current.latest != null){
                current = current.latest;
            } else {
                cont = false;
            }

        }

        return path;
    }
}
