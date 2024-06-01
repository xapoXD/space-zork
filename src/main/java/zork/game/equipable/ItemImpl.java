package zork.game.equipable;

public class ItemImpl implements Item{

    private String name;

    private int usages; //kolikrat se da pouzit

    public ItemImpl(String name, int usages) {
        this.name = name;
        this.usages = usages;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return usages;
    }

}
