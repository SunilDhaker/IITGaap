package sunil.dhaker.iitgnotif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 05-08-2014.
 */

public class HostelListAdapter extends BaseAdapter {

    String[] hostels;
    Context context;

    public HostelListAdapter(Context context) {
        this.context = context;
        hostels = context.getResources().getStringArray(R.array.hostel_array);
    }

    @Override
    public int getCount() {
        return hostels.length;
    }

    @Override
    public Object getItem(int position) {
        return hostels[position];
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
        hostelName.setText(hostels[position]);
        return v;
    }
}
