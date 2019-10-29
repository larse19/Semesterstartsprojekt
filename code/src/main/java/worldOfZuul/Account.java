package worldOfZuul;


public class Account {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    //add to balance
    public void add(int balance){
        this.balance += balance;
    }
    //substract from balance
    public void substract(int balance){
        this.balance -= balance;
    }
    
}
