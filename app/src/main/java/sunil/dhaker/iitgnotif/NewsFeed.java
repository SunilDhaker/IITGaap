package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SONY on 01-08-2014.
 */
public class NewsFeed extends Fragment {

    FeedAdapter feedAdapter;
    ListView listContainerOfNotif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.news_feed, container, false);
        listContainerOfNotif = (ListView) view.findViewById(R.id.home_newsfeed);
        String strings[] = getResources().getStringArray(R.array.dummy_notifs);
        if (listContainerOfNotif == null)
            Log.d("dkjkshsdkfjj", "ListViewIsNull");
        if (feedAdapter == null)
            Log.d("dkjkshsdkfjj", "arrayIsNull");
        listContainerOfNotif.setAdapter(feedAdapter);
        listContainerOfNotif.setOnScrollListener(feedAdapter);
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

}