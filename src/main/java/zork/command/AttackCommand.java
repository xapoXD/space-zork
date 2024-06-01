package zork.command;

import zork.game.GameData;
import zork.game.enemy.Enemy;
import zork.ui.CommandLineUi;

public class AttackCommand implements Command{

    @Override
    public String getName() {
        return "attack";
    }


    @Override
    public String execute(String[] arguments, GameData gameData) {

        if(gameData.getCurrentRoom().getEnemies().size() >= 2){ // I want input

            String enemyName = "";
            if(arguments.length == 1){
                if(gameData.getCurrentRoom().getIsInCombat()){
                    gameData.checkCombat();
                }
                CommandLineUi.log.info("attack ");
                return "missing enemy name";
            }else{
                enemyName = arguments[1];
            }

            Enemy enemy = gameData.getCurrentRoom().getEnemyByName(enemyName);

            CommandLineUi.log.info("attack " + enemyName);

            if(enemy == null){
                if(gameData.getCurrentRoom().getIsInCombat()){
                    gameData.checkCombat();
                }
                //CommandLineUi.log.info("there is no-one to fight by that name");
                return "there is no-one to fight by that name";
            }else{

                    return gameData.initiateCombat(enemy);

            }


        }else{ // I dont want input

            if(gameData.getCurrentRoom().getEnemies().size() == 1){

                Enemy enemy = gameData.getCurrentRoom().getEnemies().get(0);

                CommandLineUi.log.info("attack " + enemy.getType().getName());

                return gameData.initiateCombat(enemy);

            }else{
                return "there is no-one to fight";
            }


        }
    }




}
