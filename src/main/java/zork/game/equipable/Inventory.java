package zork.game.equipable;

import zork.game.equipable.observer.InventoryObservable;
import zork.game.equipable.observer.InventoryObserver;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements InventoryObservable, InventoryObserver {

    private static Map<String, Item> itemList = new HashMap<>();
    private WeaponImpl currentWeapon = null; // current weapon

    public Map<String,Item> getItemList() {
        return itemList;
    }

    public String getDescriptionInventory(){
        String rtrn = "";
        if(currentWeapon == null){
            rtrn = "Items: " + String.join(", ", this.itemList.keySet());
        }else{
            rtrn = "Items: " + String.join(", ", this.itemList.keySet()) + "\n" + "Equipped: " + currentWeapon.getName() + " " + currentWeapon.getAttackRange();
        }

        return rtrn;
    }

    @Override
    public void addItem(Item item) {

        if(isInventoryFull()){
            System.out.println("Inventory is full");
        }else{
            itemList.put(item.getName(),item);
            notifyInventoryChange();
        }

    }

    @Override
    public boolean isInventoryFull(){

        if(itemList.size() < 3){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void removeItem(Item item) {

        if(item.equals(currentWeapon)){
            currentWeapon = null;
        }

        itemList.remove(item.getName(),item);
        notifyInventoryChange();
    }

    @Override
    public void notifyInventoryChange() {
        System.out.println("Items in inventory"); //vytiskne aktualni obsah inventare

        System.out.println("Inventory: ");
        for (Map.Entry<String,Item> entry : itemList.entrySet()){
            //if(entry.getValue() instanceof Weapon){
            System.out.println(entry.getKey());
           // }
        }

        //pro dalsi druhy items
    }


    @Override
    public Weapon getWeaponFromInventory() {
        Weapon ww = null;

        for (Map.Entry<String,Item> entry : itemList.entrySet()){
            if(entry.getValue() instanceof Weapon){
                ww = (Weapon) entry.getValue();
            }
        }
        return ww;
    }


    @Override
    public Weapon getEquipedWeapon(){
        return currentWeapon;
    }

    @Override
    public String unequipItem(Item item) { // need fixing if more items are gonna be removed from their slots

        currentWeapon = null;

        if(itemList.containsValue(item) && item instanceof Weapon){

            currentWeapon = null;
            return "weapon unequipped";

        }else {

            return "this item cannot be equipped";
        }

    }


    @Override
    public String equipItem(Item item){

        if(itemList.containsValue(item) && item instanceof Weapon){

            if(currentWeapon == null){
                currentWeapon = (WeaponImpl) item;
                return "weapon equipped \n" + getDescriptionInventory();

            }else{
                System.out.println(currentWeapon.getName());
                return "another weapon is already equipped, you have to firstly throw away your current one";
            }

        }

        return "this item cannot be equipped";

    }

    @Override
    public Item getItemByName(String itemName) {

            return itemList.getOrDefault(itemName,null);

    }

    public void removeAllItems(){
        currentWeapon = null;
        itemList.clear();
    }

    @Override
    public Key getKeyFromInventory() {
        Key ww = null;

        for (Map.Entry<String,Item> entry : itemList.entrySet()){
            if(entry.getValue() instanceof Key){
                ww = (Key) entry.getValue();
            }
        }
        return ww;
    }

    public boolean containsClass(Class<?> clazz) {
        for (Object value : itemList.values()) {
            if (clazz.isInstance(value)) {
                return true;
            }
        }
        return false;
    }

}
