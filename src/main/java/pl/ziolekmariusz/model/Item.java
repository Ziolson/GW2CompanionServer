package pl.ziolekmariusz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "gem_store_items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private String image;
    private int gemCost;
    private int quantity;
    private boolean available;
    private String dollarOrEuroPrice;
    private String poundPrice;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (gemCost != item.gemCost) return false;
        if (quantity != item.quantity) return false;
        if (available != item.available) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (!name.equals(item.name)) return false;
        if (!url.equals(item.url)) return false;
        if (!image.equals(item.image)) return false;
        if (!dollarOrEuroPrice.equals(item.dollarOrEuroPrice)) return false;
        return poundPrice.equals(item.poundPrice);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + gemCost;
        result = 31 * result + quantity;
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + dollarOrEuroPrice.hashCode();
        result = 31 * result + poundPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String text = "Name: " + name + "\n" + "Url: " + url + " \n" + "Image: " + image + "\n" + "Gem Price: " + gemCost + "\n"
                + "Quantity: " + quantity + "\n" + "Price: " + dollarOrEuroPrice + " or " + poundPrice;
        return text;
    }
}
