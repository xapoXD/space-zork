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

class PickUpCommandTest {

    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_missingItemName() {

        String itemName[] = new String[1];

        itemName[0] = "pickUp";
        GameDataImpl gameData = new GameDataImpl();


        Command pickUp = new PickUpCommand();
        // test of missing room name
        String result = pickUp.execute(itemName, gameData);

        System.out.println("RESULT 1: " + result);
        Assert.assertTrue(result.equals("missing item name"));

    }

    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_weaponDoestNotExist() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp ";
        itemName[1] = "Blaster";

        GameDataImpl gameData = new GameDataImpl();

        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem((Item) testWeapon);

        Command pickUp = new PickUpCommand();

        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 2: " + result);

        Assert.assertTrue(result.equals("Item does not exist"));
    }


    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_weaponExistInInventory() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp";
        itemName[1] = "testWP";

        GameDataImpl gameData = new GameDataImpl();

        Weapon Blaster = new WeaponImpl("Blaster",2,5,10);
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) Blaster);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem((Item) testWeapon);

        Command pickUp = new PickUpCommand();

        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 3: " + result);

        Assert.assertTrue(result.equals("You picked up the weapon and threw away your current one from the inventory"));
    }

    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_weaponExistInInventory_WeaponIsEquipped() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp";
        itemName[1] = "testWP";

        GameDataImpl gameData = new GameDataImpl();

        Weapon Blaster = new WeaponImpl("Blaster",2,5,10);
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) Blaster);
        gameData.getInventory().equipItem((Item) Blaster);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem((Item) testWeapon);

        Command pickUp = new PickUpCommand();

        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 4: " + result);

        Assert.assertTrue(result.equals("Your picked up the weapon and threw away your equipped one, you are now weaponless"));
    }


    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_weaponExistInInventory_WeaponIsEquipped_PickUpNormalItem() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp";
        itemName[1] = "Key";

        GameDataImpl gameData = new GameDataImpl();

        Item key = new Key("Key",2);
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().equipItem((Item) testWeapon);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem(key);

        Command pickUp = new PickUpCommand();

        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 6: " + result);

        Assert.assertTrue(result.equals("Item picked up"));
    }







    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_weaponLess_invDontContainWeapon() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp";
        itemName[1] = "testWP";

        GameDataImpl gameData = new GameDataImpl();
        gameData.getInventory().removeAllItems();

        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem((Item) testWeapon);



        Command pickUp = new PickUpCommand();
        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 5: " + result);

        Assert.assertTrue(result.equals("Item picked up"));
    }



    @org.junit.jupiter.api.Test
    public void pickUpCommand_test_inventoryFull() {

        String itemName[] = new String[10];

        itemName[0] = "pickUp";
        itemName[1] = "Key";

        GameDataImpl gameData = new GameDataImpl();

        Item key = new Key("Key",2);
        Item potion = new Key("Potion",2);
        Weapon testWeapon = new WeaponImpl("testWP",2,5,10);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().addItem(potion);
        gameData.getInventory().equipItem((Item) testWeapon);

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        gameData.setCurrentRoom(testRoom);
        testRoom.registerItem(key);

        Command pickUp = new PickUpCommand();

        String result = pickUp.execute(itemName, gameData);
        System.out.println("RESULT 6: " + result);

        Assert.assertTrue(result.equals("Inventory is full"));
    }

}