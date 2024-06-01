package zork.command;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import zork.game.GameDataImpl;

import static org.junit.jupiter.api.Assertions.*;

class EndCommandTest {

    @Test
    void execute() {

        EndCommand endCommand = new EndCommand();
        GameDataImpl gameData = new GameDataImpl();

        String result = endCommand.execute(null, gameData);
        Assert.assertTrue(result.equals("You left you planet to deal with the Imperium by itself"));

    }
}