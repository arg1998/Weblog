package ir.mafiaaa.mafia.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.mafiaaa.mafia.R;

public class ChatDataAdapter extends ArrayAdapter<ChatItem>
{

    int layoutResourceID;
    ArrayList<ChatItem> chatItems;
    Context context;

    public ChatDataAdapter(Context context, int resource, ArrayList<ChatItem> data)
    {
        super(context, resource, data);

        this.context = context;
        this.chatItems = data;
        layoutResourceID = resource;
    }

    @Override
    public int getCount() {
        return chatItems.size();
    }

    @Override
    public ChatItem getItem(int position) {
        return chatItems.get(position);
    }

    @Override
    public int getPosition(ChatItem item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View row, ViewGroup parent)
    {
        class ViewHolder
        {
            ImageView imgPlayerImage;
            TextView tvPlayerNickName , tvPlayerMessage;
            ImageButton btnReportSender;
        }

        ViewHolder viewHolder = new ViewHolder();

        if (row == null || row.getTag() == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceID ,null);

            viewHolder.btnReportSender = (ImageButton) row.findViewById(R.id.btnReportMessage);
            viewHolder.imgPlayerImage = (ImageView) row.findViewById(R.id.imgPlayerImage);
            viewHolder.tvPlayerNickName = (TextView) row.findViewById(R.id.tvPlayerNickName);
            viewHolder.tvPlayerMessage= (TextView) row.findViewById(R.id.tvPlayerMessage);

            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) row.getTag();
        }

        final ChatItem chatItem = getItem(position);


        viewHolder.tvPlayerNickName.setText(chatItem.getSenderPlayer().getNickName());
        viewHolder.tvPlayerMessage.setText(chatItem.getMessage());
        int imageID = chatItem.getSenderPlayer().getLevel();
        switch (imageID)
        {
            case 1:
                viewHolder.imgPlayerImage.setImageResource(R.drawable.img_chat_example_1);
                break;
            case 2:
                viewHolder.imgPlayerImage.setImageResource(R.drawable.img_chat_example_2);
                break;
            case 3:
                viewHolder.imgPlayerImage.setImageResource(R.drawable.img_chat_example_3);
                break;
            case 4:
                viewHolder.imgPlayerImage.setImageResource(R.drawable.img_chat_example_4);
                break;
            case 5:
                viewHolder.imgPlayerImage.setImageResource(R.drawable.img_chat_example_5);
                break;

            default:
                break;
        }
        if (chatItem.getSenderPlayer().getID().equals("123456")) //ID of player itself
        {
            viewHolder.btnReportSender.setVisibility(View.GONE);
            viewHolder.btnReportSender.setEnabled(false);

            viewHolder.tvPlayerNickName.setBackgroundResource(R.drawable.bg_chat_nickname_self);
            viewHolder.tvPlayerMessage.setBackgroundResource(R.drawable.bg_chat_message_self);
        }
        else
        {
            viewHolder.btnReportSender.setVisibility(View.VISIBLE);
            viewHolder.btnReportSender.setEnabled(true);

            viewHolder.tvPlayerNickName.setBackgroundResource(R.drawable.bg_chat_nickname_other);
            viewHolder.tvPlayerMessage.setBackgroundResource(R.drawable.bg_chat_message_other);
        }

        viewHolder.btnReportSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext() ,chatItem.getSenderPlayer().getNickName() + " Has Been Reported" , Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

}
