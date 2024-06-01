package zork.game.enemy;

import zork.game.equipable.ItemImpl;

// Flyweight interface
public interface Enemy {

    EnemyType getType();
    Integer getHealth();
    Integer getDamageLow();
    Integer getDamageHigh();
    Integer genAttack();

    void setHealt(Integer newHealth);
    ItemImpl getDropItem();
    boolean getSpecialEnemy();
}
