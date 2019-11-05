package worldOfZuul;

//This Class creates a Food object, 

import java.util.HashMap;
import java.util.Map;


public class CookingSystem {
    
    private HashMap<String, HashMap<String, Integer>> recipe;

    public CookingSystem() {
        this.recipe = new HashMap<>();
        
        HashMap<String, Integer> bread = new HashMap(Map.of("flour", 1, "water", 1, "egg", 2));
        recipe.put("bread", bread);
        
        //flere opskrifter.....
        
        
        
    }
    
    public void cook(String foodProduct, Inventory inv){
        
        HashMap<String, Integer> recipe = this.recipe.get(foodProduct);
        
        if(check(recipe, inv.getInventory())){
            for(String item : recipe.keySet()){
                inv.removeItem(item, recipe.get(item));
            }
            inv.putItem(foodProduct, 1);
        }
        else{
            System.out.println("You don't have the ingredients needed.");
        }
        
    }
   
    
  

 
    
    
        //Checks if the recipeList matches the itemList.
    
    private static boolean check(HashMap<String, Integer> recipeList, HashMap<String, Integer> itemList) {
        boolean hasAllItems = true;

        for (String key : recipeList.keySet()) {
            if (!itemList.containsKey(key)) {
                hasAllItems = false;
            } else if (itemList.get(key) < recipeList.get(key)) {
                hasAllItems = false;
            }
        }
        return hasAllItems;
        
    
    
    
    
    }
    
    
    
}



