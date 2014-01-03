package underthehood;

/**
 * Created by marko on 02/01/14.
 */
public class Player {
    private static Player player;
    private int grapeFruitCount;

    public Player()
    {
       grapeFruitCount = 0;
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
