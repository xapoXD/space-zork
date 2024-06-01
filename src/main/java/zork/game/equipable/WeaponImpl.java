package zork.game.equipable;

public class WeaponImpl extends ItemImpl implements Weapon {

    private Integer attackLow;
    private Integer attackHigh;


    public WeaponImpl(String name, int usages, int attackLow, int attackHigh) {
        super(name,usages);
        this.attackLow = attackLow;
        this.attackHigh = attackHigh;
    }

    @Override
    public String getAttackRange() {
        return "[" + attackLow + " - " + attackHigh + "]";
    }

    @Override
    public Integer genAttack() {

        int genValue = (int)(Math.random() * (attackHigh - attackLow + 1)) + attackLow;

        return genValue;
    }


}
