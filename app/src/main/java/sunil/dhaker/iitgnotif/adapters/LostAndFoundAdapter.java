package sunil.dhaker.iitgnotif.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sunil.dhaker.iitgnotif.LostOrFoundItem;
import sunil.dhaker.iitgnotif.R;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 07-08-2014.
 */
public class LostAndFoundAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    ArrayList<LostOrFoundItem> items;
    Context c;
    Date current;

    public LostAndFoundAdapter(Context c) {
        this.c = c;
        items = new ArrayList<LostOrFoundItem>();
        current = Calendar.getInstance().getTime();
        loadFeed();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        LostOrFoundItem item = items.get(position);
        ViewHolder mViewHolder;
        if (v == null) {//first time creation
            v = inflater.inflate(R.layout.lost_found_item, parent, false);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
            mViewHolder = (ViewHolder) v.getTag();

            mViewHolder.headerText.setText(item.getItemName());
            mViewHolder.contentText.setText(item.getDescription());
            long min = current.getTime() / 60000 - item.getCreatedAt().getTime() / 60000;
            if (min < 60)
                mViewHolder.dateText.setText(min + " min");
            else if (min < 60 * 24)
                mViewHolder.dateText.setText(min / 60 + " hr");
            else
                mViewHolder.dateText.setText(min / (60 * 24) + " days");
            mViewHolder.eventVenueText.setText(item.getPlace());
            mViewHolder.eventDateText.setText(item.getDateString());
        } else {
            mViewHolder = (ViewHolder) v.getTag();
            mViewHolder.headerText.setText(item.getItemName());
            mViewHolder.contentText.setText(item.getDescription());
            long min = current.getTime() / 60000 - item.getCreatedAt().getTime() / 60000;
            if (min < 60)
                mViewHolder.dateText.setText(min + " min");
            else if (min < 60 * 24)
                mViewHolder.dateText.setText(min / 60 + " hr");
            else
                mViewHolder.dateText.setText(min / (60 * 24) + " days");
            mViewHolder.eventVenueText.setText(item.getPlace());
            mViewHolder.eventDateText.setText(item.getDateString());

        }

        return v;


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount > totalItemCount - 3) {
            //TODO get more data code here :)
            notifyDataSetChanged();
        }
    }

    public void loadFeed() {
        ParseQuery<LostOrFoundItem> query = ParseQuery.getQuery(LostOrFoundItem.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<LostOrFoundItem>() {
            @Override
            public void done(List<LostOrFoundItem> items1, ParseException e) {
                if (e == null) {
                    items = (ArrayList<LostOrFoundItem>) items1;
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(c, R.string.loading_feed_error, Toast.LENGTH_SHORT);
                }
            }
        });

    }


    private class ViewHolder {
        public TextView headerText, contentText, dateText, eventDateText, eventVenueText;

        public ViewHolder(View v) {

            headerText = (TextView) v.findViewById(R.id.lf_header);
            contentText = (TextView) v.findViewById(R.id.lf_description);
            dateText = (TextView) v.findViewById(R.id.lf_date);
            eventDateText = (TextView) v.findViewById(R.id.lf_date_stamp);
            eventVenueText = (TextView) v.findViewById(R.id.lf_venue_stamp);
        }
    }

}
