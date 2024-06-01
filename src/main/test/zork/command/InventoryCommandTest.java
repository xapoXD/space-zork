package zork.command;

import org.junit.Assert;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;
import zork.game.equipable.Item;
import zork.game.equipable.Weapon;
import zork.game.equipable.WeaponImpl;

import static org.junit.jupiter.api.Assertions.*;

class InventoryCommandTest {

    @org.junit.jupiter.api.Test
    public void inventoryTest_InvIsEmpty() {

        GameDataImpl gameData = new GameDataImpl();

        gameData.getInventory().removeAllItems();
        Command inventory = new InventoryCommand();

        // inventory is empty
        String result = inventory.execute(null, gameData);
        System.out.println(result);
        Assert.assertTrue(result.equals("Your inventory is empty"));

    }

    @org.junit.jupiter.api.Test
    public void inventoryTest_InvIsNotEmpty() {

        GameDataImpl gameData = new GameDataImpl();
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        //gameData.getInventory().getDescriptionInventory();
        //gameData.getInventory().getItemList();
        Command inventory = new InventoryCommand();

        //inventory is not empty
        gameData.getInventory().addItem((Item) testWeapon);
        String compare = "Your inventory: \n" + gameData.getInventory().getDescriptionInventory();
        String result2 = inventory.execute(null, gameData);
        System.out.println(result2);

        Assert.assertTrue(result2.equals(compare));



    }


}