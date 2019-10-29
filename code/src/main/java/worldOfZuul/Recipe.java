package worldOfZuul;

import java.util.ArrayList;

public class Recipe {
    private Item[] ingredients;
    private Food product;
    private final static ArrayList<String> recipeList = new ArrayList();
    
    //constructor for ingredients
    public Recipe(Item[] ingredients, Food product){
        this.ingredients = ingredients;
        this.product = product;
        recipeList.add(product.getName());
    }
    
    //getter for ingredient list for food product
    public Item[] getIngredients(){
        return ingredients;
    }
    
    //setter for ingredient list for food product
    public void setIngredients(Item[] foodIngredients){
        ingredients = foodIngredients;
    }
    
    //getter for food product
    public Food getProduct(){
        return product;
    }
    
    //setter for food product
    public void setProduct(Food foodProduct){
        product = foodProduct;
    }
    
    /*
    getter for recipelist in static context so it can be referenced elsewhere
    this method is used to display in text the avaliable recipes
    */
    public static ArrayList<String> getRecipeList(){
        return recipeList;
    }
}