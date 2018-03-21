package ir.mafiaaa.mafia.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import ir.mafiaaa.mafia.R;

public class WaitingRoomFragment extends Fragment {

    LinearLayout l1,l2;
    ProgressBar waitingProgressBar;
    Animation upToDown,downToUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_waiting_room, container, false);

        waitingProgressBar = (ProgressBar) view.findViewById(R.id.waitingRoomProgressBar);
        l1 = (LinearLayout) view.findViewById(R.id.waitingRoomLayout1);
        l2 = (LinearLayout) view.findViewById(R.id.waitingRoomLayout2);
        upToDown = AnimationUtils.loadAnimation(getActivity(),R.anim.up_to_dawn);
        downToUp = AnimationUtils.loadAnimation(getActivity(),R.anim.down_to_up);
        l1.setAnimation(upToDown);
        l2.setAnimation(downToUp);

        return view;
    }


}
