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

    public int damageAmount() {
        int oneToTen = ((int) (Math.random() * 10) + 1);
        return oneToTen * playerSword.getAttackPower();
    }
    public void attackDragon(Dragon dragon) {
        dragon.damageTaken(damageAmount());
    }

    public void dragonAttack(Dragon dragon) {
        health -= dragon.damageToPlayer();
    }

    public boolean dodge() {
        return ((int) (Math.random() * 100) + 1) <= playerSword.getDodgeRating();
    }

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

    public void checkDead() {
        if (health <= 0) {
            dead = true;
        }
    }

    public void playerStatus() {
        String status = "Your health: " + health;
        status += "\nHas health pot: " + healthPot;
        status += "\nYour sword's attack power: " + playerSword.getAttackPower();
        status += "\nYour sword's dodge rating: " + playerSword.getDodgeRating();
    }


}
