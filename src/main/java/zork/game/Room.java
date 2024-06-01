package zork.game;

import zork.game.enemy.Enemy;
import zork.game.equipable.Item;

import java.util.Collection;
import java.util.List;

public interface Room {

    String getName();
    String getDescription();
    String getDescriptionWithExits();
    String getDescriptionWithWeapons();
    String getDescriptionWithEnemies();
    boolean getDescriptionLocked();
    void setLocked(boolean locked);
    String getDescriptionAll();
    Collection<Room> getExits();

    Room getExitByName(String name);
    Item getItemByName(String name);
    Enemy getEnemyByName(String enemyName);
    public List<Enemy> getEnemies();
    void registerExit(Room room);
    void registerItem(Item item);
    void unRegisterItem(Item item);
    void registerEnemy(Enemy enemy);
    void unregisterEnemy(Enemy enemy);
    Enemy getEnemyRandom();

    void setInCombat(boolean inCombat);
    boolean getIsInCombat();


}
