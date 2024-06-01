package zork.command;

import zork.game.GameData;
import zork.ui.CommandLineUi;

public class InventoryCommand implements Command{
    @Override
    public String getName() {
        return "inventory";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }
        CommandLineUi.log.info("inventory");

        if(!gameData.getInventory().getItemList().isEmpty()){
            return "Your inventory: \n" + gameData.getInventory().getDescriptionInventory();
                   // + "" + gameData.getCurrentWeapon().getAttackRange();
        }else {

            return "Your inventory is empty";
        }

    }
}
