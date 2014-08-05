package sunil.dhaker.iitgnotif;

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

/**
 * Created by SONY on 02-08-2014.
 */
public class FeedAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    ArrayList<Notification> notificationList;
    Context c;

    public FeedAdapter(Context c) {
        this.c = c;
        notificationList = new ArrayList<Notification>();
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        Notification notif = notificationList.get(position);
        ViewHolder mViewHolder;
        if (v == null) {//first time creation
            v = inflater.inflate(R.layout.list_item, parent, false);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
            mViewHolder = (ViewHolder) v.getTag();
            mViewHolder.headerText.setText(notif.getHeader());
            mViewHolder.contentText.setText(notif.getContent());
            mViewHolder.dateText.setText(notif.getDate().getDay() + "/" + notif.getDate().getMonth());
            mViewHolder.channelText.setText(notif.getChannel());
            if (notif.getIsEvent()) {
                mViewHolder.eventDateText.setVisibility(View.VISIBLE);
                mViewHolder.eventDateText.setText(notif.getEventDate().getDay() + "/" + notif.getEventDate().getMonth());
                mViewHolder.eventVenueText.setVisibility(View.VISIBLE);
                mViewHolder.eventVenueText.setText(notif.getEventVenue());
            } else {
                mViewHolder.eventDateText.setVisibility(View.INVISIBLE);
                mViewHolder.eventVenueText.setVisibility(View.INVISIBLE);
            }
        } else {
            mViewHolder = (ViewHolder) v.getTag();
            mViewHolder.headerText.setText(notif.getHeader());
            mViewHolder.contentText.setText(notif.getContent());
            mViewHolder.dateText.setText(notif.getDate().getDay() + "/" + notif.getDate().getMonth());
            mViewHolder.channelText.setText(notif.getChannel());
            if (notif.getIsEvent()) {
                mViewHolder.eventDateText.setVisibility(View.VISIBLE);
                mViewHolder.eventDateText.setText(notif.getEventDate().getDay() + "/" + notif.getEventDate().getMonth());
                mViewHolder.eventVenueText.setVisibility(View.VISIBLE);
                mViewHolder.eventVenueText.setText(notif.getEventVenue());
            } else {
                mViewHolder.eventDateText.setVisibility(View.INVISIBLE);
                mViewHolder.eventVenueText.setVisibility(View.INVISIBLE);
            }
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
        ParseQuery<Notification> query = ParseQuery.getQuery(Notification.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<Notification>() {
            @Override
            public void done(List<Notification> notifications, ParseException e) {
                if (e == null) {
                    notificationList = (ArrayList<Notification>) notifications;
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

            headerText = (TextView) v.findViewById(R.id.notif_header);
            contentText = (TextView) v.findViewById(R.id.notif_content);
            dateText = (TextView) v.findViewById(R.id.notif_date);
            eventDateText = (TextView) v.findViewById(R.id.event_date_stamp);
            eventVenueText = (TextView) v.findViewById(R.id.event_venue_stamp);
            channelText = (TextView) v.findViewById(R.id.chennal_stamp);
        }
    }
}
