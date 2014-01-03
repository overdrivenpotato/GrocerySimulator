package underthehood;

import java.util.Random;

/**
 * Created by marko on 02/01/14.
 */
public class ConverseGen {
    private String[] pos = {
        "Nice weather we're having, huh?",
        "How about those kids and their yolo swags, eh?",
        "I like the mustard stain on your shirt.",
        "Wow! You're almost as ugly as Charlie!",
        "That beard must make it difficult for you to get a table for one at chuckie cheese's!",
        "How about that Rob Ford character, eh? Real nice honest guy."
    };

    private Random rand = new Random();

    public String getQuestion()
    {
        if(rand.nextInt(100) == 55)
        {
            return "DAAAAYUMMM NIGGA, REEEEYUL TRAYPO SHITTT YOLO SWAG HOLLA AT MA ASSY NIGGA";
        }
        return pos[rand.nextInt(pos.length - 1)];
    }
}
