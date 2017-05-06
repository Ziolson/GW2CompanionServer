package pl.ziolekmariusz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ziolekmariusz.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ziolson on 03.05.17.
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);

        Random random = new Random();
        GemStoreParserImpl gemStoreParser = new GemStoreParserImpl();

        Document doc = Jsoup.connect("https://wiki.guildwars2.com/wiki/Gem_Store").get();
        Elements newsHeadlines = doc.select("table.item.collapsible.table tr:has(td)");

        List<Item> itemList = gemStoreParser.parseItems(newsHeadlines);
        System.out.print(itemList.get(random.nextInt(itemList.size())).toString());

//        for (Element element : newsHeadlines) {
//            System.out.println(element);
//        }

//        System.out.println(newsHeadlines.text());
    }

}
