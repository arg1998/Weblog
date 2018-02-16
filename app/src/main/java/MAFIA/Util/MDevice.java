package MAFIA.Util;


import android.app.Activity;
import android.util.DisplayMetrics;

public class MDevice
{

    public static DisplayMetrics getDeviceDisplayMetcis(Activity activity)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}

