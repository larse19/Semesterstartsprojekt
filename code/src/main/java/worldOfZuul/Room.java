package worldOfZuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
//import java.util.Iterator;

/**
 * A class representing the complex type of a room.
 * The class creates a room, and can be used for setting up connections between rooms,
 * as well as having the description of the room.
 * 
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    //private ArrayList<Interactor> objects;
    /**
     * Constructor of the room class.
     * The room is initialized with the help of the description of the room as the input.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    // A method for adding exits to a room.
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    // Returns the string description.
    public String getShortDescription()
    {
        return description;
    }

    // Returns the string description as a nice formattet reply.
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    // Returns all the rooms exits as a single string.
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    // Returns the exit for a given direction inputtet as a string.
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    /*
    public ArrayList<Interactor> getInteractors(){
        return this.objects;
    }*/
}

