package worldOfZuul;


public class CustomerController {

    private Customer currentCustomer;
    private Scoreboard sb;
    
    
    public void CustomerController(){
        currentCustomer = new Customer();
    }
    
    public void feedCustomer(Food food){
        
        int sat = food.getSaturation();
        
        currentCustomer.addHP(sat);
    }
    
    public void newCustomer(){
        sb.addPoints(currentCustomer.getGainedScore());
        currentCustomer = new Customer();
    }
    
}
