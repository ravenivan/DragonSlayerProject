public class Room {


    public static int currentRoom = 1;

    private Dragon[] dragons;
    private boolean searched;
    private boolean hasHealthPot;

    private boolean roomCleared;

    private int dragonCount = 0;

    public Room() {
        searched = false;
        hasHealthPot = (int) (Math.random() * 2) == 1;
        dragons = new Dragon[3];
        for (int i = 0; i < 3; i++) {
            Dragon dragon = new Dragon();
            dragons[i] = dragon;
        }
        roomCleared = false;
    }

    public Dragon[] getDragons() {
        return dragons;
    }

    public Dragon getCurrentDragon() {
        if (!dragons[0].isSlain()) {
            return dragons[0];
        } else if (!dragons[1].isSlain()) {
            return dragons[1];
        } else {
            return dragons[2];
        }
    }

    public void checkRoomClear() {
        if (dragons[2].isSlain()) {
            roomCleared = true;
            currentRoom++;
            dragonCount++;
        }
    }

    public int getDragonCount() {
        return dragonCount;
    }


}
