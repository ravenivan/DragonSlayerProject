public class Room {

    private int level;
    private boolean searched;
    private boolean hasHealthPot;

    public Room() {
        level = (int) (Math.random() * 3) + 1;
        searched = false;
        if ((int) (Math.random() * 2) == 1) {
            hasHealthPot = true;
        } else {
            hasHealthPot = false;
        }
    }
}
