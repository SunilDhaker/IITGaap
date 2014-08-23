package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.PushService;

import java.util.Calendar;
import java.util.Date;

import sunil.dhaker.iitgnotif.adapters.FeedAdapter;

public class Hostel extends Activity implements ListView.OnItemClickListener {

    public static int HOSTEL = 0;
    public static int CLUB = 1;
    FeedAdapter mHostelFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        ListView mHostelFeed = (ListView) findViewById(R.id.m_hostel_feed);
        if (getIntent().getIntExtra("type", HOSTEL) == HOSTEL) {
            int id = getIntent().getIntExtra("id", 0);
            setTitle(getResources().getStringArray(R.array.hostel_array)[id]);
        } else if (getIntent().getIntExtra("type", HOSTEL) == CLUB) {
            int id = getIntent().getIntExtra("id", 0);
            setTitle(getResources().getStringArray(R.array.clubs)[id]);
        }
        mHostelFeedAdapter = new FeedAdapter(this);
        mHostelFeedAdapter.setIsForParticularChannel(true);
        mHostelFeedAdapter.setChannel(getTitle().toString());
        mHostelFeed.setAdapter(mHostelFeedAdapter);
        mHostelFeed.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHostelFeedAdapter.loadFeed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hostel, menu);
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
            onBackPressed();
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.hostel_subscribe) {
            if (PushService.getSubscriptions(getApplication()).contains(getTitle())) {
                PushService.unsubscribe(getApplication(), getTitle().toString());
                Toast.makeText(getApplication(), "You are  UNSUBSIDISED from " + getTitle(), Toast.LENGTH_SHORT).show();
            } else {
                PushService.subscribe(getApplication(), getTitle().toString(), Home.class);
                Toast.makeText(getApplication(), "You are subscribed to cannel " + getTitle(), Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.hostel_announce) {
            Intent i = new Intent(this, CheckUser.class);
            i.putExtra("channel", getTitle().toString());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = this;
        Notification notification = (Notification) parent.getItemAtPosition(position);
        Intent i = new Intent(activity, DetailFeed.class);
        i.putExtra("notif", notification.getID());
        i.putExtra("title", notification.getHeader());
        i.putExtra("content", notification.getContent());
        i.putExtra("isEvent", notification.getIsEvent());
        i.putExtra("event_venue", notification.getEventVenue());
        Date d = notification.getEventDate();
        i.putExtra("event_time", d.getHours() + ":" + d.getMinutes() + " " + d.getDay() + "/" + d.getMonth());
        i.putExtra("channel", notification.getChannel());
        i.putExtra("sender", notification.getSenderName());
        long min = Calendar.getInstance().getTime().getTime() / 60000 - notification.getDate().getTime() / 60000;
        if (min < 60)
            i.putExtra("time", min + " min ago");
        else if (min < 60 * 24)
            i.putExtra("time", min / 60 + " hr ago");
        else
            i.putExtra("time", min / (60 * 24) + " days ago ");
        startActivity(i);
    }
}
