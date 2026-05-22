// ============================================================
//  Warrior.java  –  Concrete Prototype
// ============================================================
import java.util.ArrayList;

public class Warrior extends GameCharacter implements Cloneable {

    private boolean hasShield;
    private int     shieldBlockChance;

    public Warrior(String name, int health, int attackPower, int defense,
                   String weapon, String armor, boolean hasShield, int shieldBlockChance) {
        super(name, health, attackPower, defense, weapon, armor, "warrior_anim_v2", "Warrior");
        this.hasShield         = hasShield;
        this.shieldBlockChance = shieldBlockChance;
        addSkill("Power Strike");
        addSkill("Battle Cry");
    }

    public void equipSword(String swordName) {
        this.weapon = swordName;
        System.out.println("[" + name + "] equips sword: " + swordName);
    }

    public void shieldBlock() {
        if (hasShield) {
            System.out.println("[" + name + "] raises shield! Block chance: "
                    + shieldBlockChance + "%");
        } else {
            System.out.println("[" + name + "] has no shield equipped!");
        }
    }

    @Override
    public Warrior clone() {
        // Call Object.clone() directly to avoid recursion
        Warrior cloned        = (Warrior) super.clone();
        cloned.skillSet       = new ArrayList<>(this.skillSet);
        cloned.hasShield      = this.hasShield;
        cloned.shieldBlockChance = this.shieldBlockChance;
        return cloned;
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("\n  WARRIOR");
        super.displayCharacterInfo();
        System.out.println("  Shield: " + (hasShield ? "Yes" : "No")
                + " | Block Chance: " + shieldBlockChance + "%");
    }
}