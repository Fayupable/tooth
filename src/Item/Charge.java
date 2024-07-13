package Item;

import Db.Enum.EDbVarItemName;

import java.time.LocalDate;
import java.time.LocalTime;

public class Charge extends Common{

    private LocalTime item_charge_time; //charge
    private LocalDate item_charge_date; //charge

    public Charge(String item_user_name, int item_user_id, int item_id, EDbVarItemName item_name, LocalTime item_charge_time, LocalDate item_charge_dete) {
        super(item_user_name, item_user_id, item_id, item_name);
        this.item_charge_time = item_charge_time;
        this.item_charge_date = item_charge_dete;

    }

    public LocalTime getItem_charge_time() {
        return item_charge_time;
    }

    public void setItem_charge_time(LocalTime item_charge_time) {
        this.item_charge_time = item_charge_time;
    }

    public LocalDate getItem_charge_date() {
        return item_charge_date;
    }

    public void setItem_charge_date(LocalDate item_charge_dete) {
        this.item_charge_date = item_charge_dete;
    }
}
