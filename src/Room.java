public class Room {


    private Dragon[] dragons;
    private boolean searched;
    private boolean hasHealthPot;

    public Room() {
        searched = false;
        hasHealthPot = (int) (Math.random() * 2) == 1;
        dragons = new Dragon[3];
        for (int i = 0; i < 3; i++) {
            Dragon dragon = new Dragon();
            dragons[i] = dragon;
        }
    }

    public Dragon[] getDragons() {
        return dragons;
    }


}
