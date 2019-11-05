package worldOfZuul;
import java.util.Random;

public class Customer {
    private int currentHp, startTick, startHp;
    private final int maxHp = 10;
    private boolean alive;
    
    public Customer(){
        alive = true;
        Random rand = new Random();
        currentHp = rand.nextInt(4) + 3;
        startTick = Game.getTick();
        startHp = currentHp;
        System.out.println(currentHp);
    }
    
    public void setCurrentHp(int hp){
        currentHp = hp;
    }
    
    public int getCurrentHp(){
        return currentHp;
    }
    
    public int maxHp(){
        return maxHp;
    }
    
    public int getTick(){
        return startTick;
    }
    
    public void damage(){
        int dmg = Game.getTick() - startTick;
        currentHp -= dmg;
        if (currentHp <= 0){
            alive = false;
            currentHp = 0;
        }
    }

    public boolean isAlive() {
        if (alive) {
            return true;
        } else {
            return false;
        }        
    }
    
    public void addHP(int hp){
        if(this.currentHp + hp <=10){
            this.currentHp += hp;
        }
        else if(this.currentHp + hp > 10){
            this.currentHp = 10;
        }
    }
    
    public int getGainedScore(){
        return this.currentHp - this.startHp;
    }
}
