package zork.command;

import zork.game.GameData;
import zork.game.equipable.Item;
import zork.game.equipable.Weapon;
import zork.ui.CommandLineUi;

public class PutDownCommand implements Command{
    @Override
    public String getName() {
        return "putDown";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }

        String itemName = "";
        if(arguments.length == 1){
            CommandLineUi.log.info("putDown: ");
            return "missing item name";
        }else{
            itemName = arguments[1];
        }

        Item item = gameData.getInventory().getItemByName(itemName);
        CommandLineUi.log.info("putDown " + itemName);
        if(item == null){
           // CommandLineUi.log.info("putDown: Item does not exist");
            return "Item does not exist";
        }


        if(item instanceof Weapon){

            if(gameData.getInventory().getEquipedWeapon() == null){
                gameData.getInventory().removeItem(item);
                gameData.getCurrentRoom().registerItem(item);
                //CommandLineUi.log.info("putDown: Weapon was thrown away");
                return "Weapon was thrown away";
            }else{
                gameData.getInventory().removeItem(item);
                gameData.getCurrentRoom().registerItem(item);
                //CommandLineUi.log.info("putDown: Equipped weapon was thrown away, you are now weaponless");
                return "Equipped weapon was thrown away, you are now weaponless";
            }

        }else{
            gameData.getInventory().removeItem(item);
            gameData.getCurrentRoom().registerItem(item);
            //CommandLineUi.log.info("putDown: Item was thrown away");
            return "Item was thrown away";

        }

    }
}
