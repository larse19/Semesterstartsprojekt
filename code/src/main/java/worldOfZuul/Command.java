/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package worldOfZuul;

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    // Constructor for the complex type Command, consisting of a CommandWord and a string secondWord.
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // A way to get the commandword from the class Command.
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    // A way to get the string secondWord from the class Command.
    public String getSecondWord()
    {
        return secondWord;
    }

    // If the commandWord is unkown, this funtion returns true.
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    // A check for wether or not the Command has a secondWord. (Will return false if it does not )
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

