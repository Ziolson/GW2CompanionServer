package pl.ziolekmariusz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import pl.ziolekmariusz.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GemstoreParser {

    private final static int SMALLEST_AMOUNT = 0;
    private final static int LARGEST_IMAGE = 2;
    private final static String ABSOLUTE_URL = "https://wiki.guildwars2.com";
    private final static String AVAILABLE_ITEMS_URL = "https://wiki.guildwars2.com/wiki/Gem_Store";
    private final static String UNAVAILABLE_ITEMS_URL = "https://wiki.guildwars2.com/wiki/Gem_Store/storage";

    public List<Item> parseAllItems() {
        List<Item> allItems = new ArrayList<>();
        List<Item> availableItems = parseItems(AVAILABLE_ITEMS_URL, true);
        List<Item> unavailableItems = parseItems(UNAVAILABLE_ITEMS_URL, false);

        if (availableItems != null)
            allItems.addAll(availableItems);

        if (unavailableItems != null)
            allItems.addAll(unavailableItems);

        return allItems;
    }

    private List<Item> parseItems(String url, boolean availability) {
        Elements elements;

        try {
            elements = getItemsFromWebsite(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<Item> items = new ArrayList<>();

        for (Element element : elements)
            items.add(createItemFromElement(element, availability));

        return items;
    }

    private Elements getItemsFromWebsite(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements itemsFromWebsite = doc.select("table.item.collapsible.table tr:has(td)");
        return itemsFromWebsite;
    }

    private Item createItemFromElement(Element element, boolean availability) {
        Item item = new Item();

        item.setName(getNameFromElement(element));
        item.setUrl(getUrlFromElement(element));
        item.setImage(getImageUrlFromElement(element));
        item.setGemCost(getGemCostFromElement(element));
        item.setQuantity(getQuantityFromElement(element));
        item.setAvailable(availability);
        item.setDollarOrEuroPrice(getDollarOrEuroPriceFromElement(element));
        item.setPoundPrice(getPoundsPriceFromElement(element));

        return item;
    }

    private String getNameFromElement(Element element) {
        String name = element.select("th").text();
        return name;
    }

    private String getUrlFromElement(Element element) {
        String url = element.select("th a").first().attr("abs:href");
        return url;
    }

    // TODO: 06.05.17 Change names, find a better way to generate an absolute url
    private String getImageUrlFromElement(Element element) {
        String srcset = element.select("th img").first().attr("srcset");
        String[] imageUrls = srcset.split(" ");
        return ABSOLUTE_URL + imageUrls[LARGEST_IMAGE];
    }

    // TODO: 06.05.17 Change names
    private int getGemCostFromElement(Element element) {
        String gemCost = element.select("td").first().text().replaceAll("\u00a0","").replaceAll(",","");
        return Integer.parseInt(gemCost.split(" ")[SMALLEST_AMOUNT]);
    }

    private int getQuantityFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().first().text();
        return Integer.parseInt(dollarOrEuroPrice.split(" ")[SMALLEST_AMOUNT]);
    }

    private String getDollarOrEuroPriceFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().next().first().text();
        return dollarOrEuroPrice.split(" ")[SMALLEST_AMOUNT];
    }

    private String getPoundsPriceFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().next().next().first().text();
        return dollarOrEuroPrice.split(" ")[SMALLEST_AMOUNT];
    }
}
