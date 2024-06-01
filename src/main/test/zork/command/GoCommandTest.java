package zork.command;

import org.junit.Assert;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;
import zork.game.equipable.Item;
import zork.game.equipable.Key;

class GoCommandTest {

    @org.junit.jupiter.api.Test
    public void goCommand_test_missingRoomName() {

        String roomName[] = new String[1];

        roomName[0] = "go";


        GameDataImpl gameData = new GameDataImpl();
        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        Room startRoom = new RoomImpl("startRoom", "start", false);
        Room lockedRoom = new RoomImpl("lockedRoom","this is a locked room",true);

        startRoom.registerExit(testRoom);
        startRoom.registerExit(lockedRoom);
        gameData.setCurrentRoom(startRoom);

        Command go = new GoCommand();
        // test of missing room name
        String result = go.execute(roomName, gameData);

        System.out.println(result);
        Assert.assertTrue(result.contains("missing room name"));

    }

    @org.junit.jupiter.api.Test
    public void goCommand_test() {

        String roomName[] = new String[10];
        roomName[0] = "go";
        GameDataImpl gameData = new GameDataImpl();
        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        Room startRoom = new RoomImpl("startRoom", "start", false);
        Room lockedRoom = new RoomImpl("lockedRoom","this is a locked room",true);
        startRoom.registerExit(testRoom);
        startRoom.registerExit(lockedRoom);
        gameData.setCurrentRoom(startRoom);
        Command go = new GoCommand();

        // test of correctly spelled room and it has the exists
        roomName[1] = "testRoom";
        String result1 = go.execute(roomName, gameData);

        System.out.println(result1);
        Assert.assertTrue(testRoom.equals(gameData.getCurrentRoom()));

    }

    @org.junit.jupiter.api.Test
    public void goCommand_test_LockedWithKey() {
        String roomName[] = new String[10];
        roomName[0] = "go";
        GameDataImpl gameData = new GameDataImpl();
        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        Room startRoom = new RoomImpl("startRoom", "start", false);
        Room lockedRoom = new RoomImpl("lockedRoom","this is a locked room",true);
        startRoom.registerExit(testRoom);
        startRoom.registerExit(lockedRoom);
        gameData.setCurrentRoom(startRoom);
        Command go = new GoCommand();

        // test of locked room if you have a key
        Item key = new Key("key",1);
        gameData.getInventory().addItem(key);
        roomName[1] = "lockedRoom";
        String result4 = go.execute(roomName, gameData);

        System.out.println(result4);
        Assert.assertTrue(lockedRoom.equals(gameData.getCurrentRoom()));

    }

    @org.junit.jupiter.api.Test
    public void goCommand_test_LockedNoKey() {
        String roomName[] = new String[10];
        roomName[0] = "go";
        GameDataImpl gameData = new GameDataImpl();
        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        Room startRoom = new RoomImpl("startRoom", "start", false);
        Room lockedRoom = new RoomImpl("lockedRoom","this is a locked room",true);
        startRoom.registerExit(testRoom);
        startRoom.registerExit(lockedRoom);
        gameData.setCurrentRoom(startRoom);
        Command go = new GoCommand();


        // test of locked room if you don't have a key
        roomName[1] = "lockedRoom";
        String result3 = go.execute(roomName, gameData);

        System.out.println(result3);
        Assert.assertTrue(result3.equals("This room is locked, you need a key to get inside"));
    }


    @org.junit.jupiter.api.Test
    public void goCommand_test_ExitDoesNotExist() {
        String roomName[] = new String[10];
        roomName[0] = "go";
        GameDataImpl gameData = new GameDataImpl();
        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        Room startRoom = new RoomImpl("startRoom", "start", false);
        Room lockedRoom = new RoomImpl("lockedRoom","this is a locked room",true);
        startRoom.registerExit(testRoom);
        startRoom.registerExit(lockedRoom);
        gameData.setCurrentRoom(startRoom);
        Command go = new GoCommand();

        // test of existence of the exit
        roomName[1] = "nonRoom";
        String result2 = go.execute(roomName, gameData);

        System.out.println(result2);
        Assert.assertTrue(result2.equals("the exit does not exist"));
    }

    }