abstract class Action {

    public abstract String getActionName();

    public abstract void execute(CommandParser parser, int points);

    public static Action createAction(String commandName) {
        switch (commandName) {
            case "attack":
                return new AttackAction();
            case "defend":
                return new DefendAction();
            case "heal":
                return new HealAction();
            case "magic":
                return new MagicAttackAction();
            case "provoke":
                return new ProvokeAction();
            case "meditate":
                return new MeditateAction();
            default:
                throw new UnsupportedOperationException();
        }
    }
}

class AttackAction extends Action {

    @Override
    public String getActionName() {
        return "attack";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.attack(points);
    }
}

class DefendAction extends Action {

    @Override
    public String getActionName() {
        return "defend";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.defend(points);
    }

}

class HealAction extends Action {

    @Override
    public String getActionName() {
        return "heal";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.heal(points);
    }

}

class MagicAttackAction extends Action {

    @Override
    public String getActionName() {
        return "magic";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.magicAttack(points);
    }

}

class ProvokeAction extends Action {

    @Override
    public String getActionName() {
        return "provoke";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.provoke();
    }

}

class MeditateAction extends Action {

    @Override
    public String getActionName() {
        return "meditate";
    }

    @Override
    public void execute(CommandParser parser, int points) {
        parser.meditate(points);
    }

}

class CommandParser {

    protected void attack(int damage) {
        System.out.println("You did " + damage + " points of damage");
    }

    protected void defend(int incomingDamage) {
        System.out.println("You were hit with " + incomingDamage + " points of damage");
    }

    protected void heal(int healPoints) {
        System.out.println("You healed yourself of " + healPoints + " points");
    }

    protected void magicAttack(int magicDamage) {
        System.out.println("You did " + magicDamage + " points of magic damage");
    }

    protected void provoke() {
        System.out.println("You provoked the enemy");
    }

    protected void meditate(int manaPoints) {
        System.out.println("You meditated and gained back " + manaPoints + " points of mana");
    }

    public void start(String[] args) {
        String command = args[0];
        int points = 0;
        if (args.length > 1) {
            points = Integer.parseInt(args[1]);
        }
        
        Action action = Action.createAction(command);
        action.execute(this, points);
    }
}

public class SWSGE1 {
    public static void main(String[] args) {
        CommandParser cp = new CommandParser();
        String[] commands = new String[] { "attack", "10" };
        cp.start(commands);
    }
}
