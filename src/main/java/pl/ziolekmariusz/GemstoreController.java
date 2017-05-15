package pl.ziolekmariusz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.ziolekmariusz.model.Changes;
import pl.ziolekmariusz.model.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class GemstoreController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ChangesRepository changesRepository;

    @Autowired
    private GemstoreParser gemstoreParser;

    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public ArrayList<Item> getChangedItems(Date date) {
        Iterable<Changes> changedItems = changesRepository.findAllByModificationTimeGreaterThan(date);
        ArrayList<Item> items = new ArrayList<>();
        for (Changes changes : changedItems) {
            items.add(itemRepository.findOne(changes.getItemId()));
        }

        return items;
    }

    //run every 15 minutes
    @Scheduled(cron = "0 0/15 * * * *")
    private void findAndSaveChangesInItems() {
        System.out.println("--- " + new Date() + " --- Stared findAndSaveChanges");
        ArrayList<Changes> changedItems = new ArrayList<>();
        List<Item> allItemsFromWeb = gemstoreParser.parseAllItems();
        for (Item item : allItemsFromWeb) {
            Item itemFromDB = itemRepository.findByName(item.getName());
            if (itemFromDB != null)
                item.setId(itemFromDB.getId());

            if (!item.equals(itemFromDB)) {
                itemRepository.save(item);
                if (item.getId() == null)
                    item.setId(itemRepository.findByName(item.getName()).getId());

                changedItems.add(new Changes(item.getId()));
            }
        }
        changesRepository.save(changedItems);
    }
}
