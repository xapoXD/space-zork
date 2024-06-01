package zork.command;

import zork.game.GameData;
import zork.ui.CommandLineUi;

import java.util.Map;

public class HelpCommand implements Command {

    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands){
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }

        CommandLineUi.log.info("help");

        return "List of possible commands: " + commands.keySet().toString();
    }
}

