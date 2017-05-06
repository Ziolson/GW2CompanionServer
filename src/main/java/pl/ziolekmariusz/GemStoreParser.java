package pl.ziolekmariusz;

import org.jsoup.select.Elements;
import pl.ziolekmariusz.model.Item;

import java.util.List;

/**
 * Created by ziolson on 04.05.17.
 */
public interface GemStoreParser {

    public List<Item> parseItems(Elements elements);
}
