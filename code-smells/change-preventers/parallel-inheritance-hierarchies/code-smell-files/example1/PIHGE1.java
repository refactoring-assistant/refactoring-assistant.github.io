interface Enemy {
    public void attack();

    public void defend();
}

class Vampire implements Enemy {
    private Weapon shotgun;
    private int defense;

    public Vampire(Weapon shotgun, int defense) {
        this.shotgun = shotgun;
        this.defense = defense;
    }

    @Override
    public void attack() {
        if (shotgun.inspectHealth() > 0) {
            System.out.println("Vampire attacks. Damage done: " + this.shotgun.getDamage());
        } else {
            System.out.println("Weapon broken. Vampire can't repair weapon");
        }
    }

    @Override
    public void defend() {
        System.out.println("Blocking attack with defense: " + (this.defense + 10));
    }

}

class Ghoul implements Enemy {
    private Weapon bazooka;
    private int defense;

    public Ghoul(Weapon bazooka, int defense) {
        this.bazooka = bazooka;
        this.defense = defense;
    }

    @Override
    public void attack() {
        if (bazooka.inspectHealth() > 0) {
            System.out.println("Ghoul attacks. Damage done: " + this.bazooka.getDamage());
        } else {
            System.out.println("Weapon broken. Repairing...");
            this.bazooka.repair();
        }
    }

    @Override
    public void defend() {
        System.out.println("Blocking attack with defense: " + (this.defense + 20));
    }

}

interface Weapon {
    public int getDamage();

    public void repair();

    public int inspectHealth();
}

class BasicWeapon implements Weapon {
    private int damage;
    private int health;

    public BasicWeapon(int damage, int health) {
        this.damage = damage;
        this.health = health;
    }

    @Override
    public int getDamage() {
        this.health -= 10;
        return damage;
    }

    @Override
    public void repair() {
        this.health = 100;
    }

    @Override
    public int inspectHealth() {
        return this.health;
    }

}

public class PIHGE1 {
    public static void main(String[] args) {
        Weapon vampireShotgun = new BasicWeapon(20, 50);
        Weapon ghoulBazooka = new BasicWeapon(30, 200);

        Enemy vampire = new Vampire(vampireShotgun, 15);
        Enemy ghoul = new Ghoul(ghoulBazooka, 5);

        vampire.attack();
        ghoul.defend();
        vampire.defend();
        ghoul.attack();
        ghoul.attack();
    }
}
