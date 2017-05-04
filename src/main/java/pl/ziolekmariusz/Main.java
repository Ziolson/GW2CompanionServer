package pl.ziolekmariusz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Created by ziolson on 03.05.17.
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);

        Document doc = Jsoup.connect("https://wiki.guildwars2.com/wiki/Gem_Store").get();
        Elements newsHeadlines = doc.select("table.item.collapsible.table tr:has(td)");

        for (Element element : newsHeadlines) {
            System.out.println(element);
        }

//        System.out.println(newsHeadlines.text());
    }

}
