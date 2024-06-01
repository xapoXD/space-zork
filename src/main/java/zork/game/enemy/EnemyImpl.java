package zork.game.enemy;

import zork.game.equipable.ItemImpl;

// Concrete Flyweight
public class EnemyImpl implements Enemy{

    private EnemyType type; // Intrinsic state (shared among all instances)

    private Integer health;
    private Integer damageLow;
    private Integer damageHigh;
    private ItemImpl dropItem;
    private boolean specialEnemy;

    public EnemyImpl(EnemyType type, Integer health, Integer damageLow, Integer damageHigh, ItemImpl dropItem, boolean specialEnemy) {
        this.type = type;
        this.health = health;
        this.damageLow = damageLow;
        this.damageHigh = damageHigh;
        this.dropItem = dropItem;
        this.specialEnemy = specialEnemy;
    }

    @Override
    public EnemyType getType() {
        return type;
    }

    public Integer getHealth() {
        return health;
    }

    @Override
    public void setHealt(Integer newHealth) {
        this.health = newHealth;
    }

    public Integer getDamageLow() {
        return damageLow;
    }

    public Integer getDamageHigh() {
        return damageHigh;
    }

    public Integer genAttack(){
        int genValue = (int)(Math.random() * (damageHigh - damageLow + 1)) + damageLow;
        return genValue;
    }

    @Override
    public ItemImpl getDropItem(){
        return this.dropItem;
    }

    @Override
    public boolean getSpecialEnemy(){
        return this.specialEnemy;
    }

}
