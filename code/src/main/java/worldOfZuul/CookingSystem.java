
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
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> friedEgg = new HashMap(Map.of("egg", 1));
        recipe.put("fried egg", friedEgg);
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> boiledEgg = new HashMap(Map.of("egg", 1, "water", 1));
        recipe.put("boiled egg", boiledEgg);
       
        this.recipe = new HashMap<>();
        HashMap<String, Integer> salad = new HashMap(Map.of("tomato", 1, "cucumber", 1, "salad", 1));
        recipe.put("salad", salad);
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> scallopedPotatos = new HashMap(Map.of("potato", 1, "milk", 1));
        recipe.put("scalloped potatos", scallopedPotatos);
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> boiledPotato = new HashMap(Map.of("potato", 1));
        recipe.put("boiled potato", boiledPotato);
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> butter = new HashMap(Map.of("milk", 1));
        recipe.put("butter", butter);
        
        this.recipe = new HashMap<>();
        HashMap<String, Integer> cake = new HashMap(Map.of("milk", 1, "flour", 1, "egg", 1, "butter", 1));
        recipe.put("cake", cake);
        
        
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