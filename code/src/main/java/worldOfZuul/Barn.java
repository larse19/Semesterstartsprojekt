package worldOfZuul;


public class Barn extends Room{

    private Animal cow;
    private Animal chicken;
    
    public Barn(String description){
        super(description);
        cow = new Animal("Cow", "Milk");
        chicken = new Animal("Chicken", "Egg");
    }

}