package sunil.dhaker.iitgnotif;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 04-08-2014.
 */
@ParseClassName("Notification")
public class Notification extends ParseObject implements Serializable {


    public Date getDate() {
        return getCreatedAt();
    }

    public String getID() {
        return getObjectId();
    }

    public String getHeader() {
        if (getString("header") != null)
            return getString("header");
        else
            return "No Header";
    }

    public void setHeader(String header) {
        put("header", header);
    }

    public String getContent() {
        if (getString("content") != null)
            return getString("content");
        else
            return "No Content";
    }

    public void setContent(String content) {
        put("content", content);
    }

    public String getChannel() {
        return getString("channel");
    }

    public void setChannel(String channel) {
        put("channel", channel);
    }

    public Boolean getIsEvent() {
        return getBoolean("isEvent");
    }

    public void setIsEvent(Boolean isEvent) {
        put("isEvent", isEvent);
    }

    public Date getEventDate() {
        return super.getDate("eventDate");
    }

    public void setEventDate(Date date) {
        put("eventDate", date);
    }

    public String getEventVenue() {
        if (!getString("eventVenue").contentEquals(""))
            return getString("eventVenue");
        else
            return "No venue";
    }

    public void setEventVenue(String venue) {
        put("eventVenue", venue);
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public String getUserEmail() {
        if (get("user") == null) {
            return "Anonumouse user";
        } else {

            ParseUser u = (ParseUser) get("user");
            try {
                u.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("user" , u.getEmail());
            return u.getEmail();
        }
    }

    public  void setSenderName(String name){
        put("sender" , name);
    }
    public  String getSenderName(){
       return (String) get("sender");
    }
}
