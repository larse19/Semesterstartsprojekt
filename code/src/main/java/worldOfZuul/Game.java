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
    private Room barn, kitchen, storefront, cropfield, cornfield, well;
    private Inventory inventory;

    // Constructor for the class game, creates all the rooms and sets up the parser.
    public Game() {
        createRooms();
        parser = new Parser();
        this.inventory = new Inventory();
    }

    // A method for assigning all the rooms and setting their exits. (This is where new rooms are to be added.)

    private void createRooms() {
        String[] animals = {"cow", "chicken"};
        this.barn = new Room("in the barn where you can fed your animals and collect their milk and eggs", animals);
        
        this.kitchen = new Room("now in the kitchen where you can use all the ingredients you've collected to make prepare food for the people waiting");
        
        this.storefront = new Room("now at the storefront where you can help the starving people");
        
        this.cropfield = new Field("now at your cropfield where you can harvest and grow crops", "potato");
        
        this.cornfield = new Field("now at your cornfield where you can harvest and grow more corn", "corn");
        
        this.well = new Room("now at the water well where you can collect fresh water");        
        
        
        
        this.storefront.setExit("north", kitchen);
        
        this.kitchen.setExit("north", cornfield);
        this.kitchen.setExit("west", cropfield);
        this.kitchen.setExit("east", barn);
        this.kitchen.setExit("south", storefront);
        
        this.well.setExit("east", cornfield);
        this.well.setExit("south", cropfield);
        
        this.barn.setExit("west", kitchen);
        
        this.cornfield.setExit("west", well);
        this.cornfield.setExit("south", kitchen);
        
        this.cropfield.setExit("north", well);
        this.cropfield.setExit("east", kitchen);
        
        
        this.currentRoom = this.storefront;
        
       
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
