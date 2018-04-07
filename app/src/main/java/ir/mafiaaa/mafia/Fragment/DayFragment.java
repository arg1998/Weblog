package ir.mafiaaa.mafia.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ir.mafiaaa.mafia.R;
import ir.mafiaaa.mafia.adapter.ChatDataAdapter;
import ir.mafiaaa.mafia.adapter.ChatItem;
import ir.mafiaaa.mafia.adapter.Player;

public class DayFragment extends Fragment {

    ArrayList<ChatItem> chatItems = new ArrayList<>();
    ChatDataAdapter chatDataAdapter;
    ArrayAdapter<String> adapter;
    ImageButton btnSend;
    EditText etMessage;
    ListView lvChatList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        btnSend = (ImageButton) view.findViewById(R.id.btnSendMessage);
        etMessage = (EditText) view.findViewById(R.id.etInputChat);
        lvChatList = (ListView) view.findViewById(R.id.lvChatLists);


        chatItems.add(new ChatItem(new Player("234567" , "Afshin" , 2), "سلام من افشینم "));
        chatItems.add(new ChatItem(new Player("234568" , "Shima" , 4), "سمنم شیمام. خوب من میگم که مافیا همین افشینه "));
        chatItems.add(new ChatItem(new Player("234569" , "Ali Reza" , 5), "سنه بابا. افشین نیست. امیررضاست "));
        chatItems.add(new ChatItem(new Player("234560" , "AMP" , 5), "نه. منم میگم افشینه"));
        chatItems.add(new ChatItem(new Player("234567" , "Afshin" , 2), "من نیستم بخدا. امیررضاست"));

        chatDataAdapter = new ChatDataAdapter(getActivity() , R.layout.item_chat_other_first_message , chatItems);
        lvChatList.setAdapter(chatDataAdapter);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatDataAdapter.add(new ChatItem(new Player("123456","Amir Reza",1), etMessage.getText().toString()));
                chatDataAdapter.notifyDataSetChanged();
                etMessage.setText("");
            }
        });



        return view;
    }


}
