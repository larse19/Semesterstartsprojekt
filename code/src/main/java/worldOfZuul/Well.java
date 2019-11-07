package worldOfZuul;

public class Well extends Room {
    public Well(String description) {
        super(description);
    }
    
    public void collectWater(Inventory inventory) {
        inventory.putItem("water", 1);
    }
            
            
    
}
