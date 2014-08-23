package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import sunil.dhaker.iitgnotif.adapters.LostAndFoundAdapter;

public class LostAndFound extends Activity {


    public static int LOST = 0;
    public static int FOUND = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ListView l = (ListView) findViewById(R.id.lost_and_found_list);
        l.setAdapter(new LostAndFoundAdapter(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost_and_found, menu);
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
        if (id == R.id.lost_button) {
            Intent i = new Intent(this, AddLostFound.class);
            i.putExtra("type", LOST);
            startActivity(i);
            return true;
        }
        if (id == R.id.found_button) {
            Intent i = new Intent(this, AddLostFound.class);
            i.putExtra("type", FOUND);
            startActivity(i);
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
