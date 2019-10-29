package worldOfZuul;

public class Item {
    protected String name;
    protected int saturation;
    
    //constructor for item with 0 saturation
    public Item(String name){
        this.name = name;
        this.saturation = 0;
    }
    
    //constructor for item with set saturation
    public Item(String name, int sat){
        this.name = name;
        saturation = sat;
    }
    
    //getter for item name
    public String getName(){
        return name;
    }
    
    //setter for item name
    public void setName(String itemName){
        name = itemName;
    }
    
    //getter for item saturation
    public int getSaturation(){
        return saturation;
    }
    
    //setter for saturation
    public void setSaturation(int itemSaturation){
        saturation = itemSaturation;
    }
}
