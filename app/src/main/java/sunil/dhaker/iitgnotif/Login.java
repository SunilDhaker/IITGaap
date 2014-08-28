package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class Login extends Activity {

    EditText elogin, epass;
    Button signIn, signUp;
    String login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        elogin = (EditText) findViewById(R.id.login_webmail);
        epass = (EditText) findViewById(R.id.login_password);
        signIn = (Button) findViewById(R.id.login_login);
        signUp = (Button) findViewById(R.id.login_signup);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
        if (id == R.id.login_ok) {
            logIn();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logIn() {

        login = elogin.getText().toString();
        pass = epass.getText().toString();

//        login = login.split("@")[0];

        final ProgressDialog p = ProgressDialog.show(this, "Loging in", ":)");
        ParseUser.logInInBackground(login, pass, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplication(), "Login Sucessfull", Toast.LENGTH_LONG);
                    p.dismiss();
                    finish();
                } else {
                    Toast.makeText(getApplication(), "Login Failed", Toast.LENGTH_LONG);
                    p.dismiss();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        this.finish();
    }
}
