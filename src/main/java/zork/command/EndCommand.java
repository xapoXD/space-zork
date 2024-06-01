package zork.command;

import zork.game.GameData;
import zork.ui.CommandLineUi;

public class EndCommand implements Command {

    @Override
    public String getName() {
        return "end";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        gameData.setFinished(false);
        CommandLineUi.log.info("End: GAME ENDED BY USER");
        return "You left you planet to deal with the Imperium by itself";
    }
}

