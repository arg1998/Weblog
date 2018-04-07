package ir.mafiaaa.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ir.mafiaaa.mafia.Fragment.DayFragment;
import ir.mafiaaa.mafia.Fragment.WaitingRoomFragment;

public class GameActivity extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        startBtn = (Button) findViewById(R.id.start_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayFragment dayFragment = new DayFragment();
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.game_layout,dayFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}
