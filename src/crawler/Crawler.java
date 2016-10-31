package crawler;

/**
 * Created by Last on 10/16/2016.
 */
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import fileHandling.SaveInFile;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import POJO.ScrapedResult;


public class Crawler {
    public static ArrayList<String> dates;

    public static int delay;
    public static String[] USER_AGENT_MAC = {
            "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0",
            "Mozilla/5.0 (compatible; U; ABrowse 0.6; Syllable) AppleWebKit/420+ (KHTML, like Gecko)",
            "Mozilla/5.0 (compatible; ABrowse 0.4; Syllable)",
            "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; Acoo Browser 1.98.744; .NET CLR 3.5.30729)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; SV1; Acoo Browser; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; Avant Browser)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB6; Acoo Browser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Acoo Browser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Acoo Browser; .NET CLR 2.0.50727; .NET CLR 1.1.4322)",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 4.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.2117.157 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.7 (KHTML, like Gecko) Comodo_Dragon/16.1.1.0 Chrome/16.0.912.63 Safari/535.7",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.30 (KHTML, like Gecko) Comodo_Dragon/12.1.0.0 Chrome/12.0.742.91 Safari/534.30",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Comodo_Dragon/4.1.1.11 Chrome/4.1.249.1042 Safari/532.5",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; x64; fr; rv:1.9.2.13) Gecko/20101203 Firebird/3.6.13",
            "Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.5) Gecko/20031026 Firebird/0.7",
            "Mozilla/5.0 (Windows; U; Win98; de-DE; rv:1.5a) Gecko/20030728 Mozilla Firebird/0.6.1",
            "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.4a) Gecko/20030425 Mozilla Firebird/0.6",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:2.0) Treco/20110515 Fireweb Navigator/2.4",
            "Mozilla/5.0 (X11; Linux x86_64; rv:17.0) Gecko/20121202 Firefox/17.0 Iceweasel/17.0.1",
            "Mozilla/5.0 (X11; Linux i686; rv:15.0) Gecko/20100101 Firefox/15.0.1 Iceweasel/15.0.1",
            "Mozilla/5.0 (X11; debian; Linux x86_64; rv:15.0) Gecko/20100101 Iceweasel/15.0",
            "Mozilla/5.0 (X11; Linux i686; rv:14.0) Gecko/20100101 Firefox/14.0.1 Iceweasel/14.0.1",
            "Mozilla/5.0 (X11; Linux i686; rv:14.0) Gecko/20100101 Firefox/14.0 Iceweasel/14.0",
            "Mozilla/5.0 (X11; Linux x86_64; rv:13.0) Gecko/20100101 Firefox/13.0.1 Iceweasel/13.0.1",
            "Mozilla/5.0 (X11; Linux x86_64; rv:13.0) Gecko/20100101 Firefox/13.0 Iceweasel/13.0"};
    //private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	public static ArrayList<ScrapedResult> input(String query, ScrapedResult scrapedResult, String date) {
        System.out.println("I'm here and the date is " + date.toString());
        ArrayList<ScrapedResult> resultList = new ArrayList<ScrapedResult>();
//        DateFormat formatter = new SimpleDateFormat("/MM/YYYY");
//        String wanted = date;
        try {
            String[] date_all = date.split("/");
            //wanted = (Date)formatter.parse(date);
//            System.out.println("HAHAHAHAHA "+wanted.toString());
            resultList = processPage(scrapedResult, date, "https://www.google.co.in/search?q=" + query + "&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A" + date_all[0] + "%2" + "F" + date_all[1] + "%2F" + date_all[2] + "%2Ccd_max%3A" + date_all[0] + "%2F" + date_all[1] + "%2F" + date_all[2] + "&tbm=nws");
            DateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
            String wanted = date;
            date_all = date.split("/");
            //wanted = (Date)formatter.parse(date);
            System.out.println("HAHAHAHAHA " + wanted.toString());
            resultList = processPage(scrapedResult, wanted, "https://www.google.co.in/search?q=" + query + "&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A" + date_all[0] + "%2" + "F" + date_all[1] + "%2F" + date_all[2] + "%2Ccd_max%3A" + date_all[0] + "%2F" + date_all[1] + "%2F" + date_all[2] + "&tbm=nws");

        }
            catch (IOException e) {
                e.printStackTrace();
            }
            return resultList;
        }

        public ArrayList<String> pickDates() {
            dates = new ArrayList<String>();
            System.out.println("Dates");

            //Scanner sc = new Scanner(System.in);
            //String date = sc.nextLine();

            //////////////////
            String dt = "18/03/2009";  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            while (!dt.toString().equals("31/10/2016")) {
                c.add(Calendar.DATE, 1);  // number of days to add
                dt = sdf.format(c.getTime());
                dates.add(dt.toString());
                System.out.println("" + dt.toString());
                System.out.println("111111111111111111111");

            }
                return dates;
            }

        public static void main (String[]args) throws IOException {
            ArrayList<ScrapedResult> resultList = new ArrayList<ScrapedResult>();
            Crawler crawler = new Crawler();


            //delay = 5;         // 5 seconds

            dates = crawler.pickDates();

            System.out.println("Dates size : " + dates.size());
            ScrapedResult pojo = null;
            SaveInFile saveInFile = new SaveInFile();
            Scanner sc = new Scanner(System.in);
            System.out.println("Query");
            String query = sc.next();
            query.replaceAll(" ", "+");
            sc.close();
            int i = 0;
            for (String date :
                    dates) {
                resultList = input(query, pojo, date);
                saveInFile.writeFile(resultList);
                for (ScrapedResult scr :
                        resultList) {
                    System.out.println("" + scr.getDate());
                    System.out.println("" + scr.getText());
                }
            }
        }

        public static ArrayList<ScrapedResult> processPage (ScrapedResult scrapedResult, String wanted, String url) throws
        IOException {

            ArrayList<ScrapedResult> resultList = new ArrayList<ScrapedResult>();
            System.out.println("------ :\t\t" + url + "\t\t: ------");
            Document doc = null;


            try {
                doc = Jsoup.connect(url).userAgent(USER_AGENT_MAC[new Random().nextInt(28)]).get();
                if (doc != null) {
                    Elements Tags = doc.select("a.l._HId");
                    Iterator<Element> tagIterator = Tags.iterator();
                    ArrayList<String> links = new ArrayList<String>();
                    while (tagIterator.hasNext()) {
                        Element aTag = tagIterator.next();
                        links.add(aTag.attr("href"));
                    }
                    for (String linkI : links) {
                        if (linkI != null) {
                            System.out.println("**********************************[" + linkI + "]**********************************");
                            resultList.add(navigatePage(scrapedResult, wanted, linkI));
                        }
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }

            try {
                Thread.sleep( new Random().nextInt(120) * 1000);
            } catch (InterruptedException ie) {

            }

            System.out.println("HA  " + resultList.size());
            return resultList;
        }

        private static ScrapedResult navigatePage (ScrapedResult scrapedResult, String wanted, String linkI){
            scrapedResult = new ScrapedResult();
            scrapedResult.setDate(wanted);
            Document doc = null;
            String resultText = null;
            try {
                doc = Jsoup.connect(linkI).userAgent(USER_AGENT_MAC[new Random().nextInt(28)]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc != null) {
                resultText = extractText(doc);
                //System.out.println("Extracted");
            }
            if (resultText != null) {
                scrapedResult.setText(resultText);
                //System.out.println("Result ________"+resultText);
            } else
                scrapedResult.setText("NULL");
            System.out.println("Final ______" + scrapedResult.getText());

            if (doc != null)
                resultText = extractText(doc);
            if (resultText != null)
                scrapedResult.setText(resultText);
            else
                scrapedResult.setText("NULL");

            return scrapedResult;
        }

        private static String extractText (Document doc){
            //System.out.println(doc.text());

            String result = "";
            result = result.concat(doc.select("p").text());
            result = result.concat(doc.select("h1").text());
            result = result.concat(doc.select("h2").text());
            result = result.concat(doc.select("strong").text());
            result = result.concat(doc.select("b").text());
            result = result.concat(doc.select("summary").text());

            return doc.text();
        }
}
