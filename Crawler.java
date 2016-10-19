package crawler;

/**
 * Created by Last on 10/16/2016.
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    public static void main(String[] args) throws IOException {
        //Custom url goes here
        processPage("https://www.google.co.in/search?q=MSFT&hl=en&gl=in&as_drrb=b&authuser=0&source=lnt&tbs=cdr%3A1%2Ccd_min%3A10%2F10%2F2011%2Ccd_max%3A10%2F10%2F2011&tbm=nws");
    }
    public static void processPage(String URL) throws IOException {
        
            System.out.println("------ :\t\t" + URL+"\t\t: ------");
            Document doc = null;
            try {
                //Just an agent definition. Don't worry about it
                doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
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

                for (String linkI:link
                     ) {
                    navigatePage(linkI);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }


    }

    private static void navigatePage(String linkI) throws IOException {
        Document doc = null;
        doc = Jsoup.connect(linkI).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
        //Selected the <p> tag here
        Elements Tags = doc.select( "p");
        System.out.println(Tags.outerHtml());
        System.out.println("-----------------------------------------------------");
    }
}
