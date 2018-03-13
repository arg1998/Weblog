package ir.mafiaaa.mafia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMainMenu extends AppCompatActivity {


    ImageButton playButton;
    ImageButton profileButton;
    ImageButton shopButton;
    ImageButton skillsButton;
    ImageButton settingButton;

    Dialog myDialog;

    LinearLayout MusicButton;
    LinearLayout SoundEffectButton;

    TextView txtclose;
    TextView txtMusicState;
    TextView txtSoundEffectState;

    static boolean musicState = true;
    static boolean soundEfffctState = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        myDialog = new Dialog(this); //Dialog for the setting Popup

        //Menu buttons

        playButton = (ImageButton) findViewById(R.id.play_btn);
        profileButton = (ImageButton) findViewById(R.id.profile_btn);
        skillsButton = (ImageButton) findViewById(R.id.skills_btn);
        shopButton = (ImageButton) findViewById(R.id.shop_btn);
        settingButton = (ImageButton) findViewById(R.id.setting_btn);

        //set OnClickListener for Buttons

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this , ShopActivity.class);
                startActivity(intent); //change
            }
        });

    }
        /* Setting Popup */



    public void ShowPopup(View v) {
        myDialog.setContentView(R.layout.popup_setting);

        txtMusicState = (TextView) myDialog.findViewById((R.id.txtMusicState));
        txtSoundEffectState = (TextView) myDialog.findViewById(R.id.txtSoundEffectState);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose); //for close the popup
        MusicButton = (LinearLayout) myDialog.findViewById(R.id.music_btn);
        SoundEffectButton = (LinearLayout) myDialog.findViewById(R.id.sound_effect_btn);

        txtclose.setText("X");

        /* On Click Listeners */

        MusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicState == true){
                    MusicButton.setBackgroundColor(Color.parseColor("#4b4b4b"));
                    txtMusicState.setText("Off");
                    musicState = false;}
                else if (musicState == false){
                    MusicButton.setBackgroundColor(Color.parseColor("#b20000"));
                    txtMusicState.setText("On");
                    musicState = true;}
            }
        });

        SoundEffectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundEfffctState == true) {
                    SoundEffectButton.setBackgroundColor(Color.parseColor("#4b4b4b"));
                    txtSoundEffectState.setText("Off");
                    soundEfffctState = false;}
                else if (soundEfffctState == false){
                    SoundEffectButton.setBackgroundColor(Color.parseColor("#0068b2"));
                    txtSoundEffectState.setText("On");
                    soundEfffctState = true;
                }
                }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
