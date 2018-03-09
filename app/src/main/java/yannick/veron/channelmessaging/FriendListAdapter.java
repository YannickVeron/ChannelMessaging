package yannick.veron.channelmessaging;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

import yannick.veron.channelmessaging.model.Friend;
import yannick.veron.channelmessaging.model.Message;

/**
 * Created by verony on 07/03/2018.
 */
public class FriendListAdapter extends ArrayAdapter<Friend> {

    private final Context context;
    private final ArrayList<Friend> values;

    public FriendListAdapter(Context context, ArrayList<Friend> values)
    {
        super(context,R.layout.friend_layout,values);
        this.context=context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);


        TextView friendName = (TextView) rowView.findViewById(R.id.name);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        friendName.setText(values.get(position).getUsername());



        /*String s = values.get(position).getName();
        if (s.startsWith("iPhone")) {
            //imageView.setImageResource(R.drawable.no);
        }
        else
        {
            //imageView.setImageResource(R.drawable.ok);
        }*/
        return rowView;
    }


    public UUID getItemUUID(int position) {
        return values.get(position).getId();
    }
}
