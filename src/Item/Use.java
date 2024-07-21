package Item;

import User.User;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class Use extends Item {
    private int user_id;
    private int item_id;
    private LocalDate use_date;
    private int use_time;
    private int battery;
    private int use_id;


    public Use() {
    }
    public Use(int user_id, int item_id, LocalDate use_date, int use_time, int battery) {
        this.user_id = user_id;
        this.item_id = item_id;
        this.use_date = use_date;
        this.use_time = use_time;
        this.battery = battery;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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
                "user_id=" + user_id +
                ", item_id=" + item_id +
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
        return user_id == use.user_id && item_id == use.item_id && use_time == use.use_time && battery == use.battery && use_id == use.use_id && Objects.equals(use_date, use.use_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, item_id, use_date, use_time, battery, use_id);
    }
}
