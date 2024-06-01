package zork.game;

import zork.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class represents running game instance
 *
 */
public class GameImpl implements Game {

    private Map<String, Command> commands = new HashMap<>();
    private GameData gameData;

    public GameImpl(){
        this.registerCommands();
        this.gameData = new GameDataImpl();
    }

    /**
     *  Method registering immutable Command instances
     *
     */
    private void registerCommands(){
        Command help = new HelpCommand(commands);
        Command reset = new ResetCommand();
        GoCommand go = new GoCommand();
        Command lookAround = new LookAroundCommand();
        PickUpCommand pickUp = new PickUpCommand();
        PutDownCommand putDown = new PutDownCommand();
        InventoryCommand inventory = new InventoryCommand();
        EquipCommand equip = new EquipCommand();
        AttackCommand attack = new AttackCommand();
        EndCommand end = new EndCommand();
        commands.put(help.getName(), help);
        commands.put(reset.getName(), reset);
        commands.put(go.getName(), go);
        commands.put(lookAround.getName(), lookAround);
        commands.put(pickUp.getName(), pickUp);
        commands.put(inventory.getName(), inventory);
        commands.put(putDown.getName(), putDown);
        commands.put(equip.getName(), equip);
        commands.put(attack.getName(), attack);
        commands.put(end.getName(), end);
    }

    /**
     *  Method returns welcome message which should be printed right after
     *  game is started
     */
    @Override
    public String welcomeMessage() {
        return "WELCOME TO THE SPACE ZORK GAME\n"
                + "- - - - - - - - - - - - - - - -\n"
                + "You were sent on a secret mission to save you home planet from The Imperium, which terrorizes all the planets in the galaxy,\n"
                + "you are the last hope to stop crazy imperator from conquering your planet!\n"
                + "If you would like to know more about the commands use 'help' \n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
                + gameData.getCurrentRoom().getDescriptionAll();
    }

    /**
     *  Method returns end message which should be printed right after
     *  game is finished
     */
    @Override
    public String endMessage() {
        return "- - - - - - - - - - - - - - - -\n"
                + "Final Boss was slain you just saved your planet from the Iperium\n"
                + "You finished with: " + gameData.getPlayerHealth() + " health, good job!\n"
                + "THANK YOU FOR PLAYING THE SPACE ZORK GAME\n";
    }

    /**
     *  Method parses input line and should divide its parts to command name
     *  and array of input arguments (0-N). Based on parsed command name,
     *  specific command should be looked up and executed. If none is found,
     *  default message should be returned
     */
    @Override
    public String processTextCommand(String line) {
        //TODO zpracovat z řádku příkaz a argumenty a naplnit kde je to potřeba
        String result;
        String[] args = line.split(" ");
        Command command = commands.getOrDefault(args[0], null);

        if(command != null){
            result = command.execute(args, gameData);
        }
        else{
            result = "Unknown command, thy something else or write 'help' for the list of possible commands";
        }
        return result;
    }

    /**
     *  Method delegates its call to mutable GameData instance, which hold current game state. This
     *  state should be checked after each command evaluation a possibly terminate whole app if
     *  true is returned
     */
    @Override
    public boolean isFinished() {
        return gameData.isFinished();
    }
}
