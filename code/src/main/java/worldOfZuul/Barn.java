package worldOfZuul;


public class Barn extends Room{

    private Animal cow;
    private Animal chicken;
    
    public Barn(String description){
        super(description);
        cow = new Animal("cow", "milk", "corn", 1, 1);
        chicken = new Animal("chicken", "egg", "corn", 1, 3);
    }
    
    public Animal getAnimal(String animal){
        if("cow".equals(animal)){
            return cow;
        }
        else if("chicken".equals(animal)){
            return chicken;
        }
        else{
            return null;
        }
    }

}