package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;


public class CheckUser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if (ParseUser.getCurrentUser() == null) {
            Toast.makeText(getApplication(), "Please Login First !", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        } else {
            ParseUser u = ParseUser.getCurrentUser() ;
            try {
                u.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (((Boolean)u.get("emailVerified")) || ((Boolean)u.get("isSuper"))) {
                Intent i = new Intent(this, Anouncment.class);
                i.putExtra("channel", getIntent().getStringExtra("channel"));
                startActivity(i);
                finish();
            } else  {
                Toast.makeText(getApplication(), "Please Verify you Email first !", Toast.LENGTH_LONG).show();
                ((TextView) findViewById(R.id.dispatch_message)).setText("Please Verify Your Email first ");
                finish();
            }
        }
        super.onResume();
    }
}
