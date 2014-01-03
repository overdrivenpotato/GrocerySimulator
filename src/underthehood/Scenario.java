package underthehood;

import java.util.ArrayList;

/**
 * Created by marko on 02/01/14.
 */
public class Scenario {
    private ActionString textPrompt;
    private ActionString description;
    private ArrayList<Scenario> options;
    private Action visible;

    public Scenario(Object textPrompt, Object description, Scenario... scenarios) {
        this.textPrompt = textPrompt instanceof String ? new ActionString((String) textPrompt) :
                textPrompt instanceof ActionString ? (ActionString) textPrompt : null;

        this.description = description instanceof String ? new ActionString((String) description) :
                description instanceof ActionString ? (ActionString) description : null;

        options = new ArrayList<Scenario>();
        for(Scenario scenario : scenarios)
            options.add(scenario);
    }

    public Scenario(Object textPrompt, Object description, Action visible, Scenario... scenarios) {
        this(textPrompt, description, scenarios);
        this.visible = visible;
    }

    public String getTextPrompt() {
        try {
            textPrompt.onEvent();
        } catch (Exception e) {}
        return textPrompt.toString();
    }

    public String getDescription() {
        return description.toString();
    }

    public ArrayList<Scenario> getOptions() {
        return options;
    }

    public boolean hasOptions() {
        return options.size() > 0;
    }

    public boolean isVisible()
    {
        try {
        return ((Boolean) visible.call()).booleanValue();
        } catch(Exception e)
        {
            return true;
        }
    }

    public Scenario addOptions(Scenario... scenarios) {
        for(Scenario scenario : scenarios)
            options.add(scenario);
        return this;
    }
}
