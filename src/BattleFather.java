import java.util.Random;

public class
BattleFather {
    private String name;
    private int pwr;
    private int maxhp;
    private int piety;
    private int hp;
    private int heresy;

    private int initiative;

    static Random rand = new Random(System.currentTimeMillis());
    BattleFather(String name, int maxhp, int pwr, int piety, int heresy) {
        this.name = name;
        this.pwr = pwr;
        this.maxhp = maxhp;
        this.piety = piety;
        this.heresy = heresy;

        this.hp = maxhp;

        //this.initiative = initiative;
    }

    BattleFather(BattleFather object){
        this.name = object.name;
        this.pwr = object.pwr;
        this.maxhp = object.maxhp;
        this.piety = object.piety;
        this.heresy = object.heresy;
        this.hp = object.maxhp;
        //this.initiative = object.initiative;
    }

    /**
     * gives a plus or minus modifier, scales on heresy stat
     * @return plus or minus modifier
     */
    private int hMod() {
        if (this.heresy > 0) {
            return 2 * rand.nextInt(this.heresy) + 2 - this.heresy;
        }
        return 0;
    }

    int attack(BattleFather defender) {
        int dmg = this.pwr + hMod();
        if (dmg < 0) dmg = 0;
        defender.hp -= dmg;
        return dmg;
    }

    int heal(BattleFather target) {
        int heal = this.piety + hMod();
        if (heal < 0) heal = 0;
        target.hp += heal;
        return heal;
    }

    public String getName() {
        return name;
    }
    public int getPwr() {
        return this.pwr;
    }
    public int getMaxhp() {
        return this.maxhp;
    }
    public int getPiety() {
        return this.piety;
    }
    public int getHp() {
        return this.hp;
    }
    public int getHeresy() {
        return this.heresy;
    }
    public int getInitiative() { return this.initiative; }

    public void setPwr(int pwr) {
        this.pwr = pwr;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }
    public void setPiety(int piety) {
        this.piety = piety;
    }
    public void setHeresy(int heresy) {
        this.heresy = heresy;
    }
    public void setInitiative(int initiative) { this.initiative = initiative; }
}
