package venter.mircea.onlinestore.model.dto;

public class ProductDtoNoId {

    private String name;
    private String description;
    private Double price;
    private Double rating;
    private Integer itemsInStock;
    private String image;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(Integer itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
