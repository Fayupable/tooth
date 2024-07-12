package Item;

import Db.Enum.EDbVarDayPart;

import java.time.LocalDate;
import java.time.LocalTime;

public class Use extends Common{
    private int item_battery;   //use
    private LocalTime item_use_time;    //use
    private LocalDate item_use_date;    //use
    private EDbVarDayPart item_use_day_part;   //use
    private int item_use_id;    //use

    public int getItem_battery() {
        return item_battery;
    }

    public void setItem_battery(int item_battery) {
        this.item_battery = item_battery;
    }

    public LocalTime getItem_use_time() {
        return item_use_time;
    }

    public void setItem_use_time(LocalTime item_use_time) {
        this.item_use_time = item_use_time;
    }

    public LocalDate getItem_use_date() {
        return item_use_date;
    }

    public void setItem_use_date(LocalDate item_use_date) {
        this.item_use_date = item_use_date;
    }

    public EDbVarDayPart getItem_use_day_part() {
        return item_use_day_part;
    }

    public void setItem_use_day_part(EDbVarDayPart item_use_day_part) {
        this.item_use_day_part = item_use_day_part;
    }

    public int getItem_use_id() {
        return item_use_id;
    }

    public void setItem_use_id(int item_use_id) {
        this.item_use_id = item_use_id;
    }
}
