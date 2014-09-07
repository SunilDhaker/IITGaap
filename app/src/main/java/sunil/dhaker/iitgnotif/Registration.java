package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Registration extends Activity {

    Button registrationButton;
    EditText username, webmail, password, confirmed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationButton = (Button) findViewById(R.id.button_register);
        username = (EditText) findViewById(R.id.registration_username);
        webmail = (EditText) findViewById(R.id.registration_email);
        password = (EditText) findViewById(R.id.registration_pass);
        confirmed_pass = (EditText) findViewById(R.id.registration_pass_2);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registration, menu);
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

    public void register() {
        String errorMessage = "";
        if (!password.getText().toString().contentEquals(confirmed_pass.getText().toString())) {
            errorMessage = "Password didn't mached";
            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_SHORT).show();
        } else if(!(webmail.getText().toString().contains("@iitg.ac.in") ||webmail.getText().toString().contains("@iitg.ac.in") )){
            errorMessage = "Only iitg webmails are allowed";
            Toast.makeText(getApplication() , "ONLY IITG Webmail is allowed" , Toast.LENGTH_SHORT).show();
        }
        else {
            ParseUser user = new ParseUser();
            user.setEmail(webmail.getText().toString());
            user.setPassword(password.getText().toString());
            user.setUsername(webmail.getText().toString().split("@")[0]);
            user.put("Name", username.getText().toString());
            final ProgressDialog p = ProgressDialog.show(this , "Signing Up" , "");
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplication(), "You are registered successfully , check your email to complete registration ", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                        finish();
                    } else {
                        Log.d("", e.getMessage());
                        Toast.makeText(getApplication(), "Registration Failed ", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                    }
                }
            });
        }
    }
}
