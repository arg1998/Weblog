package MAFIA.Game.Settings;

import android.content.Context;

import MAFIA.MConstants;
import MAFIA.Storage.MStorage;

/**
 *
 */

public class MSettings
{
    private  boolean isSoundFxEnabled = true;
    private  boolean isMusicEnabled = true;
    private MStorage settingFile;

    public MSettings(Context ctx)
    {
        settingFile = new MStorage(ctx, MConstants.Storage.PrefsSetting);
        readSettings();
    }

    /**
     * this function will load  user's saved settings from device into memory , so we can access
     * them just by calling the functions from this class
     */
    public void readSettings()
    {
        int temp;

        temp = settingFile.getInt(MConstants.Storage.PrefsSetting_SoundFx_KEY);
        if(temp == -1)
        {
            settingFile.setInt(MConstants.Storage.PrefsSetting_SoundFx_KEY , 1);
            isSoundFxEnabled = true;
        }
        else isSoundFxEnabled = (temp == 1);


        temp = settingFile.getInt(MConstants.Storage.PrefsSetting_Music_KEY);
        if(temp == -1)
        {
            settingFile.setInt(MConstants.Storage.PrefsSetting_Music_KEY , 1);
            isMusicEnabled = true;
        }
        else isMusicEnabled = (temp == 1);

    }

    public boolean isSoundFxEnabled() {return isSoundFxEnabled;}
    public boolean isMusicEnabled() {return isMusicEnabled;}

    public void setSoundFxEnabled(boolean state)
    {
        settingFile.setInt(MConstants.Storage.PrefsSetting_SoundFx_KEY , ((state) ? 1 : 0 ) );
        isSoundFxEnabled = state;
    }
    public void setMusicEnabled(boolean state)
    {
        settingFile.setInt(MConstants.Storage.PrefsSetting_Music_KEY , ((state) ? 1 : 0 ) );
        isMusicEnabled = state;
    }

}
