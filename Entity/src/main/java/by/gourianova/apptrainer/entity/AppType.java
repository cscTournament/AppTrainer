package by.gourianova.apptrainer.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class AppType extends Entity {
    private int id;
    private String type;
    private BigDecimal price;
    private String image;

    public AppType() {
    }

    public AppType(int id, String type, BigDecimal price, String image) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppType)) return false;
        AppType appType = (AppType) o;
        return getId() == appType.getId() &&
                Objects.equals(getType(), appType.getType()) &&
                Objects.equals(getPrice(), appType.getPrice()) &&
                Objects.equals(getImage(), appType.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getPrice(), getImage());
    }

    @Override
    public String toString() {
        return "AppType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                "} " + super.toString();
    }
}
