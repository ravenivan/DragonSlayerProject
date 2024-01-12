public class Player {
    private Sword playerSword;
    private int health;
    private int gold;
    private boolean healthPot;
    private String name;

    private boolean dead;


    public Player(String name) {
        health = 100;
        playerSword = new Sword();
        healthPot = false;
        gold = 0;
        this.name = name;
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

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
    }

    public void dragonAttack(int damageFromDragon) {
        health -= damageFromDragon;
    }

    public boolean dodge() {
        return ((int) (Math.random() * 100) + 1) <= playerSword.getDodgeRating();
    }

    public boolean usePot() {
        if (healthPot) {
            health += 50;
            if (health > 100) {
                health = 100;
            }
            healthPot = false;
            System.out.println("You used the health pot!");
            return true;
        } else {
            System.out.println("You have no health pot to use.");
            return false;
        }
    }

    public void checkDead() {
        if (health <= 0) {
            dead = true;
        }
    }

    public void playerStatus() {
        System.out.println("Your health: " + health);
        System.out.println("Has health pot: " + healthPot);
        System.out.println("Your sword's attack power: " + playerSword.getAttackPower());
        System.out.println("Your sword's dodge rating: " + playerSword.getDodgeRating());
    }


}
