package Item;

import java.time.LocalDate;
import java.time.LocalTime;

public class Charge extends Common{

    private LocalTime item_charge_time; //charge
    private LocalDate item_charge_dete; //charge

    public LocalTime getItem_charge_time() {
        return item_charge_time;
    }

    public void setItem_charge_time(LocalTime item_charge_time) {
        this.item_charge_time = item_charge_time;
    }

    public LocalDate getItem_charge_dete() {
        return item_charge_dete;
    }

    public void setItem_charge_dete(LocalDate item_charge_dete) {
        this.item_charge_dete = item_charge_dete;
    }
}
