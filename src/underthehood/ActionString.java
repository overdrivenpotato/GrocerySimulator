package underthehood;

/**
 * Created by marko on 02/01/14.
 */
public class ActionString {
    private String text;
    private Action action;

    public ActionString(String text)
    {
        this.text = text;
    }

    public ActionString(String text, Action action)
    {
        this.text = text;
        this.action = action;
    }

    public String toString()
    {
        if(text == null || text.length() == 0)
            return (String) action.call();
        try {
            action.call();
        } catch(Exception e) {}

        return text;
    }

    public void onEvent() {
        action.call();
    }
}
