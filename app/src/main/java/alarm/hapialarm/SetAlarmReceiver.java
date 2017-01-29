package alarm.hapialarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.Logger;
import android.support.v4.content.WakefulBroadcastReceiver;


/**
 * Created by laura on 28/01/17.
 */
public class SetAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // Toast.makeText(context, "Alarm worked ghg.", Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, SliderActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
