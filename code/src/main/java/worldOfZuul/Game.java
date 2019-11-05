package worldOfZuul;

import java.util.ArrayList;

/**
 * This class handles most of the game logic. Calls the methods to get
 * userinput, as well as using said input to change the rooms.
 */
public class Game {

    private Parser parser;
    private Room currentRoom;
    private static int gameTick;
    //All the rooms
    private Room barn, fields, cropfield, cornfield, kitchen, well, restaurant, outside, bakery;
    private Inventory inventory;

    // Constructor for the class game, creates all the rooms and sets up the parser.
    public Game() {
        createRooms();
        parser = new Parser();
        this.inventory = new Inventory();
    }

    // A method for assigning all the rooms and setting their exits. (This is where new rooms are to be added.)

    private void createRooms() {
        
        this.outside = new Room("outside");    
        this.kitchen = new Room("now in the kitchen where you can use all the ingredients you've collected to make prepare food for the people waiting");
        this.restaurant = new Room("You are now in the restaurant where you can give the food you've made for the starving people");
        this.well = new Room("You are now at the water well where you can collect fresh water");
        this.fields = new Room("Here are your fields where you can grow crops or corn");
        this.cornfield = new Room("You are now at your cornfield where you can harvest and grow more corn");
        this.cropfield = new Room("You are now at your cropfield where you can harvest and grow crops like tomatos, salad and cucumber");
        this.bakery = new Room("You are now in the bakery where you can bake bread and small cakes with the ingredients you have collected");
        //this.storefront= new Room("Here you got the line of people youre trying to help");
        
                //instatiate rooms
        String[] animals = {"cow", "chicken"};
        this.barn = new Room("in the barn where you can fed your animals and collect their milk and eggs", animals);

        this.barn.setExit("north", this.kitchen);
        this.barn.setExit("west", this.cornfield);
        this.barn.setExit("east", this.well); 
        
        this.kitchen.setExit("south", this.barn);
        this.kitchen.setExit("north", this.restaurant);
        this.kitchen.setExit("west", this.fields);
        this.kitchen.setExit("east", this.bakery);
        
        this.fields.setExit("east", this.kitchen);
        this.fields.setExit("north", this.cropfield);
        this.fields.setExit("south", this.cornfield);
        
        this.cornfield.setExit("north", this.fields);
        this.cornfield.setExit("east", this.bakery);
        
        this.cropfield.setExit("south", this.fields);
        this.cropfield.setExit("east", this.restaurant);
        
        this.restaurant.setExit("south", this.kitchen);
        this.restaurant.setExit("west", this.cropfield);
        this.restaurant.setExit("east", this.outside);
        
        this.bakery.setExit("west", this.kitchen);
        this.bakery.setExit("south", this.well);
        this.bakery.setExit("north", this.outside);
        
        this.well.setExit("north", this.bakery);
        this.well.setExit("west", this.barn);
        
        
        this.currentRoom = this.kitchen;
        
        
        //instatiate rooms
        /*String[] animals = {"cow", "chicken"};
        this.barn = new Room("in the barn", animals);

        this.kitchen = new Room("in the kitchen");

        this.barn.setExit("south", this.kitchen);

        this.kitchen.setExit("north", this.barn);

        this.currentRoom = this.kitchen;

        /*
        Room outside, theatre, pub, lab, office;

      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        field = new Room("in the field");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);


        currentRoom = field;

        currentRoom = outside;*/

    }

    // The way the game is started is by running this method, which then starts the game loop.
    public void play() {
        printWelcome();

        boolean finished = false;
        // The game loop works like this:
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    // Method for showing the welcome message, this can be redefined in this method.
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    // This method handles the userinput and redirects the command to the correct method.
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        String secondWord = command.getSecondWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        /*
        This is where the game handles the commands. add if statements to check what command has been inputtet
        Then check what room you are in, or if there are more rooms that allow that command, check if you are in any of them
        unless it is a global command, then just skip the room check
        If your command requires interaction with an Interactor, us the getRoomsInteractor() method to retrieve that object
        Then call the method from the object, that you need       
        */
        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);

        }
        else if (commandWord == CommandWord.SOW) {
            Room field = new Room("in the field");
            if (currentRoom.getShortDescription() == field.getShortDescription()) {
                Field.sowField(secondWord);
            }
        }
        else if (commandWord == CommandWord.HARVEST) {
            Room field = new Room("in the field");
            if (currentRoom.getShortDescription() == field.getShortDescription()) {
                if (Field.isReadyToHarvest()) {
                    Field.harvest();
                }
            }
        }
        else if (commandWord == CommandWord.WATER) {
            Room field = new Room("in the field");
            if (currentRoom.getShortDescription() == field.getShortDescription()) {
                Field.waterCrops();
            }
        }
        else if (commandWord == CommandWord.TEST) {
            Customer c = new Customer();
        } else if (commandWord == CommandWord.COLLECT) {
            if ("milk".equals(command.getSecondWord())) {
                if (correctRoom(this.barn)) {
                    Animal cow = (Animal) getRoomsInteractor(this.currentRoom, "Cow");
                    cow.collectProduct(this.inventory);
                }
            }
            else if("eggs".equals(command.getSecondWord())){
                if(correctRoom(this.barn)){
                    Animal chicken = (Animal)getRoomsInteractor(this.currentRoom, "Chicken");
                    chicken.collectProduct(this.inventory);
                }
            }
        }
        else if(commandWord == CommandWord.FEED){
            if("cow".equals(command.getSecondWord())){
                if(correctRoom(this.barn)){
                    Animal cow = (Animal) getRoomsInteractor(this.currentRoom, "Cow");
                    cow.feed(this.inventory);
                }
            }
            if("chicken".equals(command.getSecondWord())){
                if(correctRoom(this.barn)){
                    Animal chicken = (Animal) getRoomsInteractor(this.currentRoom, "Chicken");
                    chicken.feed(this.inventory);
                }
            }
        }
        return wantToQuit;
    }

    // A method to print the help commands response.
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    // A method to change the current room, it does this from a given command.
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    // The method to quit the game.
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    public static int getTick() {
        return gameTick;
    }

    //method for getting a specific Interactor from a room, takes the Interactors name as second parameter
    private Interactor getRoomsInteractor(Room room, String interactor) {
        ArrayList<Interactor> interactors = room.getInteractors();
        Interactor res = null;
        for (Interactor in : interactors) {
            if (interactor == in.toString()) {
                res = in;
            }
        }
        return res;

    }
    //checks if the player is in a specific room
    private boolean correctRoom(Room room){
        if(this.currentRoom == room){
            return true;
        }
        else{
            System.out.println("This command can't be used in this room");
            return false;
        }
    }
}
