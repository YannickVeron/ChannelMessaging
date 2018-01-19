package yannick.veron.channelmessaging;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity implements View.OnClickListener{

    private Button btnConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnConnect=(Button) findViewById(R.id.btnValiderID);
        btnConnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }



}
