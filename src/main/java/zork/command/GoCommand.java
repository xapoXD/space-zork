package zork.command;

import zork.game.GameData;
import zork.game.Room;
import zork.game.equipable.Key;
import zork.ui.CommandLineUi;

public class GoCommand implements Command{
    @Override
    public String getName() {
        return "go";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getIsInCombat()){
            gameData.checkCombat();
        }


        String roomName;

        if(arguments.length == 1){
            CommandLineUi.log.info("go ");
            if(gameData.getCurrentRoom().getIsInCombat()){
                gameData.checkCombat();
            }
            CommandLineUi.log.info("go ");
            return "missing room name";
        }else{
            roomName = arguments[1];
        }

        CommandLineUi.log.info("go " + roomName);

        Room exitByName = gameData.getCurrentRoom().getExitByName(roomName);
        if(exitByName == null){
            //CommandLineUi.log.info("go the exit does not exist");
            return "the exit does not exist";
        }

        if(!exitByName.getDescriptionLocked()){

            gameData.setCurrentRoom(exitByName);
            //CommandLineUi.log.info("go " + gameData.getCurrentRoom().getName());

            if(exitByName.getEnemyRandom() != null){
                exitByName.setInCombat(true);
            }

            return "Transferred to room: " + gameData.getCurrentRoom().getDescriptionAll();
        }else{
            if(gameData.getInventory().getKeyFromInventory() == null){
                if(gameData.getCurrentRoom().getIsInCombat()){
                    gameData.checkCombat();
                }
                //CommandLineUi.log.info("go This room is locked, you need a key to get inside");
                return "This room is locked, you need a key to get inside";
            } else{
                exitByName.setLocked(false); // ==> unlocked
                Key key = gameData.getInventory().getKeyFromInventory();
                gameData.getInventory().removeItem(key);
                gameData.setCurrentRoom(exitByName);
                //CommandLineUi.log.info("Room unlocked with key");
                return "Room unlocked with key: " + gameData.getCurrentRoom().getDescriptionAll();
            }

        }

    }
}
