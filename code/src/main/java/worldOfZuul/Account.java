package worldOfZuul;


public class Account {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void add(int balance){
        this.balance += balance;
    }
    
    public void substract(int balance){
        this.balance -= balance;
    }
    
}
