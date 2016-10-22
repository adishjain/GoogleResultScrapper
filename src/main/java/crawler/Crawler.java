package crawler;

/**
 * Created by Last on 10/16/2016.
 */
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import POJO.ScrapedResult;

public class Crawler {
    public static ArrayList<String> dates;

    private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	public static void input(String query, ScrapedResult scrapedResult, String date){
        DateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
        Date wanted = new Date();
        try {
            String[] date_all = date.split("/");
            wanted = (Date)formatter.parse(date);
            processPage(scrapedResult,wanted,"https://www.google.co.in/search?q="+query+"&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A"+date_all[0]+"%2F"+date_all[1]+"%2F"+date_all[2]+"%2Ccd_max%3A"+date_all[0]+"%2F"+date_all[1]+"%2F"+date_all[2]+"&tbm=nws");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public ArrayList<String> pickDates(){
        dates = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        while (!date.equals("-1")){
            dates.add(date);
            date = sc.nextLine();
        }
        //sc.close();

        return dates;
    }

    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler();
        dates = crawler.pickDates();
        ScrapedResult[] pojo = new ScrapedResult[dates.size()];
        Scanner sc = new Scanner(System.in);
        String query = sc.next();
        query.replaceAll(" ","+");
        sc.close();
        int i=0;
        for (String date:
                dates) {
            input(query,pojo[i++],date);
        }
    }

    public static void processPage(ScrapedResult scrapedResult, Date wanted, String URL) throws IOException {

            System.out.println("------ :\t\t" + URL+"\t\t: ------");
            Document doc = null;
            try {
                doc = Jsoup.connect(URL).userAgent(USER_AGENT_MAC).get();
                System.out.println(""+doc.html());
                Elements Tags = doc.select( "a.l._HId" );
                Iterator<Element> tagIterator = Tags.iterator();
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
                        navigatePage(scrapedResult,wanted,linkI);
                	} catch (SocketTimeoutException ste) {
                	 ste.printStackTrace();
                	}
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }
    }

    private static void navigatePage(ScrapedResult scrapedResult, Date wanted, String linkI) throws IOException ,HttpStatusException {
        scrapedResult = new ScrapedResult();
        scrapedResult.setDate(wanted);
        Document doc = Jsoup.connect(linkI).userAgent(USER_AGENT_MAC).get();
        String resultText = extractText(doc);
        scrapedResult.setText(resultText);
    }

	private static String extractText(Document doc) {
        System.out.println(doc.text());
        return doc.text();
	}
}
