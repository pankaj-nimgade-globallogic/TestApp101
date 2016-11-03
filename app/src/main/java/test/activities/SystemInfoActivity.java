package test.activities;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.pankajnimgade.pankajtestapp.BuildConfig;
import com.example.pankajnimgade.pankajtestapp.R;

import java.lang.reflect.Method;

public class SystemInfoActivity extends AppCompatActivity {
    private static final String TAG = SystemInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.SystemInfoActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        String versionName = BuildConfig.VERSION_NAME;
        Log.d(TAG, "initializeUI: versionName: "+versionName);

        String device = Build.MODEL;
        Log.d(TAG, "initializeUI: MODEL: "+device);

        String serialNumber = Build.SERIAL;
        Log.d(TAG, "initializeUI: SERIAL: "+getManufactureSerialNumber());

        String androidOS = Build.VERSION.RELEASE;
        Log.d(TAG, "initializeUI: androidOS: "+androidOS);


    }

    public static String getManufactureSerialNumber(){
        String serial;
        try{
            // Samsung tablets don't use Build.SERIAL for the serial number
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serial = (String) get.invoke(c, "ril.serialnumber", "unknown");
            if(serial.equals("unknown")) {
                serial = Build.SERIAL;
            }
        }
        catch (Exception ignored) {serial = Build.SERIAL;}
        return serial;
    }
}
