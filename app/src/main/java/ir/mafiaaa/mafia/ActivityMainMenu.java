package ir.mafiaaa.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityMainMenu extends AppCompatActivity {


    ImageButton playButton;
    ImageButton profileButton;
    ImageButton shopButton;
    ImageButton skillsButton;
    ImageButton settingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //button's place

        playButton = (ImageButton) findViewById(R.id.play_btn);
        profileButton = (ImageButton) findViewById(R.id.profile_btn);
        skillsButton = (ImageButton) findViewById(R.id.skills_btn);
        shopButton = (ImageButton) findViewById(R.id.shop_btn);
        settingButton = (ImageButton) findViewById(R.id.setting_btn);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainMenu.this , ShopActivity.class);
                startActivity(intent); //change
            }
        });
    }
}
