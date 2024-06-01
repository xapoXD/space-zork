package zork.command;

import org.junit.Assert;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;
import zork.game.equipable.Item;
import zork.game.equipable.Key;
import zork.game.equipable.Weapon;
import zork.game.equipable.WeaponImpl;

import static org.junit.jupiter.api.Assertions.*;

class PutDownCommandTest {

    @org.junit.jupiter.api.Test
    public void putDownCommand_test_missingItemName() {

        String itemName[] = new String[1];

        itemName[0] = "putDown";
        GameDataImpl gameData = new GameDataImpl();


        Command pickUp = new PickUpCommand();
        // test of missing room name
        String result = pickUp.execute(itemName, gameData);

        System.out.println("RESULT 1: " + result);
        Assert.assertTrue(result.equals("missing item name"));

    }

    @org.junit.jupiter.api.Test
    public void putDownCommand_test_ItemDoesNotExist() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp ";
        itemName[1] = "Blaster";

        GameDataImpl gameData = new GameDataImpl();

        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);

        Command putDown = new PutDownCommand();

        String result = putDown.execute(itemName, gameData);
        System.out.println(result);
        Assert.assertTrue(result.equals("Item does not exist"));
    }

    @org.junit.jupiter.api.Test
    public void putDownCommand_test_IsNotWeapon() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp ";
        itemName[1] = "key";

        GameDataImpl gameData = new GameDataImpl();

        Key key = new Key("key",2);
        gameData.getInventory().addItem(key);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);

        Command putDown = new PutDownCommand();

        String result = putDown.execute(itemName, gameData);
        System.out.println(result);

        Assert.assertTrue(result.equals("Item was thrown away"));
    }


    @org.junit.jupiter.api.Test
    public void putDownCommand_test_WeaponInInventory() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp ";
        itemName[1] = "testWP";

        GameDataImpl gameData = new GameDataImpl();

        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);

        Command putDown = new PutDownCommand();

        String result = putDown.execute(itemName, gameData);
        System.out.println(result);

        Assert.assertTrue(result.equals("Weapon was thrown away"));
    }

    @org.junit.jupiter.api.Test
    public void putDownCommand_test_WeaponIsEquipped() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp ";
        itemName[1] = "testWP";

        GameDataImpl gameData = new GameDataImpl();

        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().equipItem((Item) testWeapon);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);

        Command putDown = new PutDownCommand();

        String result = putDown.execute(itemName, gameData);
        System.out.println(result);

        Assert.assertTrue(result.equals("Equipped weapon was thrown away, you are now weaponless"));
    }

}