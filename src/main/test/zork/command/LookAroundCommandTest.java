package zork.command;

import org.junit.Assert;
import org.mockito.Mockito;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LookAroundCommandTest {

    @org.junit.jupiter.api.Test
    public void get_AllDescription() {

        Room testRoom = new RoomImpl("testRoom","this is a test room",false);
        GameDataImpl gameData = new GameDataImpl();
        gameData.setCurrentRoom(testRoom);
        gameData.getCurrentRoom().getDescriptionAll();

        Command lookAround = new LookAroundCommand();


        String compare = "You are in " +  gameData.getCurrentRoom().getName()
                + ", " + gameData.getCurrentRoom().getDescription() + "\n"
                + gameData.getCurrentRoom().getDescriptionWithExits() + "\n"
                + gameData.getCurrentRoom().getDescriptionWithWeapons() + "\n"
                + gameData.getCurrentRoom().getDescriptionWithEnemies();


        String result = lookAround.execute(null, gameData);

        Assert.assertTrue(result.equals(compare));
    }

}