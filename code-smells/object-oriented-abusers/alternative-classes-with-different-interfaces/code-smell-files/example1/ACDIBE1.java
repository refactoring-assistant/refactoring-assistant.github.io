interface Persona {

    public int attackWithMagic();

    public void block(int incomingDamage);

    public int attackWithMelee();

    public void restoreHealth();
}

interface Avatar {
    public int useMagic();

    public void defend(int opponentAttack);

    public int useMelee();

    public void heal();
}

class SimplePersona implements Persona {

    private String name;
    private int health;
    private int meleeDamage;
    private int magicDamage;
    private int defense;

    public SimplePersona(String name, int health, int meleeDamage, int magicDamage, int defense) {
        this.name = name;
        this.health = health;
        this.meleeDamage = meleeDamage;
        this.magicDamage = magicDamage;
        this.defense = defense;
    }

    @Override
    public int attackWithMagic() {
        System.out.println(this.name + " used Magic and caused " + this.magicDamage + " damage!");
        return this.magicDamage;
    }

    @Override
    public void block(int incomingDamage) {
        int finalDamage = this.defense * incomingDamage / 100;
        System.out.println(this.name + " defended and suffered " + finalDamage + " damage!");
        this.health -= finalDamage;
    }

    @Override
    public int attackWithMelee() {
        System.out.println(this.name + " used Melee and caused " + this.meleeDamage + " damage!");
        return this.meleeDamage;
    }

    @Override
    public void restoreHealth() {
        System.out.println(this.name + " healed themselves " + this.defense / 100 + " points!");
        System.out.println("New health: " + this.health);
        this.health += this.defense / 100;
    }

}

class SimpleAvatar implements Avatar {

    private String avatarName;
    private int healthPoints;
    private int meleeAttackPoints;
    private int magicAttackPoints;
    private int defensePoints;

    public SimpleAvatar(String avatarName, int healthPoints, int meleeAttackPoints, int magicAttackPoints,
            int defensePoints) {
        this.avatarName = avatarName;
        this.healthPoints = healthPoints;
        this.meleeAttackPoints = meleeAttackPoints;
        this.magicAttackPoints = magicAttackPoints;
        this.defensePoints = defensePoints;
    }

    @Override
    public int useMagic() {
        System.out.println(this.avatarName + " used Magic and caused " + this.magicAttackPoints + " damage!");
        return this.magicAttackPoints;
    }

    @Override
    public void defend(int opponentAttack) {
        int damageSuffered = this.defensePoints * opponentAttack / 100;
        this.healthPoints -= damageSuffered;
        System.out.println(this.avatarName + " defended and suffered " + damageSuffered + " damage!");
    }

    @Override
    public int useMelee() {
        System.out.println(this.avatarName + " used Melee and caused " + this.meleeAttackPoints + " damage!");
        return this.meleeAttackPoints;

    }

    @Override
    public void heal() {
        System.out.println(this.avatarName + " healed themselves " + this.defensePoints / 100 + " points!");
        System.out.println("New health: " + this.healthPoints);
        this.healthPoints += this.defensePoints / 100;
    }
}

public class ACDIBE1 {

    public static void main(String[] args) {
        Avatar joseph = new SimpleAvatar("Joseph", 10, 10, 10, 10);
        Persona drake = new SimplePersona("Drake", 10, 10, 10, 10);
        joseph.defend(10);
        drake.block(10);

    }

}