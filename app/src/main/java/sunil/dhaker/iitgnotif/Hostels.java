package sunil.dhaker.iitgnotif;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import sunil.dhaker.iitgnotif.adapters.HostelListAdapter;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 05-08-2014.
 */
public class Hostels extends Fragment implements ListView.OnItemClickListener {

    HostelListAdapter mHostelAdapter;
    ListView mHostelListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.hosel_list, container, false);
        mHostelListView = (ListView) view.findViewById(R.id.hostel_list);
        mHostelListView.setAdapter(mHostelAdapter);
        mHostelListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mHostelAdapter = new HostelListAdapter(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = getActivity();
        Intent i = new Intent(activity, Hostel.class);
        i.putExtra("id", position);
        i.putExtra("type", Hostel.HOSTEL);
        startActivity(i);
    }
}