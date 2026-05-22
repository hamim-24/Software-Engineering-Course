public class Assassin extends GameCharacter {

    private boolean isStealthed;
    private int     poisonDamage;

    public Assassin(String name, int health, int attackPower, int defense,
                    String weapon, String armor, int poisonDamage) {
        super(name, health, attackPower, defense, weapon, armor, "assassin_anim_v4", "Assassin");
        this.isStealthed = false;
        this.poisonDamage = poisonDamage;
        addSkill("Shadow Step");
        addSkill("Backstab");
    }

    public void activateStealth() {
        isStealthed = true;
        System.out.println("[" + name + "] vanishes into the shadows...");
    }

    public void poisonBlade(String target) {
        System.out.println("[" + name + "] poisons " + target
                + "'s blade! Poison damage: " + poisonDamage + " per turn.");
    }

    public void backstab(String target) {
        if (isStealthed) {
            int damage = attackPower * 3;
            isStealthed = false;
            System.out.println("[" + name + "] BACKSTABS " + target
                    + " from stealth for " + damage + " damage! Stealth broken.");
        } else {
            System.out.println("[" + name + "] must be stealthed to backstab!");
        }
    }

    @Override
    public Assassin clone() {
        Assassin cloned = (Assassin) super.clone();
        cloned.isStealthed  = false;
        cloned.poisonDamage = this.poisonDamage;
        return cloned;
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("\n   ASSASSIN");
        super.displayCharacterInfo();
        System.out.println("  Stealth: " + (isStealthed ? "Active" : "Inactive")
                + " | Poison Damage: " + poisonDamage + "/turn");
    }
}