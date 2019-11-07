package worldOfZuul;

import java.util.HashMap;

public class Inventory {

    private final int size = 10;
    private HashMap<String, Integer> itemList = new HashMap<String, Integer>();
    
    
    public boolean putItem(String itemName, int itemCount) {
        boolean exist = false;
        //Checks if there is room in inventory. 
        //The number of different items, limits the size, there currently isn't a stack limit for each item
        if (numberOfItems() + itemCount < this.size) {
            for (String i : itemList.keySet()) {
                if (i == itemName) {
                    itemList.put(i, itemList.get(i) + itemCount);
                    exist = true;
                }
            }
            if (!exist) {
                itemList.put(itemName, itemCount);
            }
            System.out.println(itemCount + " " + itemName + " added to inventory.");
            return true;
        }
        else{
            System.out.println("Inventory is full.");
            return false;
        }
    }
    
    //return number of items in inventory
    private int numberOfItems() {
        int result = 0;
        for (String i : itemList.keySet()) {
            result++;
        }
        return result;
    }
    
    /*
    sets down itemcount of items when used/removed
    and removes the item from itemslot in inventory if
    itemcount of an item goes below zero
    */
    public boolean removeItem(String itemName, int itemCount) {
        if (itemCount < itemList.get(itemName)) {
            itemList.put(itemName, itemList.get(itemName) - itemCount);
            return true;
        } else if (itemCount == itemList.get(itemName)) {
            itemList.remove(itemName);
            return true;
        } else {
            return false;
        }
    }
    
    //getter for item count of an item
    public void getItem(String itemName) {
        itemList.get(itemName);
    }
    
    public HashMap<String, Integer> getInventory(){
        return this.itemList;
    }
    
    @Override
    public String toString(){
        String res = "";
        for(String key : this.itemList.keySet()){
            System.out.println(itemList.get(key) + " " + key);
        }
        return res;
    }
    
}
