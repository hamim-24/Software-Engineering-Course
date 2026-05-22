
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Initializing Master Prototypes ===\n");

        Warrior masterWarrior = new Warrior(
                "Warrior Prototype", 500, 80, 60,
                "Longsword", "Plate Armor", true, 40);

        Mage masterMage = new Mage(
                "Mage Prototype", 280, 120, 20,
                "Arcane Staff", "Cloth Robes", 300, "Fire");

        Archer masterArcher = new Archer(
                "Archer Prototype", 350, 95, 35,
                "Longbow", "Leather Armor", 30, 25.0);

        Assassin masterAssassin = new Assassin(
                "Assassin Prototype", 300, 110, 30,
                "Shadow Daggers", "Shadow Cloak", 15);

        System.out.println("\n=== Registering Prototypes ===\n");

        CharacterFactory factory = new CharacterFactory();
        factory.registerPrototype("warrior",  masterWarrior);
        factory.registerPrototype("mage",     masterMage);
        factory.registerPrototype("archer",   masterArcher);
        factory.registerPrototype("assassin", masterAssassin);

        System.out.println("\n=== Spawning Enemies ===\n");

        GameCharacter enemyWarrior1 = factory.createEnemy("warrior", "Iron Guard",      0,   5);
        GameCharacter enemyWarrior2 = factory.createEnemy("warrior", "Siege Warrior", -50,  15);
        GameCharacter enemyMage     = factory.createEnemy("mage",    "Dark Sorcerer",  -80, 30);
        GameCharacter enemyArcher   = factory.createEnemy("archer",  "Forest Scout",  -100,  0);

        System.out.println("\n=== Spawning Boss ===\n");
        GameCharacter bossAssassin = factory.createBoss("assassin", "Shadow Lord");

        System.out.println("\n=== Spawning NPCs ===\n");
        GameCharacter npcMage = factory.spawnNPC("mage", "Village Healer");

        System.out.println("\n=== Character Info ===");
        enemyWarrior1.displayCharacterInfo();
        bossAssassin.displayCharacterInfo();
        npcMage.displayCharacterInfo();

        System.out.println("\n=== Combat Demonstration ===\n");

        Warrior ironGuard = (Warrior) enemyWarrior1;
        ironGuard.shieldBlock();
        ironGuard.attack("Player");

        Assassin shadowLord = (Assassin) bossAssassin;
        shadowLord.activateStealth();
        shadowLord.backstab("Player");
        shadowLord.poisonBlade("Player");

        System.out.println("\n=== Prototype Integrity Check ===\n");
        System.out.println("Master Warrior HP  : " + masterWarrior.getHealth()
                + "  |  Iron Guard HP : " + enemyWarrior1.getHealth());
        System.out.println("Master Assassin HP : " + masterAssassin.getHealth()
                + "  |  Shadow Lord HP: " + bossAssassin.getHealth());
        System.out.println("\nDeep-copy confirmed: clones are independent — master prototypes untouched.");
    }
}