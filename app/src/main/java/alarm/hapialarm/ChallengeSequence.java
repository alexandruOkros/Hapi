package alarm.hapialarm;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Alexandru on 29-Jan-17.
 */
public class ChallengeSequence {
    private Stack<Challenge> challenges = new Stack<>();
    private String temp = "";
    public ChallengeSequence() {
        // We create 4 challenges.
        // 1st is Happiness, Surprise or Sadness.
        // 2nd is Happiness, Surprise or Sadness.
        // 3rd is Surprise or Sadness.
        // Last is Happiness.
        // And no two consecutive are equal.
        String names[] = {"happiness", "surprise", "sadness"};
        Random rand = new Random();
        int min = 2;
        int max = 4;

        // 4th.
        challenges.push(new Challenge("happiness", rand.nextInt(max - min + 1) + min));

        // 3rd.1
        int id = rand.nextInt(2) + 1;
        challenges.push(new Challenge(names[id], rand.nextInt(max - min + 1) + min));
        temp += names[id];

        // 2nd.
        int last_id = id;
        do {
            id = rand.nextInt(3);
        } while(id == last_id);
        challenges.push(new Challenge(names[id], rand.nextInt(max - min + 1) + min));
        temp += names[id];

        // 1st.
        last_id = id;
        do {
            id = rand.nextInt(3);
        } while(id == last_id);
        challenges.push(new Challenge(names[id], rand.nextInt(max - min + 1) + min));
        temp += names[id];
    }

    // Returns x for current progress, -1 for challenge success and -2 for sequence success.
    public int validate(Score score) {
        if(challenges.empty()) {
            return -2;
        } else {
            Challenge next = challenges.peek();
            int result = next.validate(score);
            if(result == -1) {  // Challenge is done.
                challenges.pop();
                if(challenges.empty())
                    return -2;
                else
                    return -1;
            } else {
                return result;
            }
        }
    }

    public String getCurrentChallenge() {
        String m = "";
        switch (challenges.peek().getName()) {
            case "happiness":
                m = "Smile :)";
                break;
            case "sadness":
                m = "Look sad :(";
                break;
            case "surprise":
                m = "Be surprised :o";
                break;
        }

        return m;
    }

    public int getCurrentChallengeTotal() {
        return challenges.peek().getTotal();
    }
}
