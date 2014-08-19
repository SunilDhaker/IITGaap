package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.SendCallback;

import java.util.Calendar;

public class AnouncmentActivity extends Activity implements CheckBox.OnCheckedChangeListener {

    TextView title, content, date, venue;
    CheckBox isEventMark;
    boolean isEvent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anounce);
        title = (TextView) findViewById(R.id.announce_header);
        content = (TextView) findViewById(R.id.announce_description);
        date = (TextView) findViewById(R.id.announce_date);
        venue = (TextView) findViewById(R.id.announce_venue);
        isEventMark = (CheckBox) findViewById(R.id.announce_isevent);
        isEventMark.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anouncment, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        if (id == R.id.send) {
            Notification n = new Notification();
            n.setIsEvent(isEvent);
            n.setHeader(title.getText().toString());
            n.setContent(content.getText().toString());
            n.setChannel(getIntent().getStringExtra("channel"));
            n.setEventVenue(venue.getText().toString());
            n.setEventDate(Calendar.getInstance().getTime());
            n.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Toast.makeText(getApplication(), "msg sending failed ", Toast.LENGTH_SHORT).show();
                        ParseQuery<ParseInstallation> installationParseQuery = ParseInstallation.getQuery();
                        ParsePush.sendMessageInBackground("" + title.getText().toString(), installationParseQuery, new SendCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null)
                                    Toast.makeText(getApplication(), "push sending failed ", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getApplication(), "msg sending success ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else
                        Toast.makeText(getApplication(), "msg sending Success ", Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseInstallation> installationParseQuery = ParseInstallation.getQuery();
                    ParsePush.sendMessageInBackground("" + title.getText().toString(), installationParseQuery, new SendCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null)
                                Toast.makeText(getApplication(), "push sending failed ", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplication(), "msg sending success ", Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            });
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isEvent = isChecked;
        if (!isChecked) {
            venue.setVisibility(View.INVISIBLE);
            date.setVisibility(View.INVISIBLE);
        } else {
            venue.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
        }
    }
}
