package yannick.veron.channelmessaging;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import yannick.veron.channelmessaging.model.ChannelList;

/**
 * Created by verony on 22/01/2018.
 */
public class ChannelListActivity extends Activity implements View.OnClickListener, OnDownloadListener, AdapterView.OnItemClickListener {

    public static final String PREFS_NAME = " MyPrefsFile ";
    public ListView lv;
    //public Button flButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);

        HashMap data = new HashMap<String, String>();
        SharedPreferences sharedPrefer = getSharedPreferences(PREFS_NAME, 0);
        String token = sharedPrefer.getString("accesstoken",null);
        data.put("accesstoken",token);

        lv = (ListView) findViewById(R.id.listChannel);
        /*flButton = (Button) findViewById(R.id.btFriendList);
        flButton.setOnClickListener(this);*/


        PostRequest pr = new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getchannels",data);
        HttpPostHandler ph = new HttpPostHandler();
        ph.addOnDownloadListener(this);

        ph.execute(pr);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(getApplicationContext(),FriendList.class);
        startActivity(myIntent);
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Log.d("ChanList",downloadedContent);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ChannelList cl = gson.fromJson(downloadedContent, ChannelList.class);


        Context ctx = getApplicationContext();
        int dura = Toast.LENGTH_LONG;
        Toast dl_result = Toast.makeText(ctx, "Dl_result : "+cl.getChannels(), dura);
        Log.d("ChanList",cl.toString()+" :)");
        dl_result.show();


        lv.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), cl.getChannels()));
        lv.setOnItemClickListener(this);


    }
    //coucou
    @Override
    public void onDownloadError(String error) {

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*String toastStr ="J'ai sélectionné l'item "+id;
        Toast.makeText(getApplicationContext(),toastStr,Toast.LENGTH_SHORT).show();*/
        Intent myIntent = new Intent(getApplicationContext(),ChannelActivity.class);
        myIntent.putExtra("CHAN_ID", id);
        startActivity(myIntent);

    }


}