public class Player {
    private Sword playerSword;
    private int health;
    private int gold;
    private boolean healthPot;
    private String name;

    public Player(String name) {
        health = 100;
        playerSword = new Sword();
        healthPot = false;
        gold = 0;
        this.name = name;
    }

    public void attackDragon() {

    }


}
