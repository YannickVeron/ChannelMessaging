package yannick.veron.channelmessaging;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import yannick.veron.channelmessaging.model.Friend;
import yannick.veron.channelmessaging.model.Message;

/**
 * Created by verony on 07/03/2018.
 */
public class FriendListAdapter extends ArrayAdapter<Message> {

    private final Context context;
    private final ArrayList<Friend> values;

    public FriendListAdapter(Context context, ArrayList<Message> values)
    {
        super(context,R.layout.message_layout,values);
        this.context=context;
        this.values = values;
    }
}
