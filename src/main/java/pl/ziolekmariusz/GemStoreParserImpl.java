package pl.ziolekmariusz;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.ziolekmariusz.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziolson on 06.05.17.
 */
public class GemStoreParserImpl implements GemStoreParser{

    @Override
    public List<Item> parseItems(Elements elements) {
        ArrayList<Item> items = new ArrayList<>();

        for (Element element : elements) {
            Item item = new Item();
            item.setName(getNameFromElement(element));
            item.setUrl(getUrlFromElement(element));
            item.setImage(getImageUrlFromElement(element));
            item.setGemCost(getGemCostFromElement(element));
            item.setQuantity(getQuantityFromElement(element));
            item.setDollarOrEuroPrice(getDollarOrEuroPriceFromElement(element));
            item.setPoundPrice(getPoundsPriceFromElement(element));
            items.add(item);
        }
        return items;
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
        final int LARGEST_IMAGE_URL = 2;
        String absoluteUrl = "https://wiki.guildwars2.com";
        String srcset = element.select("th img").first().attr("srcset");
        String[] imageUrls = srcset.split(" ");
        return absoluteUrl + imageUrls[LARGEST_IMAGE_URL];
    }

    // TODO: 06.05.17 Change names
    private int getGemCostFromElement(Element element) {
        final int PRICE_PER_ONE_ITEM = 0;
        String gemCost = element.select("td").first().text().replaceAll("\u00a0","").replaceAll(",","");
        return Integer.parseInt(gemCost.split(" ")[PRICE_PER_ONE_ITEM]);
    }

    private int getQuantityFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().first().text();
        return Integer.parseInt(dollarOrEuroPrice.split(" ")[0]);
    }

    private String getDollarOrEuroPriceFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().next().first().text();
        return dollarOrEuroPrice.split(" ")[0];
    }

    private String getPoundsPriceFromElement(Element element) {
        String dollarOrEuroPrice = element.select("td").next().next().next().first().text();
        return dollarOrEuroPrice.split(" ")[0];
    }
}
