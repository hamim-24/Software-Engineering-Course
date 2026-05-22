import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    private Map<String, GameCharacter> registry = new HashMap<>();

    public void registerPrototype(String key, GameCharacter prototype) {
        registry.put(key, prototype);
        System.out.println("[CharacterFactory] Registered prototype: " + key);
    }

    private GameCharacter clonePrototype(String key) {
        GameCharacter proto = registry.get(key);
        if (proto == null) throw new RuntimeException("No prototype registered for: " + key);
        return proto.clone();
    }

    public GameCharacter createEnemy(String prototypeKey, String enemyName,
                                     int healthModifier, int attackModifier) {
        GameCharacter enemy = clonePrototype(prototypeKey);
        enemy.setName(enemyName);
        enemy.setHealth(enemy.getHealth() + healthModifier);
        enemy.setAttackPower(enemy.getAttackPower() + attackModifier);
        System.out.println("[CharacterFactory] Enemy spawned: " + enemyName);
        return enemy;
    }

    public GameCharacter createBoss(String prototypeKey, String bossName) {
        GameCharacter boss = clonePrototype(prototypeKey);
        boss.setName(bossName);
        boss.setHealth(boss.getHealth() * 3);
        boss.setAttackPower((int)(boss.getAttackPower() * 2.5));
        boss.setDefense(boss.getDefense() * 2);
        boss.addSkill("Berserker Rage");
        boss.addSkill("Enrage");
        System.out.println("[CharacterFactory] BOSS spawned: " + bossName);
        return boss;
    }

    public GameCharacter spawnNPC(String prototypeKey, String npcName) {
        GameCharacter npc = clonePrototype(prototypeKey);
        npc.setName(npcName);
        npc.setAttackPower(0);
        npc.setHealth(50);
        System.out.println("[CharacterFactory] NPC spawned: " + npcName);
        return npc;
    }
}