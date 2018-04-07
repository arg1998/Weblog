package ir.mafiaaa.mafia.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ir.mafiaaa.mafia.GameActivity;
import ir.mafiaaa.mafia.R;

public class WaitingRoomFragment extends Fragment {

    LinearLayout l1,l2;
    ProgressBar waitingProgressBar;
    Animation upToDown,downToUp;
    TextView personCounter,secondCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_waiting_room, container, false);

        //set the id
        waitingProgressBar = (ProgressBar) view.findViewById(R.id.waitingRoomProgressBar);
        l1 = (LinearLayout) view.findViewById(R.id.waitingRoomLayout1);
        l2 = (LinearLayout) view.findViewById(R.id.waitingRoomLayout2);
        personCounter = (TextView) view.findViewById(R.id.text_person_counter);
        secondCounter = (TextView) view.findViewById(R.id.text_seconds_counter);

        //setOnClickListener
        waitingProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        });

        //animation settings
        upToDown = AnimationUtils.loadAnimation(getActivity(),R.anim.up_to_dawn);
        downToUp = AnimationUtils.loadAnimation(getActivity(),R.anim.down_to_up);
        l1.setAnimation(upToDown);
        l2.setAnimation(downToUp);

        //progress bar control
        waitingProgressBar.setProgress(14);
        personCounter.setText("14");

        //second Counter Control
        new CountDownTimer(5000,1000){
            public void onTick(long millisUntilFinished) {
                secondCounter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                secondCounter.setText("done!");
            }
        }.start();

        return view;
    }


}
