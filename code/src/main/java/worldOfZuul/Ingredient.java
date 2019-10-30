package worldOfZuul;


public class Ingredient extends Item {
    //some ingredients are edible, this checks if it's true
    private boolean edible;
    
    //constructor for non-edible ingredients
    public Ingredient(String name){
        super(name);
    }
    
    //constructor for edible ingredients
    public Ingredient (String name, int saturation, boolean edible){
        super(name, saturation);
        this.edible = edible;
    }
    
    //getter for edible
    public boolean getEdible(){
        return this.edible;
    }
    
    //setter for edible
    public void setEdible(boolean edible){
        this.edible = edible;
    }
    
}
