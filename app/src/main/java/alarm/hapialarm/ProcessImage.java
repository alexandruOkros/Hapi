package alarm.hapialarm;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandru on 28-Jan-17.
 */
public class ProcessImage {
    private RequestQueue queue = null;
    private CameraActivity context = null;

    public ProcessImage(CameraActivity ctx) {
        context = ctx;
        queue = Volley.newRequestQueue(ctx);
    }

    public void makeRequest(final byte [] imageBinary) {
        Response.Listener<JSONArray> respListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Get the score of the picture.
                Score score = new Score(response.toString());
                context.processScore(score);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                // Ignore for now.
                // textView.setText(error.getMessage());
                Log.d("ERROR", ">>>>>>>>>>>>>>>>>>>>>>>>>   " + error.getMessage());
                context.processScore(null);
            }
        };
        String url = "https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize";
        final String cog_key = "779ae3bac8a64320b036341f59f2aaa1";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, url,
                (JSONObject) null, respListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                // headers.put("Content-Type", "application/octet-stream");
                headers.put("Ocp-Apim-Subscription-Key", cog_key);
                return headers;
            }

            @Override
            public byte[] getBody() {
                return imageBinary;
            }

            @Override
            public String getBodyContentType() {
                return "application/octet-stream";
            }
        };

        queue.add(jsonObjectRequest);
    }
}
