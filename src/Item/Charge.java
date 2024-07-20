package Item;

import User.User;

import java.time.LocalDate;
import java.util.Objects;


public class Charge extends Item{
    private User user;
    private LocalDate charge_date;
    private int charge_time;
    private int charge_id;

    public Charge() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCharge_date() {
        return charge_date;
    }

    public void setCharge_date(LocalDate charge_date) {
        this.charge_date = charge_date;
    }

    public int getCharge_time() {
        return charge_time;
    }

    public void setCharge_time(int charge_time) {
        this.charge_time = charge_time;
    }

    public int getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(int charge_id) {
        this.charge_id = charge_id;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "user=" + user +
                ", charge_date=" + charge_date +
                ", charge_time=" + charge_time +
                ", charge_id=" + charge_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Charge charge = (Charge) o;
        return charge_time == charge.charge_time && charge_id == charge.charge_id && Objects.equals(user, charge.user) && Objects.equals(charge_date, charge.charge_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, charge_date, charge_time, charge_id);
    }
}
