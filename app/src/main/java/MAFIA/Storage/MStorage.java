package MAFIA.Storage;


import android.content.Context;
import android.content.SharedPreferences;

import MAFIA.MConstants;
import MAFIA.Security.MSecurity;

/**
 * this class is a wrapper around Android Shared Preferences
 */

public class MStorage // Mafia Storage System
{
    private SharedPreferences prefs = null;
    private SharedPreferences.Editor editor = null;


    /**
     *
     * @param ctx
     * @param storageName is a const string from MConstants.Storage
     */
    public MStorage(Context ctx , String storageName)
    {
        if (ctx == null) throw new RuntimeException("Context Must not be Null");
        prefs = ctx.getSharedPreferences(storageName, Context.MODE_PRIVATE);
    }


    public void setString(String key, String value) {
        editor = prefs.edit();
        editor.putString(MSecurity.hashString(key), value);
        editor.apply();
    }
    public void setFloat(String key, float value) {
        editor = prefs.edit();
        editor.putFloat(MSecurity.hashString(key), value);
        editor.apply();
    }
    public void setInt(String key, int value) {
        editor = prefs.edit();
        editor.putInt(MSecurity.hashString(key), value);
        editor.apply();
    }


    public String getString(String key) {
        return prefs.getString(MSecurity.hashString(key), null);
    }
    public float getFloat(String key) {
        return prefs.getFloat(MSecurity.hashString(key), -1.0f);
    }
    public int getInt(String key) {
        return prefs.getInt(MSecurity.hashString(key), -1);
    }


    public boolean remove(String key) {
        try {
            editor = prefs.edit();
            editor.remove(MSecurity.hashString(key));
            editor.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
