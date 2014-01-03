package underthehood;

/**
 * Created by marko on 02/01/14.
 */
public class Player {
    private static Player player;
    private int grapeFruitCount;
    private int money;
    private boolean knife = false;
    private boolean shankedWoman;

    public Player()
    {
       grapeFruitCount = 0;
    }

    public int getMoney() {
        return money;
    }

    public int getCartValue() {
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

    public void buyCart() {
        money -= getCartValue();
    }


    public void setKnife(boolean knife) {
        this.knife = knife;
    }

    public Boolean hasKnife() {
        return Boolean.valueOf(knife);
    }

    public Boolean hasShanked() {
        return Boolean.valueOf(shankedWoman);
    }

    public void shankWoman()
    {
        shankedWoman = true;
    }

    public void addMoney(int money) {
        this.money += money;
    }
}
