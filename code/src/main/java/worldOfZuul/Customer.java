package worldOfZuul;

import java.util.Random;

public class Customer {

    private int currentHp, startTick, startHp, tickCounter, totalDmg;
    private final int maxHp = 10;
    private boolean alive;

    public Customer() {
        alive = true;
        Random rand = new Random();
        currentHp = rand.nextInt(4) + 3;
        startTick = Game.getTick();
        startHp = currentHp;
        System.out.println(currentHp);
        tickCounter = 0;
        totalDmg = 0;
    }

    public void setCurrentHp(int hp) {
        currentHp = hp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int maxHp() {
        return maxHp;
    }

    public int getTick() {
        return startTick;
    }

    public void damage() {
        if (this.tickCounter == 10) {
            this.currentHp -= 1;
            totalDmg++;
            if (currentHp == 0) {
                alive = false;
            }

            this.tickCounter = 0;
        } else {
            tickCounter++;
        }
    }
    
    public int getDamage(){
        return this.totalDmg;
    }

    public boolean isAlive() {
        if (alive) {
            return true;
        } else {
            return false;
        }
    }

    public void addHP(int hp) {
        if (this.currentHp + hp <= 10) {
            this.currentHp += hp;
        } else if (this.currentHp + hp > 10) {
            this.currentHp = 10;
        }
    }

    public int getGainedScore() {
        return this.currentHp - this.startHp;
    }
}
