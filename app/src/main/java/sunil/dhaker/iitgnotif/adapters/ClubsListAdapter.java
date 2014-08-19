package sunil.dhaker.iitgnotif.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sunil.dhaker.iitgnotif.R;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 06-08-2014.
 */
public class ClubsListAdapter extends BaseAdapter {
    Context context;
    String[] departments;

    public ClubsListAdapter(Context context) {
        this.context = context;
        departments = context.getResources().getStringArray(R.array.clubs);
    }

    @Override
    public int getCount() {
        return departments.length;
    }

    @Override
    public Object getItem(int position) {
        return departments[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.hostel_list_item, parent, false);
        TextView hostelName = (TextView) v.findViewById(R.id.hostel_name);
        hostelName.setText(departments[position]);
        return v;
    }
}

