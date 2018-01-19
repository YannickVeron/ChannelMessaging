package yannick.veron.channelmessaging;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends Activity implements View.OnClickListener, OnDownloadListener {

    private Button btnConnect;
    private EditText login;
    private  EditText password;

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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, downloadedContent, duration);
        toast.show();
        debug.setText(debug.getText()+"\n<start>\n"+downloadedContent+"\n<end>");
    }

    @Override
    public void onDownloadError(String error) {

    }
}
