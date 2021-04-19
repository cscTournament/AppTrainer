package by.gourianova.apptrainer.entity;


import java.util.Objects;

public class Offer extends Entity {

    private int id;
    private String description;
    private int appId;

    public Offer(){

    }

    public Offer(int id, String description, int appId){
        this.id=id;
        this.description=description;
        this.appId=appId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer  offer = (Offer) o;
        return getId() == offer.getId() &&
                Objects.equals(getDescription(), offer.getDescription()) &&
                 Objects.equals(getAppId(), offer.getAppId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getAppId());
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", description=" + description +
                ", appId=" + appId +
                "} " + super.toString();
    }


}