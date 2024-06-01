package zork.command;

import zork.game.GameData;
import zork.game.equipable.Item;
import zork.game.equipable.Weapon;
import zork.ui.CommandLineUi;

public class PickUpCommand implements Command{

    @Override
    public String getName() {
        return "pickUp";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }

        String itemName = "";
        if(arguments.length == 1){
            CommandLineUi.log.info("pickUp ");
            return "missing item name";
        }else{
            itemName = arguments[1];
        }


        Item item = gameData.getCurrentRoom().getItemByName(itemName);
        CommandLineUi.log.info("pickUp " + itemName);

        if(item == null){
            return "Item does not exist";
        }



        if(gameData.getInventory().isInventoryFull()){
            return "Inventory is full";
        }else{

            if(gameData.getInventory().containsClass(Weapon.class) && item instanceof Weapon) { //your inventory contains weapon

                if(gameData.getInventory().getEquipedWeapon() == null){
                    gameData.getCurrentRoom().unRegisterItem(item);

                    Weapon inventoryWeapon = gameData.getInventory().getWeaponFromInventory();
                    gameData.getInventory().removeItem((Item) inventoryWeapon);
                    gameData.getCurrentRoom().registerItem((Item) inventoryWeapon);
                    gameData.getInventory().addItem(item);

                    return "You picked up the weapon and threw away your current one from the inventory";
                }else{ //you alredy have a weapon equiped -> unequip -> drop to room –> add new one to inventory
                    if(item instanceof Weapon){
                        gameData.getCurrentRoom().unRegisterItem(item);
                        Weapon inventoryWeapon = gameData.getInventory().getWeaponFromInventory();
                        gameData.getInventory().removeItem((Item) inventoryWeapon);
                        gameData.getCurrentRoom().registerItem((Item) inventoryWeapon);
                        gameData.getInventory().addItem(item);

                        gameData.getInventory().unequipItem((Item) inventoryWeapon); // needs extension for more items than weapon

                        return "Your picked up the weapon and threw away your equipped one, you are now weaponless";
                    }else {
                        gameData.getCurrentRoom().unRegisterItem(item);
                        gameData.getInventory().addItem(item);

                        return "Item picked up";
                    }

                }

            }else{ //your inventory does not contain weapon
                gameData.getCurrentRoom().unRegisterItem(item);
                gameData.getInventory().addItem(item);

                return "Item picked up";
            }


        }



    }
}
