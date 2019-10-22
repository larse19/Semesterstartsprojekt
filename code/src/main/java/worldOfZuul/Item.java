package worldOfZuul;

public class Item {
    private String name;
    private int saturation;
    
    public Item(String name, int sat){
        this.name = name;
        saturation = sat;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String itemName){
        name = itemName;
    }
    
    public int getSaturation(){
        return saturation;
    }
    
    public void setSaturation(int itemSaturation){
        saturation = itemSaturation;
    }
}
