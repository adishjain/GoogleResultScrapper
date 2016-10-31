package POJO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Last on 10/21/2016.
 */
public class ScrapedResult {
    String date;
    String text;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String data) {
        this.text += data;
    }

}
