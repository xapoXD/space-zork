package zork.game.equipable;

public class Potion extends ItemImpl{

    public Potion(String name, int usages) {
        super(name, usages);
    }

    public Potion getPotion(){
        return this;
    }
}
