package worldOfZuul;

public class Animal {

    private String name;
    private Item product;
    private boolean fed;
    private String animalFood;
    private int foodAmount, yieldCount;

    public Animal(String name, Item product) {
        this.name = name;
        this.product = product;
        this.animalFood = "Seeds";
        fed = false;
        this.foodAmount = 1;
        this.yieldCount = 1;
    }

    public Animal(String name, Item product, String animalFood, int foodAmount, int yieldCount) {
        this.name = name;
        this.product = product;
        this.animalFood = animalFood;
        this.fed = false;
        this.foodAmount = foodAmount;
        this.yieldCount = yieldCount;
    }

    //Feeds the animal
    public void feed(Inventory inventory) {
        if (!fed) {
            if (inventory.removeItem(this.animalFood, this.foodAmount)) {

                this.fed = true;
                System.out.println("You have fed the " + this.name + ".");
            } else {
                System.out.println("You don't have any " + this.animalFood + " to feed the " + this.name + ".");
            }
        } else {
            System.out.println(this.name + " is already fed.");
        }
    }

    public boolean getFed() {
        return this.fed;
    }

    public void setFed(boolean fed) {
        this.fed = fed;
    }

    //Collects product from animal, and adds it to inventory, if the animal is fed
    public void collectProduct(Inventory inventory) {
        if (this.fed) {
            //Missing ingredient class to finish; following is example
            Ingredient product = new Ingredient(this.product);
            if (inventory.putItem(product, this.yieldCount)) {
                this.fed = false;
                System.out.println("You have collected " + this.yieldCount + this.product + ".");
            }
            else{
                System.out.println("You don't have enough room in your inventory.");
            }
        } else {
            System.out.println(this.name + " needs to be fed.");
        }
    }

}
