package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Hostel extends Activity {

    FeedAdapter mHostelFeedAdapter;
    public static int HOSTEL = 0;
    public static int CLUB = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        ListView mHostelFeed = (ListView) findViewById(R.id.m_hostel_feed);
        mHostelFeedAdapter = new FeedAdapter(this);
        mHostelFeedAdapter.setIsForParticularChannel(true);
        mHostelFeed.setAdapter(mHostelFeedAdapter);
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
        if(getIntent().getIntExtra("type",HOSTEL) == HOSTEL) {
            int id = getIntent().getIntExtra("id", 0);
            setTitle(getResources().getStringArray(R.array.hostel_array)[id]);
        }else if(getIntent().getIntExtra("type",HOSTEL) == CLUB){
            int id = getIntent().getIntExtra("id", 0);
            setTitle(getResources().getStringArray(R.array.clubs)[id]);
        }
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
}
