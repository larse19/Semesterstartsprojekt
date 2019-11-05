package worldOfZuul;

public class Mill extends Room {
    public Mill(String description){
        super(description);
    }
    
    public void grindFlour(Inventory inventory) {
        inventory.removeItem("Corn", 1);
        inventory.putItem("Flour", 1);
        
    }
    
}
