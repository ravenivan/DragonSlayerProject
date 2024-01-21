public class Room {

    public static String[] roomNames = {"Dragon's Lair", "Roaring Abyss", "Fireforge Fathom", "Volcanic Bastion", "Dragon's Hoard Haven"};
    private final Dragon[] dragons; // array of dragons in this room
    private final boolean hasHealthPot; // checks if room has a health pot to be searched
    private boolean searched; // checks if room treasure has been searched

    private boolean roomCleared; // checks if room has been cleared

    private int dragonCount; // keeps track of which dragon the player is fighting this room (0-2)

    public Room() {
        searched = false;
        hasHealthPot = (int) (Math.random() * 2) == 1;
        dragons = new Dragon[3];
        for (int i = 0; i < 3; i++) {
            Dragon dragon = new Dragon(this);
            dragons[i] = dragon;
        }
        roomCleared = false;
        dragonCount = 0;
    }

    /* Getter methods */
    public boolean isRoomCleared() {
        return roomCleared;
    }

    public Dragon getCurrentDragon() {
        return dragons[dragonCount];
    }

    /* Moves onto the next dragon in the room after current dragon is killed. If all dragons in the room are killed, the room is cleared. */
    public void nextDragon() {
        dragonCount++;
        if (dragonCount == 3) {
            roomCleared = true;
        } else {
            System.out.println("A new dragon has spawned!");
        }
    }

    /* Searches room */
    public boolean searchRoom() {
        if (!searched) {
            searched = true;
            if (hasHealthPot) {
                System.out.println("You find a health potion!");
                return true;
            } else {
                System.out.println("You search the whole room, but there is nothing.");
                return false;
            }
        } else {
            System.out.println("You already searched this room!");
            return false;
        }
    }
}
