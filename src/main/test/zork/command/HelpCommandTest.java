package zork.command;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import zork.game.GameDataImpl;

import java.util.HashMap;
import java.util.Map;

class HelpCommandTest {

    @org.junit.jupiter.api.Test
    public void get_list_of_possible_commands() {

        Map<String, Command> commands = new HashMap<>();
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



        GameDataImpl gameData = Mockito.spy(new GameDataImpl());
        String result = help.execute(null, gameData);

        Assert.assertTrue(result.equals("List of possible commands: [help, equip, putDown, lookAround, attack, go, reset, pickUp, end, inventory]"));
    }
}