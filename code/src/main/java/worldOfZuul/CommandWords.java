package worldOfZuul;
import java.util.HashMap;

/**
 * A class representing all valid commandWords.
 * Contains a few methods to check the validity of a commandword, a way to show all commands, and return the command as a command.
 */
public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    // Constructor fills in the validCommands with the commands for commandWord.
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    // Returns the given strings commandWord as a command.
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    // Method that checks the validity of the given string command.
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    // Method for showing all valid commands.
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
