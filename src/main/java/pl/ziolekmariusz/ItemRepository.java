package pl.ziolekmariusz;

import org.springframework.data.repository.CrudRepository;
import pl.ziolekmariusz.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    Item findByName(String name);
}
