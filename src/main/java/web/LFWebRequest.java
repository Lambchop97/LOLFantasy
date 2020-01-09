package web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LFWebRequest {
    private static final int MAX_REQUESTS = 10;
    private static final int TIME_WINDOW = 2000;

    private static int requests = 0;

    private String URL;
    private Document document;

    public LFWebRequest(String URL){
        this.URL = URL;
        fetchHTML(this);
        if (document != null) {
            System.out.println(document.title());
        }
    }


    private static void fetchHTML(LFWebRequest request){
        if(requests >= MAX_REQUESTS){
            try {
                Thread.sleep(TIME_WINDOW);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            requests = 0;
        }

        try {
            request.document = Jsoup.connect(request.URL).get();
            requests++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
