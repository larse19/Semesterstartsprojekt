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
    private Room kitchen;
    private CustomerController storefront;
    private Field cropfield, cornfield;
    private Mill mill;
    private Well well;
    private Barn barn;
    private Inventory inventory;
    private static Scoreboard sb;
    private final ArrayList<Ingredient> possiblIngredients = new ArrayList<Ingredient>();
    // Temporarily disabled until further functionality
    private final ArrayList<Food> possibleFoods = new ArrayList<Food>();
    // private final ArrayList<Recipe> possiblRecipes = new ArrayList<Recipe>();

    // Constructor for the class game, creates all the rooms and sets up the parser.
    public Game() {
        createRooms();
        parser = new Parser();
        this.inventory = new Inventory();
        this.sb = new Scoreboard();
        // All possible ingredients, food items, and recipe's
        String[] edible = {"carrot", "milk", "salat", "cucumber"};
        String[] nonEdible = { "Corn", "flour", "eggs", "potato" };
        for (String temp : nonEdible) {
            this.possiblIngredients.add(new Ingredient(temp));
        }
        for (String temp : edible) {
            this.possiblIngredients.add(new Ingredient(temp, 1, true));
        }

        this.possibleFoods.add(new Food("bread", 5));
        this.possibleFoods.add(new Food("fried egg", 2));
        // Temporarily disabled until further functionality
        // this.possibleFoods.add(new Food("Bread", 2));
        // Temp recipe list
        // Item[] temp = {new Ingredient("Corn"), new Ingredient("Milk")};
        // this.possiblRecipes.add(new Recipe(temp , this.possibleFoods.get(0)));
    }

    // A method for assigning all the rooms and setting their exits. (This is where
    // new rooms are to be added.)

    private void createRooms() {
        this.barn = new Barn("in the barn where you can fed your animals and collect their milk and eggs");

        this.kitchen = new Room(
                "now in the kitchen where you can use all the ingredients you've collected to make prepare food for the people waiting");

        this.storefront = new CustomerController("now at the storefront where you can help the starving people");

        this.cropfield = new Field("now at your cropfield where you can harvest and grow crops", "Potato");

        this.cornfield = new Field("now at your cornfield where you can harvest and grow more corn", "Corn");
        
        this.well = new Well("now at the water well where you can collect fresh water");     
        
        this.mill = new Mill("now at the mill where you can grind your corn to get flour");
        
        
        

        this.storefront.setExit("north", kitchen);

        this.kitchen.setExit("north", cornfield);
        this.kitchen.setExit("west", cropfield);
        this.kitchen.setExit("east", barn);
        this.kitchen.setExit("south", storefront);

        this.well.setExit("east", cornfield);
        this.well.setExit("south", cropfield);

        this.barn.setExit("west", kitchen);

        this.barn.setExit("north", mill);

        this.cornfield.setExit("west", well);
        this.cornfield.setExit("south", kitchen);

        this.cropfield.setExit("north", well);
        this.cropfield.setExit("east", kitchen);

        this.mill.setExit("south", barn);
        this.mill.setExit("west", cornfield);

        this.currentRoom = this.storefront;

    }

    // The way the game is started is by running this method, which then starts the
    // game loop.
    public void play() {
        printWelcome();
        System.out.println(sb.getScore());

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
        System.out.println("Welcome to the Hunger Games!");
        System.out.println("Hunger games is a game about resource management and feeding the poor to end world hunger");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    // This method handles the userinput and redirects the command to the correct
    // method.
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        /**
         * This is where the game handles the commands. add if statements to check what
         * command has been inputtet Then check what room you are in, or if there are
         * more rooms that allow that command, check if you are in any of them unless it
         * is a global command, then just skip the room check If your command requires
         * interaction with an Interactor, us the getRoomsInteractor() method to
         * retrieve that object Then call the method from the object, that you need
         */
        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);

        } 
        //Sow crops
        else if (commandWord == CommandWord.SOW) {
            if (correctRoom(this.cropfield)) {
                this.cropfield.sowField(command.getSecondWord());
            } else if (correctRoom(this.cornfield)) {
                this.cornfield.sowField(command.getSecondWord());
            }
        } 
        //Harvest crops
        else if (commandWord == CommandWord.HARVEST) {
            if (correctRoom(this.cropfield)) {
                if (this.cropfield.isReadyToHarvest()) {
                    this.inventory.putItem(this.cropfield.getCrop(), 1);
                }

            } else if (correctRoom(this.cornfield)) {
                if (this.cornfield.isReadyToHarvest()) {
                    this.inventory.putItem(this.cornfield.getCrop(), 1);
                }
            }
        } 
        //Water crops
        else if (commandWord == CommandWord.WATER) {
            if (correctRoom(this.cropfield)) {
                this.cropfield.waterCrops();
            } else if (correctRoom(this.cornfield)) {
                this.cornfield.waterCrops();
            }
        } 
        
        else if (commandWord == CommandWord.TEST) {
            System.out.println("This is a test command");
            
        }
        //Collect products
        else if (commandWord == CommandWord.COLLECT) {
            if ("milk".equals(command.getSecondWord())) {
                if (correctRoom(this.barn)) {
                    Animal cow = this.barn.getAnimal("cow");
                    cow.collectProduct(this.inventory);
                }
            } else if ("eggs".equals(command.getSecondWord())) {
                if (correctRoom(this.barn)) {
                    Animal chicken = this.barn.getAnimal("chicken");
                    chicken.collectProduct(this.inventory);
                }
            }

            else if("water".equals(command.getSecondWord())){
                if(correctRoom(this.well)) {
                    this.well.collectWater(this.inventory);
                }
            }
        }
        //Feed cow or chicken
        else if(commandWord == CommandWord.FEED){
            inventory.putItem("corn", 1);
            if("cow".equals(command.getSecondWord())){
                if(correctRoom(this.barn)){

                    Animal cow = this.barn.getAnimal("cow");
                    cow.feed(this.inventory);
                }
            }
            if ("chicken".equals(command.getSecondWord())) {
                if (correctRoom(this.barn)) {
                    Animal chicken = this.barn.getAnimal("chicken");
                    chicken.feed(this.inventory);
                }
            }
        }
        //Grind corn to flour
        else if(commandWord == CommandWord.GRIND) {
            if(correctRoom(this.mill)){
                this.mill.grindFlour(this.inventory);
            }
        }
        //Give food or edible ingredient to customer
        else if(commandWord == CommandWord.GIVE){
            
            if(correctRoom(this.storefront)){
                for(Food food : this.possibleFoods){
                    if(command.getSecondWord().equals(food.getName())){
                        storefront.feedCustomer(food, this.inventory);
                    }
                }
                for(Ingredient ingredient : this.possiblIngredients){
                    if(command.getSecondWord().equals(ingredient.getName())){
                        storefront.feedCustomer(ingredient, this.inventory);
                    }
                }
            }
        }
        //Get customer HP
        else if(commandWord == CommandWord.CUSTOMER){
            if("hp".equals(command.getSecondWord())){
                System.out.println("The customer has: " + storefront.getHp() + " health.");
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

    private void tick() {
        this.cornfield.grow();
        this.cropfield.grow();
        this.storefront.updateCustomer();

        gameTick++;
    }
    
    public static Scoreboard getScoreboard(){
        return Game.sb;
    }
}
