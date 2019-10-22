package worldOfZuul;

public class Animal {
    private String name;
    private Item product;
    private boolean fed;
    
    public Animal(String name, Item product){
        this.name = name;
        this.product = product;
        fed = false;
    }
    
    public Animal(String name, Item product, boolean fed){
        this.name = name;
        this.product = product;
        this.fed = fed;
    }
    
    public void feed(){
        this.fed = true;
    }        
    
    public boolean getFed(){
        return this.fed;
    }
    
    public void setFed(boolean fed){
        this.fed = fed;
    }
    
    
    
}
