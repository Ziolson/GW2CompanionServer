package pl.ziolekmariusz.model;

import java.util.ArrayList;

/**
 * Created by ziolson on 04.05.17.
 */
public class Item {
    private String name;
    private String url;
    private String image;
    private int gemCost;
    private int quantity;
    private String dollarOrEuroPrice;
    private String poundPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getGemCost() {
        return gemCost;
    }

    public void setGemCost(int gemCost) {
        this.gemCost = gemCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDollarOrEuroPrice() {
        return dollarOrEuroPrice;
    }

    public void setDollarOrEuroPrice(String dollarOrEuroPrice) {
        this.dollarOrEuroPrice = dollarOrEuroPrice;
    }

    public String getPoundPrice() {
        return poundPrice;
    }

    public void setPoundPrice(String poundPrice) {
        this.poundPrice = poundPrice;
    }

    @Override
    public String toString() {
        String text = "Name: " + name + "\n" + "Url: " + url + " \n" + "Image: " + image + "\n" + "Gem Price: " + gemCost + "\n"
                + "Quantity: " + quantity + "\n" + "Price: " + dollarOrEuroPrice + " or " + poundPrice;
        return text;
    }
}
