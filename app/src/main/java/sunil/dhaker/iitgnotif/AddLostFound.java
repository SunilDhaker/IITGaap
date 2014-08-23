package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SaveCallback;


public class AddLostFound extends Activity implements View.OnClickListener {

    Button bt_submit;
    EditText et_title, description, date, venue, mobile, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost_found);
        bt_submit = (Button) findViewById(R.id.alf_button_submit);
        et_title = (EditText) findViewById(R.id.alf_item_name);
        description = (EditText) findViewById(R.id.alf_item_description);
        date = (EditText) findViewById(R.id.alf_date);
        venue = (EditText) findViewById(R.id.alf_venue);
        mobile = (EditText) findViewById(R.id.alf_mobile);
        address = (EditText) findViewById(R.id.alf_user_detail);
        bt_submit.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.add_lost_found, menu);
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
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        submit();
        finish();
    }

    private void submit() {

        LostOrFoundItem lfItem = new LostOrFoundItem();
        lfItem.seMobileNo(mobile.getText().toString());
        lfItem.setLOSTorFOUND(getIntent().getIntExtra("type", LostAndFound.LOST));
        lfItem.setDescription(description.getText().toString());
        lfItem.setItemName(et_title.getText().toString());
        lfItem.setNameAndAdress(address.getText().toString());
        lfItem.putPlace(venue.getText().toString());
        lfItem.setDateTimeString(date.getText().toString());
        lfItem.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplication(), "Posted item successfully", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
