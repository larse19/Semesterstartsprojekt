package worldOfZuul;

public class Mill extends Room {
    public Mill(String description){
        super(description);
    }
    
    public void grindFlour(Inventory inventory) {
        if(inventory.removeItem("corn", 1)){
            inventory.putItem("flour", 1);
        }        
        
    }
    
}
