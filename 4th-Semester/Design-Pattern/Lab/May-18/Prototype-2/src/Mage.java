
public class Mage extends GameCharacter {

    private int    mana;
    private int    maxMana;
    private String spellElement;

    public Mage(String name, int health, int attackPower, int defense,
                String weapon, String armor, int maxMana, String spellElement) {
        super(name, health, attackPower, defense, weapon, armor, "mage_anim_v3", "Mage");
        this.maxMana      = maxMana;
        this.mana         = maxMana;
        this.spellElement = spellElement;
        addSkill("Arcane Missile");
        addSkill("Teleport");
    }

    public void castSpell(String spellName, int manaCost) {
        if (mana >= manaCost) {
            mana -= manaCost;
            System.out.println("[" + name + "] casts " + spellElement
                    + " spell '" + spellName + "'! Mana: " + mana + "/" + maxMana);
        } else {
            System.out.println("[" + name + "] not enough mana to cast " + spellName + "!");
        }
    }

    public void manaRecharge(int amount) {
        mana = Math.min(mana + amount, maxMana);
        System.out.println("[" + name + "] recharges mana. Mana: " + mana + "/" + maxMana);
    }

    @Override
    public Mage clone() {
        Mage cloned = (Mage) super.clone();
        cloned.mana         = this.maxMana;
        cloned.maxMana      = this.maxMana;
        cloned.spellElement = this.spellElement;
        return cloned;
    }

    @Override
    public void displayCharacterInfo() {
        System.out.println("\n   MAGE");
        super.displayCharacterInfo();
        System.out.println("  Mana: " + mana + "/" + maxMana
                + " | Element: " + spellElement);
    }
}