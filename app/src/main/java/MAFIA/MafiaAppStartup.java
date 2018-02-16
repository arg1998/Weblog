package MAFIA;

import android.app.Application;

/**
 * this is thr first class which runs when the MAFIA App start loading
 *
 * USAGE : we can Initialize things in here before our MainActivity loads up
 *
 * IMPORTANT NOTE  : You MUST put this class in AndroidManifest.xml under the <Application> section as "name = ... ""
 * otherwise it will not work
 */

public class MafiaAppStartup extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        //// TODO: 1/29/2018 Initialize things here

    }
}
