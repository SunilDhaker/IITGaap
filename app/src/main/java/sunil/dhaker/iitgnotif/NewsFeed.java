package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.PushService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import sunil.dhaker.iitgnotif.adapters.FeedAdapter;


/**
 * Created by SONY on 01-08-2014.
 */
public class NewsFeed extends Fragment implements ListView.OnItemClickListener {

    FeedAdapter feedAdapter;
    ListView listContainerOfNotif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.news_feed, container, false);
        listContainerOfNotif = (ListView) view.findViewById(R.id.home_newsfeed);
        listContainerOfNotif.setAdapter(feedAdapter);
        listContainerOfNotif.setOnScrollListener(feedAdapter);
        listContainerOfNotif.setOnItemClickListener(this);
        //TODO: add the loading newsfeed here !!
        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        feedAdapter = new FeedAdapter(activity);
        ArrayList<String> channels = new ArrayList<String>(PushService.getSubscriptions(getActivity().getApplication()));
        channels.add("All");
        channels.add("IITG-ALL");
        feedAdapter.setChannelList(channels);
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