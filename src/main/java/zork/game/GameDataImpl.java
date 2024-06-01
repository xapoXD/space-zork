package zork.game;

import zork.game.enemy.Enemy;
import zork.game.enemy.EnemyBuilder;
import zork.game.enemy.EnemyBuilderImpl;
import zork.game.equipable.*;
import zork.ui.CommandLineUi;

import java.util.ArrayList;
import java.util.List;

/**
 *  All mutable game data should exist within this class
 *  e.g. room map, finished, inventory, weapons..
 */
public class GameDataImpl implements GameData {

    private Room currentRoom;
    private boolean finished = true;
    private List<Room> rooms;
    private Inventory inventory = new Inventory();
    private Integer playerHealth;
    /**
     *  Room map registration in constructor
     */
    public GameDataImpl(){
        this.init();
    }

    public void init(){
        this.rooms = new ArrayList<>();
        Room baseRoom = new RoomImpl("Lobby", "Lobby of the spaceship ZORK with lots of rooms around", false);
        Weapon blaster = new WeaponImpl("Blaster",7,15,25);
        Item healthPotion = new Potion("HealthPotion",1);
        Item manaPotion = new Potion("ManaPotion",1);
        Item staminaPotion = new Potion("StaminaPotion",1);
        rooms.add(baseRoom);
        baseRoom.registerItem((ItemImpl) blaster);
        baseRoom.registerItem(healthPotion);
        baseRoom.registerItem(manaPotion);
        baseRoom.registerItem(staminaPotion);

        Room cinema = new RoomImpl("Cinema", "Cinema is projecting document about whales, there is a group of Imperial soldiers watching it with passion, you disturbed them...", false);
        baseRoom.registerExit(cinema);
        cinema.registerExit(baseRoom);
        Weapon M4 = new WeaponImpl("M4",10,50,80);
        EnemyBuilder enemyBuilderSol = new EnemyBuilderImpl("Soldier");
        Enemy soldier = enemyBuilderSol.setHealth(40).setDamageLow(10).setDamageHigh(20).setItem((ItemImpl) M4).build();
        //Enemy soldier2 = enemyBuilder.setHealth(30).setDamageLow(15).setDamageHigh(30).build();
        cinema.registerEnemy(soldier);
        //corridor.registerEnemy(soldier2);
        rooms.add(cinema);


        // TODO: implement gamba
        Room dineRoom = new RoomImpl("DineRoom", "Dine room with a strange looking fridge, does it look like a slot machine?", false);
        baseRoom.registerExit(dineRoom);
        dineRoom.registerExit(baseRoom);
        rooms.add(dineRoom);

        Room corridor = new RoomImpl("Corridor", "Long corridor with something sticking on the ceiling, it's looking at you...", false);
        baseRoom.registerExit(corridor);
        corridor.registerExit(baseRoom);
        rooms.add(corridor);

        EnemyBuilder enemyBuilder = new EnemyBuilderImpl("Mucus");
        Weapon yoyo = new WeaponImpl("Yoyo",10,-100,200); // adding health
        Enemy mucus = enemyBuilder.setHealth(150).setDamageLow(5).setDamageHigh(40).setItem((ItemImpl) yoyo).build();
        Enemy smallmucus = enemyBuilder.setHealth(30).setDamageLow(15).setDamageHigh(30).build(); // registered new enemy
        corridor.registerEnemy(mucus);
        corridor.registerEnemy(smallmucus);



        Room serverRoom = new RoomImpl("ServerRoom", "Small room with dead soldiers of the Imperium, one of them is holding something...", false);
        Weapon granade = new WeaponImpl("Granade",1,5,50);
        baseRoom.registerExit(serverRoom);
        serverRoom.registerExit(baseRoom);
        serverRoom.registerItem((ItemImpl) granade);
        rooms.add(serverRoom);


        Room BossRoom = new RoomImpl("Congressroom", "Congress room with a big purple mutant standing on the podium holding a gauntlet", true);
        serverRoom.registerExit(BossRoom);
        cinema.registerExit(BossRoom);
        EnemyBuilder enemyBuilder2 = new EnemyBuilderImpl("Thanos");
        Enemy thanos = enemyBuilder2.setHealth(150).setDamageLow(30).setDamageHigh(80).setSpecialEnemy(true).build();
        BossRoom.registerEnemy(thanos);
        rooms.add(serverRoom);


        Room bedrooms = new RoomImpl("Bedroom", "Low ceiling room, there is a Imperial General resting on one of the beds...", false);
        bedrooms.registerExit(dineRoom);
        bedrooms.registerExit(cinema);
        rooms.add(bedrooms);
        EnemyBuilder enemyBuilder3 = new EnemyBuilderImpl("General");
        Key key = new Key("CongressRoomKey",1);
        Enemy general = enemyBuilder3.setHealth(110).setDamageLow(10).setDamageHigh(35).setItem(key).build();
        bedrooms.registerEnemy(general);


        cinema.registerExit(bedrooms);
        dineRoom.registerExit(bedrooms);

        this.playerHealth = 150;
        this.currentRoom = baseRoom;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }
    /**
     *  Sets room, where the user currently resides
     */
    @Override
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Integer getPlayerHealth(){
        return playerHealth;
    }

    @Override
    public void setPlayerHealth(Integer playerHealth) {
        this.playerHealth = playerHealth;
    }

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void resetAll(){
        inventory.removeAllItems();
    }

    /**
     *  Sets finished flag, indicating game is done/finished
     */
    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     *  Retrieves finished flag -> parent components decides whether to end the game
     *  based on this method
     */
    @Override
    public boolean isFinished() {
        return finished;
    }


    @Override
    public String initiateCombat(Enemy enemy) { // one round

        int enemyAttack = 0;
        int playerAttack = 0;

        Weapon isWeaponEquipped = inventory.getEquipedWeapon();
        if (isWeaponEquipped == null) {
            //oponnent attacks
            enemyAttack = enemyAttack(enemy);
            //CommandLineUi.log.info("attack: Final boss was slain, ending the game");
            return "Status after attacks: \n" + enemy.getType().getName() + " attacked with " + enemyAttack + " dmg\n"
                    + "You didn't attacked this turn -> You don't have any weapon equipped, equip weapon by typing 'equip' + 'weapon name'\n"
                    + enemy.getType().getName() +" health: "+ enemy.getHealth() +" vs " + "Your health: " + getPlayerHealth();

        } else {
            //firstly player attacks
            playerAttack = playerAttack(enemy);

            if (getCurrentRoom().getEnemyByName(enemy.getType().getName()).getHealth() <= 0) { //killed enemy
                //if ur fighting a specialEnemy
                if(enemy.getSpecialEnemy()){ //END GAME
                    System.out.println("You slain the enemy " + enemy.getType().getName());
                    //CommandLineUi.log.info("attack: Final boss was slain, ending the game");
                    setFinished(false);
                }else{
                    if(getCurrentRoom().getEnemyByName(enemy.getType().getName()).getDropItem() != null){
                        getCurrentRoom().registerItem(getCurrentRoom().getEnemyByName(enemy.getType().getName()).getDropItem());
                        getCurrentRoom().unregisterEnemy(enemy);
                        if(getCurrentRoom().getEnemyRandom() == null){
                            getCurrentRoom().setInCombat(false);
                        }
                        //CommandLineUi.log.info("attack: You slain the enemy " + enemy.getType().getName());
                        return "Status after attacks: \n"  + "You attacked with " + playerAttack + " dmg\n"
                                + "You slain the enemy " + enemy.getType().getName() + "\n"
                                + enemy.getType().getName() +" health: "+ enemy.getHealth() +" vs " + "Your health: " + getPlayerHealth()
                                + "\nEnemy has dropped some loot!!!";
                    }else{
                        getCurrentRoom().unregisterEnemy(enemy);
                        if(getCurrentRoom().getEnemyRandom() == null){
                            getCurrentRoom().setInCombat(false);
                        }
                        //CommandLineUi.log.info("attack: You slain the enemy " + enemy.getType().getName());
                        return "Status after attacks: \n"  + "You attacked with " + playerAttack + " dmg\n"
                                + "You slain the enemy " + enemy.getType().getName() + "\n"
                                + enemy.getType().getName() +" health: "+ enemy.getHealth() +" vs " + "Your health: " + getPlayerHealth();
                    }

                }

            } else { //enemy attacks
                enemyAttack = enemyAttack(enemy);

                if (playerHealth <= 0) { //end game
                    System.out.println("YOU DIED!!!");
                    resetAll();
                    init();
                    //CommandLineUi.log.info("attack: " + "YOU DIED!!! " + "The game was successfully reseted.");
                    return "The game was successfully reseted. \n" + getCurrentRoom().getDescriptionAll();
                }

            }
           // CommandLineUi.log.info("attack " + enemy.getType().getName());


            return "Status after attacks: \n" + enemy.getType().getName() + " attacked with " + enemyAttack + " dmg\n"
                    + "You attacked with " + playerAttack + " dmg\n"
                    + enemy.getType().getName() +" health: "+ enemy.getHealth() +" vs " + "Your health: " + getPlayerHealth();
        }

    }


    private int playerAttack(Enemy enemy){
        Integer genAttackPlayer = getInventory().getWeaponFromInventory().genAttack();
        Integer enemyHealth = getCurrentRoom().getEnemyByName(enemy.getType().getName()).getHealth();
        enemyHealth = enemyHealth - genAttackPlayer;
        getCurrentRoom().getEnemyByName(enemy.getType().getName()).setHealt(enemyHealth); //new health
        return genAttackPlayer;
    }


    private int enemyAttack(Enemy enemy){
        Integer genAttackEnemy = getCurrentRoom().getEnemyByName(enemy.getType().getName()).genAttack();
        Integer newplayerHealth = getPlayerHealth();
        newplayerHealth = newplayerHealth - genAttackEnemy;
        this.playerHealth = newplayerHealth;
        return genAttackEnemy;
    }

    @Override
    public void checkCombat(){

        if(getCurrentRoom().getIsInCombat()){ //enemy attacks
            Enemy rndEnemy = getCurrentRoom().getEnemyRandom();
            int enemyGenAttack = enemyAttack(rndEnemy);

            //CommandLineUi.log.info("enemy attack");

            System.out.println("Status after attacks: \n" + rndEnemy.getType().getName() + " attacked with " + enemyGenAttack + " dmg\n"
                    + "You didn't attacked this turn\n"
                    + rndEnemy.getType().getName() +" health: "+ rndEnemy.getHealth() +" vs " + "Your health: " + getPlayerHealth());

            if (playerHealth <= 0) { //end game
                System.out.println("YOU DIED!!!");
                resetAll();
                init();
                System.out.println("The game was successfully reseted. \n" + getCurrentRoom().getDescriptionAll());
            }
        }


    }

}
