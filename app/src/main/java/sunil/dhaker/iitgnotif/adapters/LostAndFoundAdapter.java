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
import java.util.List;

import sunil.dhaker.iitgnotif.LostOrFoundItem;
import sunil.dhaker.iitgnotif.R;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 07-08-2014.
 */
public class LostAndFoundAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    ArrayList<LostOrFoundItem> items;
    Context c;

    public LostAndFoundAdapter(Context c) {
        this.c = c;
        items = new ArrayList<LostOrFoundItem>();
    }

    @Override
    public int getCount() {
        return 10;
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
        // LostOrFoundItem item = items.get(position);
        ViewHolder mViewHolder;
        if (v == null) {//first time creation
            v = inflater.inflate(R.layout.lost_found_item, parent, false);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
            mViewHolder = (ViewHolder) v.getTag();
//            if (isForParticularChannel)
//                v.findViewById(R.id.chennal_stamp).setVisibility(View.INVISIBLE);
//            mViewHolder.headerText.setText(item.getHeader());
//            mViewHolder.contentText.setText(item.getContent());
//            mViewHolder.dateText.setText(item.getDate().getDay() + "/" + item.getDate().getMonth());
//            mViewHolder.channelText.setText(item.getChannel());
//            if (item.getIsEvent()) {
//                mViewHolder.eventDateText.setVisibility(View.VISIBLE);
//                mViewHolder.eventDateText.setText(item.getEventDate().getDay() + "/" + item.getEventDate().getMonth());
//                mViewHolder.eventVenueText.setVisibility(View.VISIBLE);
//                mViewHolder.eventVenueText.setText(item.getEventVenue());
//            } else {
//                mViewHolder.eventDateText.setVisibility(View.INVISIBLE);
//                mViewHolder.eventVenueText.setVisibility(View.INVISIBLE);
//            }
//        } else {
//            mViewHolder = (ViewHolder) v.getTag();
//            mViewHolder.headerText.setText(item.getHeader());
//            mViewHolder.contentText.setText(item.getContent());
//            mViewHolder.dateText.setText(item.getDate().getDay() + "/" + item.getDate().getMonth());
//            mViewHolder.channelText.setText(item.getChannel());
//            if (item.getIsEvent()) {
//                mViewHolder.eventDateText.setVisibility(View.VISIBLE);
//                mViewHolder.eventDateText.setText(item.getEventDate().getDay() + "/" + item.getEventDate().getMonth());
//                mViewHolder.eventVenueText.setVisibility(View.VISIBLE);
//                mViewHolder.eventVenueText.setText(item.getEventVenue());
//            } else {
//                mViewHolder.eventDateText.setVisibility(View.INVISIBLE);
//                mViewHolder.eventVenueText.setVisibility(View.INVISIBLE);
//            }
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
                    items1 = (ArrayList<LostOrFoundItem>) items1;
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(c, R.string.loading_feed_error, Toast.LENGTH_SHORT);
                }
            }
        });

    }


    private class ViewHolder {
        public TextView headerText, contentText, dateText, eventDateText, channelText, eventVenueText;

        public ViewHolder(View v) {

            headerText = (TextView) v.findViewById(R.id.lf_header);
            contentText = (TextView) v.findViewById(R.id.lf_description);
            dateText = (TextView) v.findViewById(R.id.lf_date_stamp);
            eventDateText = (TextView) v.findViewById(R.id.lf_date_stamp);
            channelText = (TextView) v.findViewById(R.id.lf_venue);
        }
    }

}
