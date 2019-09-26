package worldOfZuul;

import java.util.Scanner;
//import java.util.StringTokenizer;

/**
 * The complex type Parser is used for reading user input, as well as handling a few of the commands the user inputs.
 */
public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    /**
     * Constructor for the class, where there are no initial arguments.
     * It starts by setting up a scanner as well as an instance of the class CommandWords.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    // Takes the input of the user and converts it to a Command.
    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }
        tokenizer.close();
        return new Command(commands.getCommandWord(word1), word2);
    }

    // Shows all the availiable commands.
    public void showCommands()
    {
        commands.showAll();
    }
}
