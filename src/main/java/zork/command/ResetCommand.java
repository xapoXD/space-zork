package zork.command;

import zork.game.GameData;

import static zork.ui.CommandLineUi.log;

public class ResetCommand implements Command {
    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        gameData.resetAll();
        gameData.init();
        log.info("reset");
        return "The game has been reset \n" + gameData.getCurrentRoom().getDescriptionAll();

    }
}
