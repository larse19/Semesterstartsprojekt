package worldOfZuul;

import java.util.HashMap;

public class Inventory {
    private HashMap<String,Integer> itemList = new HashMap<String,Integer>();
    
    public void putItem(String itemName, int itemCount){
        boolean exist = false;
        for(String i : itemList.keySet()){
            if(i == itemName){
                itemList.put(i, itemList.get(i)+itemCount);
                exist = true;
            }
        }
        if (!exist){
            itemList.put(itemName, itemCount);
        }
    }
    
    public boolean removeItem(String itemName, int itemCount){
        if (itemCount < itemList.get(itemName)){
            itemList.put(itemName, itemList.get(itemName)-itemCount);
            return true;
        } else if(itemCount == itemList.get(itemName)) {
            itemList.remove(itemName);
            return true;
        } else {
            return false;
        }
    }
    
    public void getItem(String itemName){
        itemList.get(itemName);
    }
    
}
