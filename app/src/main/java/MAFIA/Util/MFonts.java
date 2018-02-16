package MAFIA.Util;

import android.content.Context;
import android.graphics.Typeface;

public class MFonts
{
    private static final String path = "font/";

    public static Typeface getFont(Context context , FontsEnum fonts)
    {
        switch (fonts)
        {
            case splashScreen_AAA_logo:
                return Typeface.createFromAsset(context.getAssets(), path + "splash_aaa_logo.ttf");

            case splashScreen_AAA_studio:
                return Typeface.createFromAsset(context.getAssets(), path + "splash_aaa_studio.ttf");

            case splashScreen_AAA_Loading:
                return Typeface.createFromAsset(context.getAssets(), path + "loading.ttf");

            default:
                return null;
        }
    }
}
