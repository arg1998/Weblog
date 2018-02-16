package MAFIA.Game.Sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import MAFIA.Game.Sound.Interfaces.MAudioEventHandler;


public class MAudioPlayer
{
    private Context context;
    private MediaPlayer player = null;
    private boolean loaded = false;
    private boolean loop = false;

    public MAudioPlayer(Context ctx )
    {
        context = ctx;
        loaded = false;
    }

    public void setLooping(boolean loop)
    {
        this.loop = loop;
    }
    public boolean isLooping(){return loop;}



    public void load(int audioResourceID)
    {
        player = MediaPlayer.create(context , audioResourceID);
        player.setLooping(loop);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            player.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
        }

        loaded = true;
    }
    public void load(int audioResourceID , final MAudioEventHandler eventHandler)
    {
        player = MediaPlayer.create(context , audioResourceID);
        player.setLooping(loop);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            player.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
        }

        loaded = true;

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(eventHandler != null) eventHandler.onAudioFinished();
            }
        });
        if (eventHandler != null) eventHandler.onAudioStarted();
    }


    public void play()
    {
        if(player != null && !player.isPlaying())
        {
            player.start();
        }
    }

    public void pause()
    {
        if(player != null && player.isPlaying())
        {
            player.pause();
        }
    }

    public void stop()
    {
        if(player != null && loaded)
        {
            if(player.isPlaying()) player.stop();
        }
    }

    public void reset()
    {
        if(player != null && loaded)
        {
            player.reset();
        }
    }

    public void dispose()
    {
        player.release();
        player = null;
        loaded = false;
    }
}
