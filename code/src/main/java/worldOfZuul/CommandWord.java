package worldOfZuul;


/**
 * Complex type CommandWord.
 * This class is an enum of the words the user can input in the game.
 */
public enum CommandWord
{
    // List of the words that get registered as commandWords.



    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), SOW("sow"), HARVEST("harvest"), WATER("water"), TEST("test"), COLLECT("collect"), FEED("feed"), GRIND("grind"), GIVE("give"), CUSTOMER("customer"), SCORE("score"), COOK("cook"), INVENTORY("inventory"), RECIPE("recipe"), BALANCE("balance");



    
    private String commandString;
    
    // Constructor initializing the class from a string.
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    // Returns the commandWord as a string.
    public String toString()
    {
        return commandString;
    }
}
