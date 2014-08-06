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
        i.putExtra("notif", notification);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(i);
    }
}