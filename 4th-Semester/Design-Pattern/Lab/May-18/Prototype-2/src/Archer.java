public class Archer extends GameCharacter {

    private int    arrowCount;
    private double criticalHitChance;

    public Archer(String name, int health, int attackPower, int defense,
                  String weapon, String armor, int arrowCount, double criticalHitChance) {
        super(name, health, attackPower, defense, weapon, armor, "archer_anim_v1", "Archer");
        this.arrowCount       = arrowCount;
        this.criticalHitChance = criticalHitChance;
        addSkill("Rapid Fire");
        addSkill("Eagle Eye");
    }

    public void shootArrow(String target) {
        if (arrowCount > 0) {
            arrowCount--;
            boolean isCrit = Math.random() * 100 < criticalHitChance;
            int damage = isCrit ? attackPower * 2 : attackPower;
            System.out.println("[" + name + "] shoots " + target
                    + (isCrit ? " [CRITICAL HIT!]" : "")
                    + " for " + damage + " damage. Arrows left: " + arrowCount);
        } else {
            System.out.println("[" + name + "] has no arrows left!");
        }
    }

    public void reloadQuiver(int arrows) {
        arrowCount += arrows;
        System.out.println("[" + name + "] reloads quiver. Arrows: " + arrowCount);
    }

    @Override
    public Archer clone() {
        Archer cloned = (Archer) super.clone();
        cloned.arrowCount        = this.arrowCount;
        cloned.criticalHitChance = this.criticalHitChance;
        return cloned;
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("\n  ARCHER");
        super.displayCharacterInfo();
        System.out.println("  Arrows: " + arrowCount
                + " | Crit Chance: " + criticalHitChance + "%");
    }
}