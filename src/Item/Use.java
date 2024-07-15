package Item;

import User.User;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class Use extends Item {
    private User user;
    private Item item;
    private LocalDate use_date;
    private int use_time;
    private int battery;
    private int use_id;


    public Use() {
    }
    public Use(User user, Item item, LocalDate use_date, int use_time, int battery) {
        this.user = user;
        this.item = item;
        this.use_date = use_date;
        this.use_time = use_time;
        this.battery = battery;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getUse_date() {
        return use_date;
    }

    public void setUse_date(LocalDate use_date) {
        this.use_date = use_date;
    }

    public Time getUse_time() {
        int hour = Integer.parseInt(String.valueOf(use_time));
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("Hour must be between 0 and 23.");
        }
        return Time.valueOf(hour + ":00:00");
    }

    public void setUse_time(int use_time) {
        this.use_time = use_time;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    @Override
    public String toString() {
        return "Use{" +
                "user=" + user +
                ", use_date=" + use_date +
                ", use_time=" + use_time +
                ", battery=" + battery +
                ", use_id=" + use_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Use use = (Use) o;
        return use_time == use.use_time && battery == use.battery && use_id == use.use_id && Objects.equals(user, use.user) && Objects.equals(use_date, use.use_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, use_date, use_time, battery, use_id);
    }
}
