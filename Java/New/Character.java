package textrpg;

public class Character {
    int health;
    int maxHealth;
    int strength;
    int defense;
    public int speed;
    int level;
    int exp;
    
    public Character(int x, int y, int z, int w, int lvl){
        maxHealth = x + lvl;
        health = maxHealth;
        strength = y + lvl;
        defense = z + lvl;
        level = lvl;
        speed = w;
    }
    
    public void expGain (int x){
        exp += x;
        System.out.println("Gained " + x + " exp");
        while (exp >= (10 + (2 * level))) {
            maxHealth++;
            health = maxHealth;
            level++;
            strength++;
            defense++;

            exp -= (10 + (2 * level));
            System.out.println("\nLevel up \nHealth, strength, defense, and speed increased");
        }
    }
    
    public void Stats() {
        System.out.println(health + " health out of " + maxHealth + ",\n"
                         + defense + " defense," + "\n"
                         + strength + " strength" + "\n"
                         + speed + " speed");
    }

    public void EnemyStats() {
        System.out.println(health + " health out of " + maxHealth + ",\n"
                                          + defense + " defense," + "\n"
                                          + strength + " strength" + "\n"
                                          + speed + " speed");
    }
}
