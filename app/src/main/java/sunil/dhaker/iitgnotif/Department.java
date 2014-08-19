package sunil.dhaker.iitgnotif;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.PushService;

import java.util.Locale;

public class Department extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // Set up the action bar.
        String department_name = getResources().getStringArray(R.array.departments_array)[getIntent().getIntExtra("departmentID", 0)];
        final ActionBar actionBar = getActionBar();
        setTitle(department_name);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.department, menu);
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
        if (id == R.id.department_subscribe) {
            PushService.subscribe(getApplication(), getTitle().toString(), Home.class);

            Toast.makeText(getApplication(), "You are subscribed to cannel " + getTitle(), Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.department_announce) {
            Intent i = new Intent(this, AnouncmentActivity.class);
            i.putExtra("channel", getTitle().toString());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.d("", "" + position);
            if (position == 1 + 1) {
                Department1st d = new Department1st();
                d.setChannelName(getIntent().getIntExtra("departmentID", 0));
                return d;
            }
            if (position == 2 + 1) {
                Department2nd d = new Department2nd();
                d.setChannelName(getIntent().getIntExtra("departmentID", 0));
                return d;
            }
            if (position == 3 + 1) {
                Department3rd d = new Department3rd();
                d.setChannelName(getIntent().getIntExtra("departmentID", 0));
                return d;
            }
            if (position == 4 + 1) {
                Department4th d = new Department4th();
                d.setChannelName(getIntent().getIntExtra("departmentID", 0));
                return d;
            }
            {
                DepartmentAll d = new DepartmentAll();
                d.setChannelName(getIntent().getIntExtra("departmentID", 0));
                return d;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.department_feed);
                case 1:
                    return getString(R.string.first_year);
                case 2:
                    return getString(R.string.second_year);
                case 3:
                    return getString(R.string.third_year);
                case 4:
                    return getString(R.string.fourth_year);
            }
            return null;
        }
    }

}

