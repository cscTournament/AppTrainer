package by.gourianova.apptrainer.entity;

import java.util.Objects;


public class HttpAddress extends Entity {
    private int id;
    private String web_shop;
    private String location;

    public HttpAddress() {
    }

    public HttpAddress(int id, String web_shop, String location) {
        this.id = id;
        this.web_shop = web_shop;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeb_shop() {
        return web_shop;
    }

    public void setWeb_shop(String web_shop) {
        this.web_shop = web_shop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpAddress)) return false;
        HttpAddress httpAddress = (HttpAddress) o;
        return getId() == httpAddress.getId() &&
                Objects.equals(getWeb_shop(), httpAddress.getWeb_shop()) &&
                Objects.equals(getLocation(), httpAddress.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWeb_shop(), getLocation());
    }

    @Override
    public String toString() {
        return "HttpAddress{" +
                "id=" + id +
                ", web_shop='" + web_shop + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
