package zork.command;

import zork.game.GameData;
import zork.ui.CommandLineUi;

public class LookAroundCommand implements Command{
    @Override
    public String getName() {
        return "lookAround";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }

        CommandLineUi.log.info("lookAround");
        return  gameData.getCurrentRoom().getDescriptionAll();
    }
}
