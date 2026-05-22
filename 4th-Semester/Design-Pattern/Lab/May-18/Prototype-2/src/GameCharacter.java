
import java.util.ArrayList;
import java.util.List;

public abstract class GameCharacter implements Cloneable {

    protected String       name;
    protected int          health;
    protected int          attackPower;
    protected int          defense;
    protected String       weapon;
    protected String       armor;
    protected List<String> skillSet;
    protected String       animationProfile;
    protected String       role;

    public GameCharacter(String name, int health, int attackPower, int defense,
                         String weapon, String armor,
                         String animationProfile, String role) {
        this.name             = name;
        this.health           = health;
        this.attackPower      = attackPower;
        this.defense          = defense;
        this.weapon           = weapon;
        this.armor            = armor;
        this.animationProfile = animationProfile;
        this.role             = role;
        this.skillSet         = new ArrayList<>();
    }

    @Override
    public GameCharacter clone() {
        try {
            GameCharacter cloned = (GameCharacter) super.clone();
            cloned.skillSet = new ArrayList<>(this.skillSet);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed: " + e.getMessage());
        }
    }

    public void attack(String target) {
        System.out.println("[" + name + "] attacks " + target
                + " with " + weapon + " for " + attackPower + " damage!");
    }

    public void move(String direction) {
        System.out.println("[" + name + "] moves " + direction
                + " using animation: " + animationProfile);
    }

    public void addSkill(String skill) { skillSet.add(skill); }
    public void removeSkill(String skill) { skillSet.remove(skill); }

    public void displayCharacterInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf("│  %-10s  %-28s│%n", "Name:",   name);
        System.out.printf("│  %-10s  %-28s│%n", "Role:",   role);
        System.out.printf("│  %-10s  %-28s│%n", "HP:",     health);
        System.out.printf("│  %-10s  %-28s│%n", "Attack:", attackPower);
        System.out.printf("│  %-10s  %-28s│%n", "Defense:", defense);
        System.out.printf("│  %-10s  %-28s│%n", "Weapon:", weapon);
        System.out.printf("│  %-10s  %-28s│%n", "Armor:",  armor);
        System.out.printf("│  %-10s  %-28s│%n", "Anim:",   animationProfile);
        System.out.printf("│  %-10s  %-28s│%n", "Skills:", skillSet);
        System.out.println("└─────────────────────────────────────────┘");
    }

    public void setName(String name)           { this.name = name; }
    public void setHealth(int health)          { this.health = health; }
    public void setAttackPower(int atk)        { this.attackPower = atk; }
    public void setDefense(int defense)        { this.defense = defense; }
    public void setWeapon(String weapon)       { this.weapon = weapon; }
    public void setArmor(String armor)         { this.armor = armor; }

    public String getName()   { return name; }
    public int    getHealth() { return health; }
    public String getRole()   { return role; }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }
}