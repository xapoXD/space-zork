package zork.game.equipable;

public class Key extends ItemImpl{

    public Key(String name, int usages) {
        super(name, usages);
    }

    public Key getKey(){
        return this;
    }
}
