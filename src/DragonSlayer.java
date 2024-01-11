import java.util.Scanner;

public class DragonSlayer {


    private Scanner sc;
    private Player player;
    private Room[] rooms;


    public DragonSlayer() {
        sc = new Scanner(System.in);
        rooms = new Room[5];
        for (int i = 0; i < rooms.length; i++) {
            Room newRoom = new Room();
            rooms[i] = newRoom;
        }
    }

    public void play() {
        welcomePlayer();
        enteringRoom();
    }

    public void welcomePlayer() {
        System.out.println("Welcome to Dragon Slayer!");
        System.out.print("What is your name, brave warrior? ");
        String name = sc.nextLine();
        player = new Player(name);
        System.out.println("How to beat this game: Clear all 5 rooms. Each room has a dragon that you must slay.");
        System.out.println("******************************");
    }

    public void enteringRoom() {
        System.out.println("Entering room " + (Room.currentRoom) + " . . .");
        System.out.println("A dragon has spawned. ");


    }

    public void playerOption(Room room) {
        Dragon currentDragon =
        boolean invalidAnswer = false;
        String option;
        do {
            System.out.println("******************************");
            System.out.println("Your turn.");
            System.out.println("Do you want to: ");
            System.out.println("(1) Attack the dragon");
            System.out.println("(2) Use a health potion");
            option = sc.nextLine();
            if (!option.equals("1") && !option.equals("2")) {
                invalidAnswer = true;
                System.out.println("Invalid input.");
            }
        } while (invalidAnswer);


        switch (option) {
            // fix this shi
            case "1":
                player.attackDragon(rooms[Room.currentRoom - 1].getDragons()[rooms[Room.currentRoom -1 ].getDragonCount()]);
                break;
            case "2":
                player.usePot();
                break;
        }


    }

    public void dragonTurn(Dragon dragon) {
        int damageToPlayer = dragon.damageToPlayer();
        System.out.println("Dragon's turn.");
        System.out.println("The dragon attacks you.");
        if (player.dodge()) {
            System.out.println("You dodged the dragon's attack!");
        } else {
            System.out.println("The dragon dealt " + damageToPlayer + " to you.");
            player.dragonAttack(damageToPlayer);
            player.checkDead();
        }

        if (player.isDead()) {
            System.out.println("Game over. You died.");
            System.exit(0);
        }

    }


}
