public class Pokemon {
    private String name;
    private double hp;
    private double attack;
    private int rarity;
    private String attribute;

    public Pokemon(String name, double hp, double attack,int rarity,String attribute) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.rarity = rarity;
        this.attribute = attribute;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public double getAttack() {
        return attack;
    }
    public int getRarity(){
        return rarity;
    }
    public String getAttribute(){
        return attribute;
    }

    // Setters
    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRarity(int rarity){
        this.rarity = rarity;
    }
    public void setAttribute(String attribute){
        this.attribute = attribute; 
    }

    @Override
    public String toString() {
        return "name :" + name + "\nhp :" + hp + "\nattack :" + attack + "\nrarity :" + rarity + "\nattribute :"
                + attribute ;
    }
    





}

