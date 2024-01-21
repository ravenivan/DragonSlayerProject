public class Player {

    /* Tracker variable */
    private static int highestScore = 0; // tracks the high score among players

    /* Instance variables */
    private final Sword playerSword;
    private final String name;
    private int health;
    private int gold;
    private boolean healthPot;
    private boolean dead;
    private int score;

    public Player(String name) {
        health = 100;
        playerSword = new Sword();
        healthPot = false;
        gold = 0;
        this.name = name;
        dead = false;
        score = 10000;
    }

    /* Getter methods */
    public boolean isDead() {
        return dead;
    }

    public int getScore() {
        return score;
    }

    public String getName() {return name;}

    public static int getHighestScore() {
        return highestScore;
    }

    /* Setter methods */
    public void addGoldToScore() {
        score += (gold * 10); // 1 gold = 10 extra points
    }

    public void addRemainingHealthToScore() {
        score += (health * 20); // 1 health = 20 extra points;
    }

    public void setHighestScore() {
        if (score > highestScore) {
            highestScore = score;
        }
    }

    /* Other instance methods */
    public int damageAmount() {
        int oneToThree = ((int) (Math.random() * 3) + 1);
        return oneToThree * playerSword.getAttackPower();
    }

    public void receiveHealthPot() {
        if (healthPot) {
            System.out.println("You already have a health pot in your inventory.");
            System.out.println("The potion you found disappears.");
        } else {
            healthPot = true;
            System.out.println("You now have a health pot in your inventory.");
        }
    }
    public void attackDragon(Dragon dragon) {
        int damageToDragon = damageAmount();
        System.out.println("You attack the dragon for " + damageToDragon + " damage!");
        dragon.damageTaken(damageToDragon);
        score -= 100; // Every attack takes away points. The least attacks you end the game with, the more points you have.
    }

    public void dragonAttack(int damageFromDragon) {
        health -= damageFromDragon;
    }

    public boolean dodge() {
        return ((int) (Math.random() * 100) + 1) <= playerSword.getDodgeRating();
    }

    public void checkDead() {
        if (health <= 0) {
            dead = true;
        }
    }

    /* Prints player status */
    public void playerStatus() {
        System.out.println("Your health: " + ConsoleUtility.GREEN + health + ConsoleUtility.RESET);
        System.out.println("Has health pot: " + healthPot);
        System.out.println("Your sword's attack power: " + ConsoleUtility.GREEN + playerSword.getAttackPower() + ConsoleUtility.RESET);
        System.out.println("Your sword's dodge rating: " + ConsoleUtility.GREEN + playerSword.getDodgeRating() + ConsoleUtility.RESET);
        System.out.println("Your gold: " + ConsoleUtility.YELLOW + gold + ConsoleUtility.RESET);
    }

    /* Player uses potion */
    public void usePot() {
        if (healthPot) {
            health += 50;
            if (health > 100) {
                health = 100;
            }
            healthPot = false;
            System.out.println("You used the health pot!");
        } else {
            System.out.println("You have no health pot to use.");
        }
    }

    /* Player receives dragonLoot, does nothing if dragon drops nothing */
    public void receiveDragonLoot(String loot) {
        switch (loot) {
            case "gold" -> gold += 50;
            case "a sword upgrade" -> playerSword.upgradeSword();
            case "a dodge rate upgrade" -> playerSword.upgradeDodgeRating();
            case "health" -> health += 30;
        }
    }

    /* Calculates player score at the end of the game if player wins */
    public void calculatingScore() {
        System.out.println("Calculating score...");
        System.out.println("Score from attacks: " + score);
        System.out.println("Score from gold: " + gold + " x 10 -> " + (gold * 10));
        System.out.println("Score from remaining health: " + health + " x 20 ->" + (health * 20));
    }
}
