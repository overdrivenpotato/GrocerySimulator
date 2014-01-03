package underthehood;

/**
 * Created by marko on 02/01/14.
 */
public class Player {
    private static Player player;
    private int grapeFruitCount;
    private int money;

    public Player()
    {
       grapeFruitCount = 0;
    }

    public int getMoney() {
        return money;
    }

    public Object getCartValue() {
        return grapeFruitCount * 100;
    }

    public Player addGrapefruit()
    {
        grapeFruitCount++;
        return this;
    }

    public static Player player()
    {
        return player;
    }

    static
    {
        player = new Player();
    }
}
