package POJO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Last on 10/21/2016.
 */
public class ScrapedResult {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(String data) {
        text.add(data);
    }

    ArrayList<String> text = new ArrayList<String>();

}
