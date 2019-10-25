package worldOfZuul;


public class Ingredient extends Item {
    private boolean edible;
    
    public Ingredient(String name){
        super(name);
    }
    
    public Ingredient (String name, boolean edible){
        super(name);
        this.edible = edible;
    }
    
    public boolean getEdible(){
        return this.edible;
    }
    
    public void setEdible(boolean edible){
        this.edible = edible;
    }
    
}
