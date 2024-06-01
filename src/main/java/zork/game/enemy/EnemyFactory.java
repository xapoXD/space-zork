package zork.game.enemy;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public class EnemyFactory {

    private static Map<String, EnemyType> enemyMap = new HashMap<>();

    public static EnemyType getEnemy(String type) {
        // If the enemy with the given name already exists, return it
        if (enemyMap.containsKey(type)) {
            return enemyMap.get(type);
        } else {
            // create a new enemy, store it, and return
            EnemyType newEnemy = new EnemyType(type);
            enemyMap.put(type, newEnemy);
            return newEnemy;
        }
    }

}
