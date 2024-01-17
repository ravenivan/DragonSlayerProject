import java.util.Scanner;

public class DragonSlayer {


    private Scanner sc;
    private Player player;
    private Room[] rooms;

    private int currentRoom;

    private Dragon currentDragon;


    public DragonSlayer() {
        sc = new Scanner(System.in);
        rooms = new Room[5];
        for (int i = 0; i < rooms.length; i++) {
            Room newRoom = new Room();
            rooms[i] = newRoom;
        }
        currentRoom = 0;
        currentDragon = rooms[currentRoom].getCurrentDragon(); // returns current dragon fighting
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
        Dragon.setPlayer(player);
        System.out.println("How to beat this game: Clear all 5 rooms. Each room has a dragon that you must slay.");
        System.out.println("******************************");
    }

    public void enteringRoom() {
        while (currentRoom != 5) {
            System.out.println("Entering room " + (currentRoom + 1) + " . . .");
            System.out.println("A dragon has spawned. ");
            while (!rooms[currentRoom].isRoomCleared()) {
                currentDragon = rooms[currentRoom].getCurrentDragon();
                showStats();
                playerOption();
                if (!rooms[currentRoom].isRoomCleared()) {
                    dragonTurn(currentDragon);
                }
            }
            currentRoom++;
            Dragon.increaseStrongerDragonChance();
        }
        playerWinsGame();

    }

    public void playerOption() {
        String option;
        boolean invalidAnswer = false;
        do {
            System.out.println("******************************");
            System.out.println("Your turn.");
            System.out.println("Do you want to: ");
            System.out.println("(1) Attack the dragon");
            System.out.println("(2) Use a health potion");
            System.out.println("(3) Search the room");
            System.out.println("(4) Main menu");
            option = sc.nextLine();
            if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
                invalidAnswer = true;
                System.out.println("Invalid input.");
            } else {
                invalidAnswer = false;
            }
            if (option.equals("4")) {
                mainMenu();
                invalidAnswer = true;
            }
            System.out.println("******************************"); // maybe will have issue
        } while (invalidAnswer);


        switch (option) {
            // fix this shi (maybe fixed)
            case "1":
                player.attackDragon(currentDragon);
                break;
            case "2":
                player.usePot();
                break;
            case "3":
                if (rooms[currentRoom].searchRoom()) {
                    player.receiveHealthPot();
                }
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


    public void playerWinsGame() {
        System.out.println("Congratulations adventurer! You cleared all five rooms!");
        System.out.println("YOU WIN!");
    }

     public void showStats() {
         currentDragon.dragonStatus();
         System.out.println("----------");
         player.playerStatus();
     }

     public void mainMenu() {
         System.out.println("******************************");
         System.out.println("Main menu: ");
         boolean invalidAnswer = false;
         String option;
         do {
             System.out.println("(1) Return to game");
             System.out.println("(2) Start new game");
             System.out.println("(3) View top score");
             option = sc.nextLine();
             if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
                 invalidAnswer = true;
                 System.out.println("Invalid input.");
             }
         } while (invalidAnswer);

         switch (option) {
             case "1":
                 System.out.println("Returning to game...");
                 break;
             case "2":
                 System.out.println("New game not added yet.");
                 break;
             case "3":
                 System.out.println("Top score not added yet.");
                 break;
         }
     }


}
