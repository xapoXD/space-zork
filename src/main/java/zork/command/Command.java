package zork.command;

import zork.game.GameData;

/**
 *  Represents Command interface containing method to indentify and execute command
 */
public interface Command {

    String getName();
    String execute(String[] arguments, GameData gameData);

}
