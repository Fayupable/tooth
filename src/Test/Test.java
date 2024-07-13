package Test;

import Db.DbConnector;
import Db.DbFunctions;
import Db.Enum.EDbVarDayPart;
import Db.Enum.EDbVarItemName;
import Db.Exception.DbConnectionException;
import Item.Charge;
import Item.Use;
import Ui.ItemPage;
import Ui.MainPage;
import Ui.StatisticPage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) throws DbConnectionException, SQLException {
//        DbConnector.getConnection();
//        DbConnector.closeConnection();
//        EDbVarItemName.getById(1);
//        EDbVarItemName.printAll();
//        EDbVarDayPart.getById(1);
//        EDbVarDayPart.printAll();
//        DbFunctions dbFunctions = new DbFunctions("item_user_name", 1, 1, EDbVarItemName.ORAL_B_IO_5, 1, null, null, EDbVarDayPart.MORNING, 1);
//        dbFunctions.getFunctionById(1);

//        Use use = new Use("item_user_name", 1, 1, EDbVarItemName.ORAL_B_IO_5, 1, LocalTime.now(), LocalDate.now(), EDbVarDayPart.MORNING, 1);
//        System.out.println(use.getItem_battery());
//        Use use1 = new Use("item_user_name", 1, 1, EDbVarItemName.ORAL_B_AQUACARE_AGIZ_DUSU, 1, null, null, EDbVarDayPart.MORNING, 1);
//
//        Charge charge = new Charge("item_user_name", 1, 1, EDbVarItemName.ORAL_B_IO_5, null, null);
//        System.out.println(charge.getItem_charge_time());
//        Charge charge1 = new Charge("item_user_name", 1, 1, EDbVarItemName.ORAL_B_AQUACARE_AGIZ_DUSU, null, null);
//        System.out.println(charge1.getItem_charge_time());
//
//
//        DbFunctions dbFunctions= new DbFunctions();
//
//        dbFunctions.insertItem_use(use);

//        MainPage mainPage = new MainPage();
//        mainPage.setVisible(true);
        ItemPage itemPage = new ItemPage();
        itemPage.setVisible(true);

        StatisticPage statisticPage = new StatisticPage();
        statisticPage.setVisible(true);


    }
}
