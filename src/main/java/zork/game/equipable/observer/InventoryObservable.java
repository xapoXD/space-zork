package zork.game.equipable.observer;

import zork.game.equipable.Item;
import zork.game.equipable.Key;
import zork.game.equipable.Weapon;

public interface InventoryObservable {

    void addItem(Item item);

    void removeItem(Item item);

    Weapon getEquipedWeapon();

    Weapon getWeaponFromInventory();

    Key getKeyFromInventory();

    String equipItem(Item item);

    String unequipItem(Item item);

    Item getItemByName(String itemName);

    public boolean isInventoryFull();

}
