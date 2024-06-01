package zork.game.enemy;

import zork.game.equipable.ItemImpl;

// Builder interface
public interface EnemyBuilder {
    EnemyBuilder setName(String name);
    EnemyBuilder setHealth(int health);
    EnemyBuilder setDamageLow(int damageLow);
    EnemyBuilder setDamageHigh(int damageHigh);

    EnemyBuilder setItem(ItemImpl item);
    EnemyBuilder setSpecialEnemy(boolean bool);

    Enemy build();
}
