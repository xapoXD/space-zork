package zork.command;

import org.junit.Assert;
import zork.game.GameDataImpl;
import zork.game.Room;
import zork.game.RoomImpl;
import zork.game.enemy.Enemy;
import zork.game.enemy.EnemyBuilder;
import zork.game.enemy.EnemyBuilderImpl;
import zork.game.equipable.Item;
import zork.game.equipable.ItemImpl;
import zork.game.equipable.Weapon;
import zork.game.equipable.WeaponImpl;

import static org.junit.jupiter.api.Assertions.*;

class AttackCommandTest {

    @org.junit.jupiter.api.Test
    public void attackCommand_test_OneOrLess() {

        String enemyName[] = new String[10];

        enemyName[0] = "attack";

        GameDataImpl gameData = new GameDataImpl();
        Room startRoom = new RoomImpl("startRoom", "start", false);
        gameData.setCurrentRoom(startRoom);

        EnemyBuilder enemyBuilder = new EnemyBuilderImpl("Mucus");
        Enemy mucus = enemyBuilder.setHealth(150).setDamageLow(40).setDamageHigh(40).build();


        Weapon testWeapon = new WeaponImpl("testWP",5,5,5);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().equipItem((Item) testWeapon);


        Command attack = new AttackCommand();

        //non existing enemy
        //enemyName[1] = "NonExistingEnemy";
        String result2 = attack.execute(enemyName, gameData);

        System.out.println(result2);
        Assert.assertTrue(result2.equals("there is no-one to fight"));

    }

    @org.junit.jupiter.api.Test
    public void goCommand_test_ExistingEnemy() {

        String enemyName[] = new String[10];

        enemyName[0] = "attack";

        GameDataImpl gameData = new GameDataImpl();
        Room startRoom = new RoomImpl("startRoom", "start", false);
        gameData.setCurrentRoom(startRoom);

        EnemyBuilder enemyBuilder = new EnemyBuilderImpl("Mucus");
        Enemy mucus = enemyBuilder.setHealth(150).setDamageLow(40).setDamageHigh(40).build();


        Weapon testWeapon = new WeaponImpl("testWP",5,5,5);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().equipItem((Item) testWeapon);


        Command attack = new AttackCommand();

        // existing enemy
        enemyName[1] = "Mucus";

        startRoom.registerEnemy(mucus);

        String result1 = attack.execute(enemyName, gameData);


        System.out.println(result1);

        Assert.assertTrue(result1.equals(
                "Status after attacks: \n" + mucus.getType().getName() + " attacked with " + 40 + " dmg\n"
                        + "You attacked with " + 5 + " dmg\n"
                        + mucus.getType().getName() +" health: "+ 145 +" vs " + "Your health: " + 110));

    }



    @org.junit.jupiter.api.Test
    public void goCommand_test_CorrectDmgToEnemy() {
        String enemyName[] = new String[10];
        enemyName[0] = "attack";
        GameDataImpl gameData = new GameDataImpl();
        Room startRoom = new RoomImpl("startRoom", "start", false);
        gameData.setCurrentRoom(startRoom);
        EnemyBuilder enemyBuilder = new EnemyBuilderImpl("Mucus");
        Enemy mucus = enemyBuilder.setHealth(150).setDamageLow(40).setDamageHigh(40).build();
        enemyName[1] = "Mucus";

        Weapon testWeapon = new WeaponImpl("testWP",5,5,5);
        gameData.getInventory().addItem((Item) testWeapon);
        gameData.getInventory().equipItem((Item) testWeapon);

        startRoom.registerEnemy(mucus);
        Command attack = new AttackCommand();

        String result1 = attack.execute(enemyName, gameData);

        enemyName[1] = "Mucus";
        String result = attack.execute(enemyName, gameData);

        System.out.println(result);
        Assert.assertTrue(mucus.getHealth().equals(140));

    }

    @org.junit.jupiter.api.Test
    public void goCommand_test_TwoAndMore_missingEnemyName() {

        String enemyName[] = new String[1];

        enemyName[0] = "attack";

        GameDataImpl gameData = new GameDataImpl();
        Room startRoom = new RoomImpl("startRoom", "start", false);
        gameData.setCurrentRoom(startRoom);
        EnemyBuilder enemyBuilder = new EnemyBuilderImpl("Mucus");
        Enemy mucus = enemyBuilder.setHealth(150).setDamageLow(40).setDamageHigh(40).build();
        Enemy mucus2 = enemyBuilder.setHealth(100).setDamageLow(40).setDamageHigh(40).build();
        Enemy mucus3 = enemyBuilder.setHealth(50).setDamageLow(40).setDamageHigh(40).build();
        startRoom.registerEnemy(mucus);
        startRoom.registerEnemy(mucus2);
        startRoom.registerEnemy(mucus3);


        Command attack = new AttackCommand();

        String result = attack.execute(enemyName, gameData);

        System.out.println(result);

        Assert.assertTrue(result.equals("missing enemy name"));

    }

}