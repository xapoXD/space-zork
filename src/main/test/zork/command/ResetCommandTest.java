package zork.command;

import zork.command.ResetCommand;
import zork.game.GameDataImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ResetCommandTest {

    @Test
    public void reset_whenCalled_thenInvokeResetOnGameData(){
        ResetCommand resetCommand = new ResetCommand();

        GameDataImpl gameData = Mockito.spy(new GameDataImpl());

        String result = resetCommand.execute(null, gameData);
        Assert.assertTrue(result.equals("The game has been reset \n" + gameData.getCurrentRoom().getDescriptionAll()));
        Mockito.verify(gameData, Mockito.times(1)).init();
    }

}
