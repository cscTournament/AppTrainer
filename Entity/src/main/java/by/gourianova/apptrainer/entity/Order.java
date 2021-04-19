package by.gourianova.apptrainer.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class Order extends Entity {
    private int id;
    private int appId;
    private int userId;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    private BigDecimal value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDateTime startRent) {
        this.startRent = startRent;
    }

    public LocalDateTime getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDateTime endRent) {
        this.endRent = endRent;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                getAppId() == order.getAppId() &&
                getUserId() == order.getUserId() &&
                Objects.equals(getStartRent(), order.getStartRent()) &&
                Objects.equals(getEndRent(), order.getEndRent()) &&
                Objects.equals(getValue(), order.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAppId(), getUserId(), getStartRent(), getEndRent(), getValue());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", appId=" + appId +
                ", userId=" + userId +
                ", startRent=" + startRent +
                ", endRent=" + endRent +
                ", value=" + value +
                "} " + super.toString();
    }
}
