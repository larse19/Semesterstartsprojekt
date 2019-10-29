package worldOfZuul;

public class Food extends Item{
    private Recipe recipe;
    
    //food constructor
    public Food(String name, int saturation, Recipe recipe){
        super(name, saturation);
        this.recipe = recipe;
    }
    
    //getter for recipe
    public Recipe getRecipe(){
        return recipe;
    }
    
    /*
    setter for recipe that food uses. This can be used to check 
    if food object contains recipe object ect.
    */
    public void setRecipe(Recipe foodRecipe){
        recipe = foodRecipe;
    }
    
}
