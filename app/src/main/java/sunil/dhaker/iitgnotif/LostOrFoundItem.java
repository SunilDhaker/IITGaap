package sunil.dhaker.iitgnotif;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by Sunil965@live.com(Sunil Dhaker) on 07-08-2014.
 */
@ParseClassName("LostOrFoundItem")
public class LostOrFoundItem extends ParseObject {

    public void setLOSTorFOUND(int type) {
        put("type", type);
    }

    public int getLOSTorFound() {
        return getInt("type");
    }

    public String getItemName() {
        return getString("item");
    }

    public void setItemName(String name) {
        put("item", name);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public void seMobileNo(String mn) {
        put("mn", mn);
    }

    public String getMobileNo() {
        return getString("mn");
    }

    public String getNameAndAdress() {
        return getString("nameAA");
    }

    public void setNameAndAdress(String nameAndAdress) {
        put("nameAA", nameAndAdress);
    }

    public void putPlace(String place) {
        put("place", place);
    }

    public String getPlace() {
        return getString("place");
    }

    public Date getDateTime() {
        return getDate("date");
    }

    public void setDateTime(Date date) {
        put("date", date);
    }

    public void setDateTimeString(String date) {
        put("date_string", date);
    }

    public String getDateString() {
        return getString("date_string");
    }
}
