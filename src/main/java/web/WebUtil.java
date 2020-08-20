package web;

import io.CSVFile;
import io.CSVWriter;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import java.util.List;

public class WebUtil {
    public static final String liquipediaURL = "https://lol.gamepedia.com";

    public static void extractTeamsFromMainPage(LFWebRequest mainPage){
        Element teamsParentElement = mainPage.getDocument().selectFirst("div.tournament-rosters.maxteams-5");
        List<Element> teamLinks = teamsParentElement.select("div > a[href]");
        CSVFile file = new CSVFile("teams", 2, 10);
        int i = 0;
        for(Element e: teamLinks){
            for(Attribute a: e.attributes()){
                if(a.getKey().matches("href")){
                    System.out.println("link to team: " + liquipediaURL + a.getValue());
                    String[][] token = new String[1][2];
                    token[0][0] = a.getValue().split("\\.")[0].substring(1);
                    token[0][1] = liquipediaURL + a.getValue();
                    file.setRange(token, 0,i++);
                }
            }
        }
        CSVWriter.writeCSVFile(file);
    }

}
