package zork.game;

import zork.game.enemy.Enemy;
import zork.game.equipable.Item;

import java.util.*;

/**
 *  Class represents Room, e.g. space in our game. It contains exits and can form a map of Rooms
 */
public class RoomImpl implements Room {

    private String name;
    private String description;
    private boolean locked;
    private Map<String,Room> exits = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();
    private List<Enemy> enemies = new ArrayList<>();
    private boolean inCombat = false;

    public RoomImpl(String name, String description, boolean locked){
        this.name = name;
        this.description = description;
        this.locked = locked;
    }

    @Override
    public boolean getDescriptionLocked(){
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String getDescriptionAll(){
        return "You are in " + getName() + ", " + getDescription() + "\n" + getDescriptionWithExits() + "\n" + getDescriptionWithWeapons() + "\n" + getDescriptionWithEnemies();
    }


    //TODO: IMPLEMENT enemies
    @Override
    public void registerEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    @Override
    public void unregisterEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public Enemy getEnemyByName(String enemyName){

        for(Enemy enemy : enemies){
            if(enemyName.equals(enemy.getType().getName())){
                return enemy;
            }
        }
        return null;
    }

    @Override
    public Enemy getEnemyRandom(){

        if(!enemies.isEmpty()){
            return enemies.get(0);
        }
        return null;
    }

    /**
     *  Adds new exit to map
     */
    @Override
    public void registerExit(Room room){
        exits.put(room.getName(), room);
    }

    @Override
    public void registerItem(Item item) {
        items.put(item.getName(),item); //get name

    }

    @Override
    public void unRegisterItem(Item item) {
        items.remove(item.getName(), item); //get name
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     *  Method returns description of this room (from getDescription call)
     *  and should add possible exit names
     */
    @Override
    public String getDescriptionWithExits() {
        return "Rooms around: " + String.join(", ", this.exits.keySet());
    }

    @Override
    public String getDescriptionWithWeapons() {
        return "Items around: " + String.join(", ", this.items.keySet());
    }

    @Override
    public String getDescriptionWithEnemies() {

        String out = "";
        if(!enemies.isEmpty()) {
            out = "Enemies Around: ";
            for (int i = 0; i < enemies.size(); i++) {
                if(i == enemies.size() - 1){
                    out = out + enemies.get(i).getType().getName() + " with Health: " + enemies.get(i).getHealth();
                }else {
                    out = out + enemies.get(i).getType().getName() + " with Health: " + enemies.get(i).getHealth() + ", "; // + " with Health: " + enemy.getHealth() + ", Damage: [ " + enemy.getDamageLow() + " - " + enemy.getDamageHigh() + " ]";
                }
            }
        } else {
            out = "Enemies around: none";
        }
       return out;
       // return "Enemies around: " + String.join(", ", this.enemies.keySet());
    }

    /**
     *  Method returns description of this room
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *  Return unmodifiable view of our map
     */
    @Override
    public Collection<Room> getExits() {
        return Collections.unmodifiableCollection(exits.values());
    }

    /**
     *  Returns room based on entered room (exit) name
     */
    @Override
    public Room getExitByName(String name) {
        return exits.getOrDefault(name, null);
    }

    @Override
    public Item getItemByName(String name) {
        return items.getOrDefault(name,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomImpl room = (RoomImpl) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    @Override
    public boolean getIsInCombat() {
        return inCombat;
    }




}
