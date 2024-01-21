import java.util.Scanner;

public class DragonSlayer {

    private final Scanner sc;
    private Player player;
    private Room[] rooms;
    private int currentRoom;
    private Dragon currentDragon;
    private boolean playAgain;


    public DragonSlayer() {
        sc = new Scanner(System.in);
        rooms = new Room[5];
        playAgain = true;
    }

    public void play() {
        while (playAgain) {
            welcomePlayer();
            enteringRoom();
        }
    }

    public void welcomePlayer() {
        System.out.println("Welcome to Dragon Slayer!");
        System.out.print("What is your name, brave warrior? ");
        String name = sc.nextLine();
        player = new Player(name);
        currentRoom = 0;
        Dragon.resetStrongerDragonChance();
        rooms[currentRoom] = new Room();
        currentDragon = rooms[currentRoom].getCurrentDragon();
        Dragon.setPlayer(player);
        System.out.println("How to beat this game: Clear all 5 rooms. Each room has a dragon that you must slay.");
        System.out.println("******************************");
    }

    public void enteringRoom() {
        while (currentRoom != 5) {
            System.out.println("Entering room " + (currentRoom + 1) + "." + ConsoleUtility.RED + " The " + Room.roomNames[currentRoom] + ". . ." + ConsoleUtility.RESET);
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
            if (currentRoom != 5) {
                rooms[currentRoom] = new Room();
            }
        }
        playerWinsGame();

    }

    public void playerOption() {
        String option;
        boolean invalidAnswer;
        do {
            System.out.println("******************************");
            System.out.println(ConsoleUtility.BLACK + "Your turn." + ConsoleUtility.RESET);
            System.out.println("Do you want to: ");
            System.out.println("(1) " + ConsoleUtility.RED + "Attack the dragon" + ConsoleUtility.RESET);
            System.out.println("(2) " + ConsoleUtility.GREEN + "Use a health potion" + ConsoleUtility.RESET);
            System.out.println("(3) " + ConsoleUtility.YELLOW +"Search the room" + ConsoleUtility.RESET);
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
            playAgain();
        }

    }


    public void playerWinsGame() {
        System.out.println("Congratulations adventurer! You cleared all five rooms!");
        System.out.println("YOU WIN!");
        player.calculatingScore();
        player.addGoldToScore();
        player.addRemainingHealthToScore();
        player.setHighestScore();
        System.out.println("Your total score: " + ConsoleUtility.PURPLE + player.getScore() + ConsoleUtility.RESET);
        System.out.println("Top score: " + ConsoleUtility.CYAN + Player.getHighestScore() + ConsoleUtility.RESET + " (" + player.getName() + ")");
        playAgain();
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
             System.out.println("(4) Exit game");
             option = sc.nextLine();
             if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
                 invalidAnswer = true;
                 System.out.println("Invalid input.");
             }
         } while (invalidAnswer);

         switch (option) {
             case "1" -> System.out.println("Returning to game...");
             case "2" -> {
                 System.out.println("Resetting game...");
                 play();
             }
             case "3" -> System.out.println("Top score: " + ConsoleUtility.CYAN + Player.getHighestScore() + ConsoleUtility.RESET + " (" + player.getName() + ")");
             case "4" -> {
                 System.out.println("Thanks for playing!");
                 System.exit(0);
             }
         }
     }

     public void playAgain() {
        boolean invalidInput;
         do {
             invalidInput = false;
             System.out.println("Would you like to play again? (y/n)");
             String play = sc.nextLine();
             if (play.equals("y")) {
                 playAgain = true;
             } else if (play.equals("n")) {
                 System.out.println("Thank you for playing!");
                 playAgain = false;
                 System.exit(0);
             } else {
                 invalidInput = true;
                 System.out.println("Invalid input.");
             }
         } while (invalidInput);
         play();
     }

}
