package zork.command;

import org.junit.Assert;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;
import zork.game.equipable.Item;
import zork.game.equipable.Weapon;
import zork.game.equipable.WeaponImpl;

import static org.junit.jupiter.api.Assertions.*;

class EquipCommandTest {

    @org.junit.jupiter.api.Test
    public void equipCommand_test_itemInInventory() {

        String itemName[] = new String[10];

        itemName[0] = "equip";
        itemName[1] = "testWP";


        GameDataImpl gameData = new GameDataImpl();


        Command equip = new EquipCommand();
        String result = equip.execute(itemName, gameData);
        System.out.println(result);

        //is in inventory
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);
        String result2 = equip.execute(itemName, gameData);
        System.out.println(result2);

        Assert.assertTrue(testWeapon.equals(gameData.getInventory().getEquipedWeapon()));

    }

    @org.junit.jupiter.api.Test
    public void equipCommand_test_itemIsNotInInventory() {

        String itemName[] = new String[10];

        itemName[0] = "equip";
        itemName[1] = "testWP";


        GameDataImpl gameData = new GameDataImpl();


        Command equip = new EquipCommand();
        String result = equip.execute(itemName, gameData);
        System.out.println(result);
        // is not in inventory
        Assert.assertTrue(result.equals("the item does not exist in your inventory"));

    }



    @org.junit.jupiter.api.Test
    public void goCommand_test_missingEnemyName() {

        String weaponName[] = new String[1];

        weaponName[0] = "attack";

        GameDataImpl gameData = new GameDataImpl();

        Command equip = new EquipCommand();

        String result = equip.execute(weaponName, gameData);

        System.out.println(result);

        Assert.assertTrue(result.equals("missing item name"));

    }

}