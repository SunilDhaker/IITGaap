package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.Calendar;

public class DetailFeed extends Activity {

    Notification notification;
    TextView header, content, datesent, eventDate, eventVenue, channel, sender;
    View eventDateContainer, eventVenueContainer;
    String sHeader, sContent, sEventVenue, stime, eventTime;
    boolean isEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_notif);
        findViewElement();
        initializeView();
        updateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_feed, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
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
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void findViewElement() {
        header = (TextView) findViewById(R.id.nd_header);
        content = (TextView) findViewById(R.id.nd_content);
        datesent = (TextView) findViewById(R.id.nd_notif_date);
        eventDate = (TextView) findViewById(R.id.nd_event_time_value);
        eventVenue = (TextView) findViewById(R.id.nd_venue_value);
        eventDateContainer = findViewById(R.id.nd_event_time_container);
        eventVenueContainer = findViewById(R.id.nd_venue_container);
    }

    public void initializeView() {
        sHeader = getIntent().getStringExtra("title");
        sContent = getIntent().getStringExtra("content");
        isEvent = getIntent().getBooleanExtra("isEvent", false);
        stime = getIntent().getStringExtra("time");
        header.setText(sHeader);
        content.setText(sContent);
        datesent.setText(stime);
        if (isEvent) {
            sEventVenue = getIntent().getStringExtra("event_venue");
            eventTime = getIntent().getStringExtra("event_time");
            eventDateContainer.setVisibility(View.VISIBLE);
            eventVenueContainer.setVisibility(View.VISIBLE);
            eventDate.setText(eventTime);
            eventVenue.setText(sEventVenue);
        } else {

            eventVenueContainer.setVisibility(View.INVISIBLE);
            eventDateContainer.setVisibility(View.INVISIBLE);

        }
    }

    public void updateView() {
        String id = getIntent().getStringExtra("notif");
        notification = ParseObject.createWithoutData(Notification.class, id);
        notification.fetchInBackground(new GetCallback<Notification>() {
            @Override
            public void done(Notification parseObject, ParseException e) {
                notification = parseObject;
                header.setText(notification.getHeader());
                content.setText(notification.getContent());
                long min = Calendar.getInstance().getTime().getTime() / 60000 - notification.getDate().getTime() / 60000;
                if (min < 60)
                    datesent.setText(min + " min ago");
                else if (min < 60 * 24)
                    datesent.setText(min / 60 + " hr ago");
                else
                    datesent.setText(min / (60 * 24) + " days ago");
                if (isEvent) {
                    eventDate.setVisibility(View.VISIBLE);
                    eventVenue.setVisibility(View.VISIBLE);
                    eventDate.setText(notification.getDate().toString());
                    eventVenue.setText(notification.getEventVenue());
                } else {
                    eventDate.setVisibility(View.INVISIBLE);
                    eventVenue.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


}
