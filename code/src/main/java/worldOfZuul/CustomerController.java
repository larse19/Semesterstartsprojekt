package worldOfZuul;


public class CustomerController extends Room{

    private Customer currentCustomer;
    private Scoreboard sb;
    
    
    public CustomerController(String description){
        super(description);
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
