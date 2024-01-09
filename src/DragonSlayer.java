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

    }


}
