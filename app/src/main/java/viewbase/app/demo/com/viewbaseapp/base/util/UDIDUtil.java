package viewbase.app.demo.com.viewbaseapp.base.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.UUID;

public class UDIDUtil {
    public static String getUDID(Context context) {
        String uniqueId = "";
        if (null != context) {
            uniqueId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            if (uniqueId != null && !uniqueId.isEmpty()) {
                uniqueId = getUniquePsuedoID();
            }
        }

        return uniqueId;
    }

    private static String getUniquePsuedoID() {
        String deviceIdShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits

        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            return new UUID(deviceIdShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial"; // some value
        }
        return new UUID(deviceIdShort.hashCode(), serial.hashCode()).toString();
    }
}
