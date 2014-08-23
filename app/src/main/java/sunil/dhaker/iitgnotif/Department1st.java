package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Date;

import sunil.dhaker.iitgnotif.adapters.FeedAdapter;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 16-08-2014.
 */
public class Department1st extends Fragment implements ListView.OnItemClickListener {
    FeedAdapter feedAdapter;
    ListView listContainerOfNotif;
    String channel_name;
    int channel_id;

    public void setChannelName(int position) {
        channel_id = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.news_feed, container, false);
        listContainerOfNotif = (ListView) view.findViewById(R.id.home_newsfeed);
        listContainerOfNotif.setAdapter(feedAdapter);
        listContainerOfNotif.setOnScrollListener(feedAdapter);
        listContainerOfNotif.setOnItemClickListener(this);
        getActivity().setTitle(channel_name);
        //TODO: add the loading newsfeed here !!
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.department, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        feedAdapter = new FeedAdapter(activity);
        channel_name = getResources().getStringArray(R.array.departments_short_array)[channel_id] + "_1st";
        feedAdapter.setChannel(channel_name);
        feedAdapter.loadFeed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = getActivity();
        FragmentManager fm = activity.getFragmentManager();
        Notification notification = (Notification) parent.getItemAtPosition(position);
        Intent i = new Intent(activity, DetailFeed.class);
        i.putExtra("notif", notification.getID());
        i.putExtra("title", notification.getHeader());
        i.putExtra("content", notification.getContent());
        i.putExtra("isEvent", notification.getIsEvent());
        i.putExtra("event_venue", notification.getEventVenue());
        Date d = notification.getEventDate();
        i.putExtra("event_time", d.getHours() + ":" + d.getMinutes() + " " + d.getDay() + "/" + d.getMonth());
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