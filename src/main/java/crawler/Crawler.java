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

    private static final String USER_AGENT_MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	public static ArrayList<ScrapedResult> input(String query, ScrapedResult scrapedResult, String date){
        ArrayList<ScrapedResult> resultList = new ArrayList<ScrapedResult>();
        DateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
        String wanted = date;
        try {
            String[] date_all = date.split("/");
            //wanted = (Date)formatter.parse(date);
            System.out.println("HAHAHAHAHA "+wanted.toString());
            resultList = processPage(scrapedResult,wanted,"https://www.google.co.in/search?q="+query+"&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A"+date_all[0]+"%2" + "F"+date_all[1]+"%2F"+date_all[2]+"%2Ccd_max%3A"+date_all[0]+"%2F"+date_all[1]+"%2F"+date_all[2]+"&tbm=nws");
       }
//       catch (ParseException e) {
//            e.printStackTrace();
//        }
        catch (IOException e) {
                e.printStackTrace();
            }
            return resultList;
    }

    public ArrayList<String> pickDates(){
        dates = new ArrayList<String>();
        System.out.println("Dates");
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
        ArrayList<ScrapedResult> resultList = new ArrayList<ScrapedResult>();
        Crawler crawler = new Crawler();

        delay = 10;         // 10 seconds

//        10/10/2014
//        17/07/2000
//        15/10/2016
//        -1

        dates = crawler.pickDates();
        ScrapedResult pojo = null;
        SaveInFile saveInFile = new SaveInFile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Query");
        String query = sc.next();
        query.replaceAll(" ","+");
        sc.close();
        int i=0;
        for (String date:
                dates) {
            resultList = input(query,pojo,date);
            saveInFile.writeFile(resultList);
            for (ScrapedResult scr:
                    resultList) {
                System.out.println(""+scr.getDate());
                System.out.println(""+scr.getText());
            }
        }
//        System.out.println("Here");
    }

    public static ArrayList<ScrapedResult> processPage(ScrapedResult scrapedResult, String wanted, String URL) throws IOException {

            ArrayList<ScrapedResult> resultList= new ArrayList<ScrapedResult>();
            System.out.println("------ :\t\t" + URL+"\t\t: ------");
            Document doc = null;


            try {
                doc = Jsoup.connect(URL).userAgent(USER_AGENT_MAC).get();
                //System.out.println(""+doc.html());
                if (doc!=null) {
                    Elements Tags = doc.select("a.l._HId");
                    Iterator<Element> tagIterator = Tags.iterator();
//                    String[] link = new String[10];
                    ArrayList<String> links = new ArrayList<String>();
//                    int i = 0;
                    while (tagIterator.hasNext()) {
                        Element aTag = tagIterator.next();
                        links.add(aTag.attr("href"));
                    }
                    for (String linkI : links) {
                        if (linkI!=null) {
                            System.out.println("**********************************[" + linkI + "]**********************************");
                            resultList.add(navigatePage(scrapedResult, wanted, linkI));
                        }
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }

            try{
                Thread.sleep(delay * 1000);
            }catch(InterruptedException ie){
                
            }

        System.out.println("HA  "+resultList.size());
            return resultList;
    }

    private static ScrapedResult navigatePage(ScrapedResult scrapedResult, String wanted, String linkI) {
        scrapedResult = new ScrapedResult();
        scrapedResult.setDate(wanted);
        Document doc = null;
        String resultText = null;
        try {
            doc = Jsoup.connect(linkI).userAgent(USER_AGENT_MAC).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc!=null)
            resultText = extractText(doc);
        if(resultText!=null)
            scrapedResult.setText(resultText);
        else
            scrapedResult.setText("NULL");
        return scrapedResult;
    }

	private static String extractText(Document doc) {
        //System.out.println(doc.text());
        return doc.text();
	}
}
