public class Dragon {

    public static final String[] lootPossibility = {"gold", "sword upgrade", "dodgerate upgrade", "gain health", "nothing"};

    public static int strongerDragonChance = 0;

    public static int levelThreeDragonChance = 0;
    private int level;
    private int health;

    private String loot;

    private boolean slain;

    public Dragon() {
        health = 100;
        loot = lootPossibility[(int) (Math.random() * 5)];
        slain = false;
        assignLevel();
    }

    public boolean isSlain() {
        return slain;
    }

    public void assignLevel() {
        boolean strongDragon = ((int) (Math.random() * 100) + 1) <= strongerDragonChance;
        boolean levelThree = ((int) (Math.random() * 100) + 1) <= levelThreeDragonChance;
        if (strongDragon) {
            if (levelThree) {
                level = 3;
            } else {
                level = 2;
            }
        } else {
            level = 1;
        }
    }

    public void damageTaken(int damage) {
        health -= damage;
        checkSlain();
    }

    public int damageToPlayer() {
        return ((int) (Math.random() * 10) + 1) * level;
    }

    public void checkSlain() {
        if (health <= 0) {
            slain = true;
            System.out.println("This dragon has been killed.");
        }
    }

    public static void increaseStrongerDragonChance() {
        strongerDragonChance += 20;
        levelThreeDragonChance += 15;
    }

}
