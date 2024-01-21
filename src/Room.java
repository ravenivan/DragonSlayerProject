public class Room {

    public static String[] roomNames = {"Dragon's Lair", "Roaring Abyss", "Fireforge Fathom", "Volcanic Bastion", "Dragon's Hoard Haven"};
    private Dragon[] dragons;
    private boolean searched;
    private boolean hasHealthPot;

    private boolean roomCleared;

    private int dragonCount;

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

    public Dragon[] getDragons() {
        return dragons;
    }

    public boolean isRoomCleared() {
        return roomCleared;
    }

    public void nextDragon() {
        dragonCount++;
        if (dragonCount == 3) {
            roomCleared = true;
        } else {
            System.out.println("A new dragon has spawned!");
        }
    }

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


    public Dragon getCurrentDragon() {
        return dragons[dragonCount];
    }



}
