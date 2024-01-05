public class Dragon {

    public static final String[] lootPossibility = {"gold", "sword upgrade", "dodgerate upgrade", "gain health", "nothing"}
    private int level;
    private int health;

    private String loot;

    public Dragon() {
        health = 100;
        loot = lootPossibility[(int) (Math.random() * 5)];
    }

    public void damageTaken(int damage) {
        health -= damage;
    }

    public void dragonAttack(int damage) {

    }
}
