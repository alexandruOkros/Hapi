package alarm.hapialarm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alexandru on 28-Jan-17.
 */
public class Score {
    // Scores.
    public double anger = 0.0;
    public double contempt = 0.0;
    public double disgust = 0.0;
    public double fear = 0.0;
    public double happiness = 0.0;
    public double neutral = 0.0;
    public double sadness = 0.0;
    public double surprise = 0.0;

    public Score(String jsonObject) {
        try {
            // Parse the json and grab the data for the first face.
            JSONArray jsonArr1 = new JSONArray(jsonObject);
            JSONObject jsonObj = jsonArr1.getJSONObject(0);

            // Get the scores.
            JSONObject jsonScores = jsonObj.getJSONObject("scores");

            // Parse the score for each attribute.
            anger = getScore(jsonScores, "anger");
            contempt = getScore(jsonScores, "contempt");
            disgust = getScore(jsonScores, "disgust");
            fear = getScore(jsonScores, "fear");
            happiness = getScore(jsonScores, "happiness");
            neutral = getScore(jsonScores, "neutral");
            sadness = getScore(jsonScores, "sadness");
            surprise = getScore(jsonScores, "surprise");
        } catch (JSONException e) {
            // Ignore this for now and let all the scores be 0.
            // e.printStackTrace();
        }
    }

    private double getScore(JSONObject jsonObject, String attr) {
        try {
            return jsonObject.getDouble(attr);
        } catch (JSONException e) {
            return 0.0;
        }
    }

    public boolean isHappiness() {
        return (happiness > 0.3);
    }

    public boolean isSurprise() {
        return (surprise > 0.2);
    }

    public boolean isSadness() {
        return (sadness > 0.2);
    }

    public String toString() {
        return "anger(" + anger + "); contempt(" + contempt + "); disgust(" + disgust + "); " +
                "fear(" + fear + "); happiness(" + happiness + "); neutral(" + neutral +
                "); sadness(" + sadness + "); surprise(" + surprise + ");";
    }
}
