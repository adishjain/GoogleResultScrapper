package crawler;

/**
 * Created by Last on 10/16/2016.
 */
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	public static void main(String[] args) throws IOException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter query");
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        query.replaceAll(" ","+");
        System.out.println("Enter the Date_Range you want to search the results for");
        System.out.println("Min:");
        String m_Date = sc.next();
        System.out.println("Max:");
        String max_Date = sc.next();
        System.out.println("Enter the type of tab :");
        System.out.println("News: nws \n Video: vid \n All: all");
        String tbm = sc.next();
        Date minDate = new Date(),maxDate = new Date();
        try {
            minDate = (Date)formatter.parse(m_Date);
            //System.out.println(""+minDate.getDate());
            maxDate = (Date)formatter.parse(max_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Custom url goes here
        if (tbm.equals("all")){
            //System.out.println("https://www.google.co.in/search?q="+query+"&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A"+minDate.getDate()+"%2F"+minDate.getMonth()+"%2F"+minDate.getYear()+"%2Ccd_max%3A"+maxDate.getDate());
            processPage("https://www.google.co.in/search?q="+query+"&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A"+minDate.getDate()+"%2F"+minDate.getMonth()+"%2F"+minDate.getYear()+"%2Ccd_max%3A"+maxDate.getDate()+"%2F"+maxDate.getMonth()+"%2F"+maxDate.getYear());
        }
        else{
            processPage("https://www.google.co.in/search?q="+query+"&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A"+minDate.getDate()+"%2F"+minDate.getMonth()+"%2F"+minDate.getYear()+"%2Ccd_max%3A"+maxDate.getDate()+"%2F"+maxDate.getMonth()+"%2F"+maxDate.getYear()+"&tbm="+tbm);
        }
    }

    public static void processPage(String URL) throws IOException {

            System.out.println("------ :\t\t" + URL+"\t\t: ------");
            Document doc = null;
            try {
                //Just an agent definition. Don't worry about it
                doc = Jsoup.connect(URL).userAgent(USER_AGENT_MAC).get();
                //Class of <a> tag under which results are placed
                Elements Tags = doc.select( "a.l._HId" );
                Iterator<Element> tagIterator = Tags.iterator();
                //link[] will contain the links to the result-webpages
                String[] link = new String[10];
                int i=0;
                while (tagIterator.hasNext() && i<10){
                    Element aTag = tagIterator.next();
                    link[i] = aTag.attr("href");
                    System.out.println(link[i]);
                    i++;
                }

                for (String linkI:link) {

                	try {
			 	System.out.println("**********************************["+linkI+"]**********************************");	
                     		navigatePage(linkI);
                	} catch (SocketTimeoutException ste) {
                	 ste.printStackTrace();
                	}

                }

            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }


    }

    private static void navigatePage(String linkI) throws IOException ,HttpStatusException {

        Document doc = Jsoup.connect(linkI).userAgent(USER_AGENT_MAC).get();
        //Selected the <p> tag here
        extractTag(doc,"p");
        extractTag(doc,"span");
   		extractTag(doc,"strong");
		extractTag(doc,"b");
		extractTag(doc,"pre");
		extractTag(doc,"summary");

    }

	private static void extractTag(Document doc,String tag) {

		System.out.println("----------------------------------[ "+tag+" ]----------------------------------");
		Elements tags = doc.select(tag);
        System.out.println(tags.text());

	}
}
