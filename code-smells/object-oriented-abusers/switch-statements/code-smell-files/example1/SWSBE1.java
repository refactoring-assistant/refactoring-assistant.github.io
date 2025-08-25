class CommandParser {

    private void attack(int damage) {
        System.out.println("You did " + damage + " points of damage");
    }

    private void defend(int incomingDamage) {
        System.out.println("You were hit with " + incomingDamage + " points of damage");
    }

    private void heal(int healPoints) {
        System.out.println("You healed yourself of " + healPoints + " points");
    }

    private void magicAttack(int magicDamage) {
        System.out.println("You did " + magicDamage + " points of magic damage");
    }

    private void provoke() {
        System.out.println("You provoked the enemy");
    }

    private void meditate(int manaPoints) {
        System.out.println("You meditated and gained back " + manaPoints + " points of mana");
    }

    public void start(String[] args) {
        String command = args[0];
        int points = 0;
        if (args.length > 1) {
            points = Integer.parseInt(args[1]);
        }
        switch (command) {
            case "attack":
                attack(points);
                break;
            case "defend":
                defend(points);
                break;
            case "heal":
                heal(points);
                break;
            case "magic":
                magicAttack(points);
                break;
            case "provoke":
                provoke();
                break;
            case "meditate":
                meditate(points);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

}

public class SWSBE1 {
    public static void main(String[] args) {
        CommandParser cp = new CommandParser();
        String[] commands = new String[] { "attack", "10" };
        cp.start(commands);
    }
}
