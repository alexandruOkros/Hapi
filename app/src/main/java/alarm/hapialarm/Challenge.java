package alarm.hapialarm;

/**
 * Created by Alexandru on 29-Jan-17.
 */
public class Challenge {
    private String name = null;
    private int count = 0;
    private int n_challenge = 0;

    // Name is "surprise", "happiness" or "sadness".
    public Challenge(String name, int n) {
        this.name = name;
        this.n_challenge = n;
    }

    // Returns x for current progress, -1 for challenge success.
    public int validate(Score score) {
        switch(name) {
            case "surprise":
                if(score.isSurprise())
                    count += 1;
                else
                    reset();
                break;
            case "happiness":
                if(score.isHappiness())
                    count += 1;
                else
                    reset();
                break;
            case "sadness":
                if(score.isSadness())
                    count += 1;
                else
                    reset();
                break;
        }

        if(count >= n_challenge)
            return -1;
        else
            return count;
    }

    private void reset() {
        count = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return n_challenge;
    }
}
