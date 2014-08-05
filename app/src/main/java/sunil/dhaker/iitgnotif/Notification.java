package sunil.dhaker.iitgnotif;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 04-08-2014.
 */
@ParseClassName("Notification")
public class Notification extends ParseObject {


    public Date getDate() {
        return getCreatedAt();
    }

    public String getID() {
        return getObjectId();
    }

    public String getHeader() {
        return getString("header");
    }

    public void setHeader(String header) {
        put("header", header);
    }

    public String getContent() {
        return getString("content");
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
        return getString("eventVenue");
    }

    public void setEventVenue(String venue) {
        put("eventVenue", venue);
    }

}
