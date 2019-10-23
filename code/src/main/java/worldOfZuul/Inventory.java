package worldOfZuul;

import java.util.HashMap;

public class Inventory {

    private static int size = 5;
    private HashMap<String, Integer> itemList = new HashMap<String, Integer>();

    public boolean putItem(String itemName, int itemCount) {
        boolean exist = false;
        //Checks if there is room in inventory. 
        //Currently number of individuel items limit the space, not the number of different items
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
            System.out.println(itemCount + itemName + " added to inventory.");
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
            result += itemList.get(i);
        }
        return result;
    }

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

    public void getItem(String itemName) {
        itemList.get(itemName);
    }

}
