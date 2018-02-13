package yannick.veron.channelmessaging;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import yannick.veron.channelmessaging.model.connect;

public class LoginActivity extends Activity implements View.OnClickListener, OnDownloadListener {

    private Button btnConnect;
    private EditText login;
    private  EditText password;

    public static final String PREFS_NAME = " MyPrefsFile ";

    private TextView debug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnConnect=(Button) findViewById(R.id.btnValiderID);
        btnConnect.setOnClickListener(this);

        login=(EditText) findViewById(R.id.id);
        password=(EditText) findViewById(R.id.password);

        debug=(TextView) findViewById(R.id.tvDebug);


    }

    @Override
    public void onClick(View v) {
        debug.setText("Debug :");
        HashMap data = new HashMap<String, String>();
        data.put("username", login.getText().toString());
        data.put("password", password.getText().toString());
        PostRequest pr = new PostRequest("http://www.raphaelbischof.fr/messaging/?function=connect",data);
        HttpPostHandler ph = new HttpPostHandler();
        ph.addOnDownloadListener(this);
        ph.execute(pr);
        /*for (OnDownloadListener listener:ph.getListeners()) {
            debug.setText(debug.getText()+listener.toString());
        }*/




    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        connect connection = gson.fromJson(downloadedContent, connect.class);


        debug.setText(debug.getText()+"\n<start>\nCode : "+connection.getCode()+"\n<end>");



        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("accesstoken", connection.getAccesstoken());
        editor.commit();





        SharedPreferences sharedPrefer = getSharedPreferences(PREFS_NAME, 0);
        String token = sharedPrefer.getString("accesstoken",null);
        debug.setText(debug.getText()+"\n"+token);


        if(connection.getCode()!=200)
        {
            Context ctx = getApplicationContext();
            int dura = Toast.LENGTH_LONG;
            Toast err = Toast.makeText(ctx, "error code : "+connection.getCode()+"\nerror : "+connection.getResponse(), dura);
            err.show();
        }
        else
        {
            Intent myIntent = new Intent(getApplicationContext(),ChannelListActivity.class);
            startActivity(myIntent);
        }






    }

    @Override
    public void onDownloadError(String error) {

    }
}
