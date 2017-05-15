package pl.ziolekmariusz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ziolekmariusz.GemstoreController;
import pl.ziolekmariusz.model.Item;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/gemstore")
public class GemstoreRestController {

    @Autowired
    private GemstoreController gemstoreController;

    @RequestMapping("/all")
    public @ResponseBody Iterable<Item> getAllItems() {
        return gemstoreController.getAllItems();
    }

    @RequestMapping("/changes")
    public @ResponseBody ArrayList<Item> getChangedItems(@RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return gemstoreController.getChangedItems(date);
    }
}
