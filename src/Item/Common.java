package Item;

import Db.Enum.EDbVarItemName;


public class Common {
    private String item_user_name; //use,charge
    private int item_user_id;   //use,charge
    private int item_id;        //use,charge
    private EDbVarItemName item_name;   //use,charge

    public String getItem_user_name() {
        return item_user_name;
    }

    public void setItem_user_name(String item_user_name) {
        this.item_user_name = item_user_name;
    }

    public int getItem_user_id() {
        return item_user_id;
    }

    public void setItem_user_id(int item_user_id) {
        this.item_user_id = item_user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public EDbVarItemName getItem_name() {
        return item_name;
    }

    public void setItem_name(EDbVarItemName item_name) {
        this.item_name = item_name;
    }
}




