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

import sunil.dhaker.iitgnotif.adapters.ClubsListAdapter;


public class Clubs extends Fragment implements ListView.OnItemClickListener {

    ClubsListAdapter mClubAdapter;
    ListView mClubListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.hosel_list, container, false);
        mClubListView = (ListView) view.findViewById(R.id.hostel_list);
        mClubListView.setAdapter(mClubAdapter);
        mClubListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mClubAdapter = new ClubsListAdapter(activity);
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
        i.putExtra("type", Hostel.CLUB);
        startActivity(i);
    }


}
