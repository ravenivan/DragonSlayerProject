public class Dragon {

    public static final String[] lootPossibility = {"gold", "a sword upgrade", "a dodge rate upgrade", "health", "nothing"};

    public static int strongerDragonChance = 0;

    public static int levelThreeDragonChance = 0;

    public static Player player;
    private int level;
    private int health;

    private String loot;

    private boolean slain;

    private Room dragonRoom;

    public Dragon(Room room) {
        health = 100;
        loot = lootPossibility[(int) (Math.random() * 5)];
        slain = false;
        assignLevel();
        dragonRoom = room;
    }

    public static void setPlayer(Player p) {
        player = p;
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
            player.receiveDragonLoot(dropLoot());
            dragonRoom.nextDragon();
        }
    }

    public void dragonStatus() {
        System.out.println("Dragon health: " + ConsoleUtility.RED + health + ConsoleUtility.RESET);
        System.out.println("Dragon level: " + ConsoleUtility.PURPLE + level + ConsoleUtility.RESET);
    }

    /* Drops dragon loot after slain */
    public String dropLoot() {
        System.out.println("The dragon drops: " + loot);
        System.out.println("******************************");
        return loot;
    }

    /* Public static methods */
    public static void increaseStrongerDragonChance() {
        strongerDragonChance += 20;
        levelThreeDragonChance += 15;
    }

    public static void resetStrongerDragonChance() {
        strongerDragonChance = 0;
        levelThreeDragonChance = 0;
    }

}
