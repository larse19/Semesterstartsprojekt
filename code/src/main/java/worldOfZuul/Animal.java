package worldOfZuul;

public class Animal {
    private String name;
    private Item product;
    private boolean fed;
    private String animalFood;
    private int foodAmount;
    private int yieldCount;
    
    public Animal(String name, Item product){
        this.name = name;
        this.product = product;
        this.animalFood = "Seeds";
        fed = false;
        this.foodAmount = 1;
        this.yieldCount = 1;
    }
    
    public Animal(String name, Item product, String animalFood, int foodAmount, int yieldCount){
        this.name = name;
        this.product = product;
        this.animalFood = animalFood;
        this.fed = false;
        this.foodAmount = foodAmount;
        this.yieldCount = yieldCount;
    }
    
    //Feeds the animal
    public void feed(Inventory inventory){
        this.fed = true;
        inventory.removeItem(this.animalFood, this.foodAmount);
        System.out.println("You have fed the " + this.name);
    }        
    
    public boolean getFed(){
        return this.fed;
    }
    
    public void setFed(boolean fed){
        this.fed = fed;
    }
    
    //Collects product from animal, and adds it to inventory, if the animal is fed
    public void collectProduct(Inventory inventory){
        if(this.fed){
            this.fed = false;
            //Missing ingredient class to finish; following is example
            Ingredient product = new Ingredient(this.product);
            inventory.putItem(product, this.yieldCount);
            System.out.println("You have collected " + this.yieldCount + this.product);
        }
        else{
            System.out.println(this.name + " needs to be fed");
        }
    }
    
}
