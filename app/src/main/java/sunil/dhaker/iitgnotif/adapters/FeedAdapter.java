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

import sunil.dhaker.iitgnotif.Notification;
import sunil.dhaker.iitgnotif.R;

/**
 * Created by SONY on 02-08-2014.
 */
public class FeedAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    ArrayList<Notification> notificationList;
    Context c;
    Date current;
    ArrayList<String> channels;
    boolean isForParticularChannel = false, isLoading = false;

    public FeedAdapter(Context c) {
        this.c = c;
        channels = new ArrayList<String>();
        notificationList = new ArrayList<Notification>();
        current = Calendar.getInstance().getTime();
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
            v = inflater.inflate(R.layout.notif_list_item, parent, false);
            mViewHolder = new ViewHolder(v);
            v.setTag(mViewHolder);
            mViewHolder = (ViewHolder) v.getTag();
            if (isForParticularChannel)
                v.findViewById(R.id.chennal_stamp).setVisibility(View.INVISIBLE);
            mViewHolder.headerText.setText(notif.getHeader());
            mViewHolder.contentText.setText(notif.getContent().trim());
            long min = current.getTime() / 60000 - notif.getDate().getTime() / 60000;
            if (min < 60)
                mViewHolder.dateText.setText(min + " min");
            else if (min < 60 * 24)
                mViewHolder.dateText.setText(min / 60 + " hr");
            else
                mViewHolder.dateText.setText(min / (60 * 24) + " days");
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
            mViewHolder.contentText.setText(notif.getContent().trim());
            long min = current.getTime() / 60000 - notif.getDate().getTime() / 60000;
            if (min < 60)
                mViewHolder.dateText.setText(min + " min");
            else if (min < 60 * 24)
                mViewHolder.dateText.setText(min / 60 + " hr");
            else
                mViewHolder.dateText.setText(min / (60 * 24) + " days");
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
            if (totalItemCount > 9) {
                loadMore(notificationList.get(totalItemCount - 1));
            }
        }
    }

    public void loadFeed() {
        ParseQuery<Notification> query = ParseQuery.getQuery(Notification.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.whereContainedIn("channel", channels);
        query.addDescendingOrder("createdAt");
        query.setLimit(15);
        query.findInBackground(new FindCallback<Notification>() {
            @Override
            public void done(List<Notification> notifications, ParseException e) {
                if (e == null) {
                    notificationList = (ArrayList<Notification>) notifications;
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void loadMore(Notification lastNotif) {
        ParseQuery<Notification> query = ParseQuery.getQuery(Notification.class);
        query.addDescendingOrder("createdAt");
        query.whereLessThan("createdAt", lastNotif.getDate());
        query.setLimit(10);
        if ((!isLoading)) {
            isLoading = true;
            Toast.makeText(c, "loading", Toast.LENGTH_SHORT).show();
            query.findInBackground(new FindCallback<Notification>() {
                @Override
                public void done(List<Notification> notifications, ParseException e) {

                    if (e == null) {
                        addIfNotExist((ArrayList<Notification>) notifications);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(c, R.string.loading_feed_error, Toast.LENGTH_SHORT);
                    }
                    isLoading = false;
                }
            });

        }
    }

    public void setIsForParticularChannel(boolean isForParticularChannel) {
        this.isForParticularChannel = isForParticularChannel;
    }

    public void addIfNotExist(ArrayList<Notification> notifs) {
        boolean ifExists = false;
        for (Notification notif : notifs) {
            ifExists = false;
            for (Notification notification : notificationList) {
                if (notif.getID() == notification.getID())
                    ifExists = true;
            }
            if (!ifExists) {
                notificationList.add(notif);
            }
        }
    }

    public void setChannel(String channel) {
        channels.add(channel);
    }

    public void setChannelList(ArrayList<String> channels) {
        this.channels.addAll(channels);
    }

    private class ViewHolder {
        public TextView headerText, contentText, dateText, eventDateText, channelText, eventVenueText;

        public ViewHolder(View v) {

            headerText = (TextView) v.findViewById(R.id.notif_header);
            contentText = (TextView) v.findViewById(R.id.notif_content);
            dateText = (TextView) v.findViewById(R.id.notif_date);
            eventDateText = (TextView) v.findViewById(R.id.lf_date_stamp);
            eventVenueText = (TextView) v.findViewById(R.id.event_venue_stamp);
            channelText = (TextView) v.findViewById(R.id.chennal_stamp);
        }
    }

}
