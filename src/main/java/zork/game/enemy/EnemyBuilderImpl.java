package zork.game.enemy;

import zork.game.equipable.ItemImpl;
import zork.ui.CommandLineUi;

public class EnemyBuilderImpl implements EnemyBuilder{

    private EnemyType type;
    private Integer health;
    private Integer damageLow;
    private Integer damageHigh;
    private ItemImpl dropItem = null;
    private boolean specialEnemy = false;

    public EnemyBuilderImpl(String type) {

        this.type = EnemyFactory.getEnemy(type);
    }

    @Override
    public EnemyBuilder setName(String name) {
        this.type.name = name;
        return this;
    }

    @Override
    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public EnemyBuilder setDamageLow(int damage) {
        this.damageLow = damage;
        return this;
    }

    @Override
    public EnemyBuilder setDamageHigh(int damage) {
        this.damageHigh = damage;
        return this;
    }


    @Override
    public EnemyBuilder setItem(ItemImpl item) {
        this.dropItem = item;
        return this;
    }

    @Override
    public EnemyBuilder setSpecialEnemy(boolean bool) {
        this.specialEnemy = bool;
        return this;
    }


    @Override
    public Enemy build() {
        // Flyweight pattern
        Enemy simpleEnemy = new EnemyImpl(type,health,damageLow,damageHigh,dropItem, specialEnemy);
        if(dropItem != null){
            CommandLineUi.log.info("Building " + type.getName() + " with Health: " + health + ", Damage: [ " + damageLow + " - " + damageHigh + " ]" + " with item: " + dropItem.getName());
        }else{
            CommandLineUi.log.info("Building " + type.getName() + " with Health: " + health + ", Damage: [ " + damageLow + " - " + damageHigh + " ]");
        }

        return simpleEnemy;
    }


}
