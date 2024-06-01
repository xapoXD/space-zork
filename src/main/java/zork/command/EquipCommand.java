package zork.command;

import zork.game.GameData;
import zork.game.equipable.Item;
import zork.ui.CommandLineUi;

public class EquipCommand implements Command{

    @Override
    public String getName() {
        return "equip";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }

        String itemName = "";
        if(arguments.length == 1){
            CommandLineUi.log.info("equip ");
            return "missing item name";
        }else{
            itemName = arguments[1];
        }
        CommandLineUi.log.info("equip " + itemName);

        Item item = gameData.getInventory().getItemByName(itemName);

        if(item == null){
           // CommandLineUi.log.info("equip: the item does not exist in your inventory");
            return "the item does not exist in your inventory";
        }
        //CommandLineUi.log.info("equip " + item.getName());
        return gameData.getInventory().equipItem(item);
    }
}
