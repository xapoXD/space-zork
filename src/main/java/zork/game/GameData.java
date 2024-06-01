package zork.game;

import zork.game.enemy.Enemy;
import zork.game.equipable.Inventory;

import java.util.List;

public interface GameData {

    boolean isFinished();
    void setFinished(boolean finished);
    Room getCurrentRoom();
    Inventory getInventory();
    Integer getPlayerHealth();
    void setPlayerHealth(Integer newHealth);
    List<Room> getRooms();
    void setCurrentRoom(Room currentRoom);
    void resetAll();
    void init();

    String initiateCombat(Enemy enemy);
    void checkCombat();
}
