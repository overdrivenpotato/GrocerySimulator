package underthehood;

import java.util.ArrayList;

/**
 * Created by marko on 02/01/14.
 */
public class Scenario {
    private String textPrompt;
    private String description;
    private ArrayList<Scenario> options;
    private Action complete;
    private ConverseGen convGen;

    public Scenario(String textPrompt, String description, Scenario... scenarios) {
        options = new ArrayList<Scenario>();
        this.textPrompt = textPrompt;
        this.description = description;

        for(int i = 0; i < scenarios.length; i++) {
            options.add(scenarios[i]);
        }
    }

    public Scenario(String textPrompt, String description, Action complete, Scenario... scenarios) {
        this(textPrompt, description, scenarios);
        this.complete = complete;
    }

    public Scenario(ConverseGen converseGen, String description, Scenario... scenarios) {
        this((String) null, description, scenarios);
        this.convGen = converseGen;
    }

    public String getTextPrompt() {
        if(complete != null)
            complete.onChoose();
        if(convGen != null)
            return convGen.getQuestion();
        return textPrompt;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Scenario> getOptions() {
        return options;
    }

    public boolean hasOptions() {
        return options.size() > 0;
    }

    public Scenario addOptions(Scenario... scenarios) {
        for(Scenario scenario : scenarios)
            options.add(scenario);
        return this;
    }
}
