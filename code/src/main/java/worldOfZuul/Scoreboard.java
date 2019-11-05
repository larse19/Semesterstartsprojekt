package worldOfZuul;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Scoreboard {

    private int score;
    private final File myFile;
    
    public Scoreboard(){
        myFile = new File("Scoreboard.csv");
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void addPoints(int points){
        this.score += points;
    }
    
    public boolean saveHighscore(){
        
        PrintWriter pw;
        ArrayList<Integer> highscores = getHighscores();
        
        try{
            pw = new PrintWriter(this.myFile);
            pw.print("");
            highscores.add(this.score);
            Collections.sort(highscores);
            Collections.reverse(highscores);
            for(Integer score : highscores){
                pw.println(score);
            }
            pw.close();
            return true;
        }
        catch(FileNotFoundException ex){
            System.out.println("Highscore file not found.");
            return false;
        }
        
    }
    
    public ArrayList<Integer> getHighscores(){
        
        ArrayList<Integer> highscores = new ArrayList<>();
        
        Scanner s;
        try{
            s = new Scanner(this.myFile);
            while(s.hasNext()){
                highscores.add(s.nextInt());
            }
            s.close();
            return highscores;
        }
        catch(FileNotFoundException ex){
            return highscores;
        }
        
    }
    
}
