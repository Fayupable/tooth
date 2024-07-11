package Db;

import Db.Enum.EDbVarDayPart;
import Db.Enum.EDbVarItemName;
import Db.Exception.DbConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DbFunctions implements IDbFunctions {
    //^(100|[1-9]?[0-9])$ regex for 1-100
    private String item_user_name; //use,charge
    private int item_user_id;   //use,charge
    private int item_id;        //use,charge
    private EDbVarItemName item_name;   //use,charge
    private int item_battery;   //use
    private LocalTime item_use_time;    //use
    private LocalDate item_use_date;    //use
    private EDbVarDayPart item_use_day_part;   //use
    private int item_use_id;    //use
    private LocalDate item_charge_dete; //charge
    private LocalTime item_charge_time; //charge


    private static final String insert_item_use = "INSERT ITEM item_use (item_user_name,item_user_id,item_id,item_name,item_battery,item_use_time,item_use_date,item_use_day_part) VALUES (?,?,?,?,?,?,?,?)";
    private static final String insert_item_charge = "INSERT ITEM item_charge (item_user_name,item_user_id,item_id,item_name,item_charge_date,item_charge_time) VALUES (?,?,?,?,?,?)";
    private static final String select_item_use_by_id = "SELECT * FROM item_use WHERE item_use_id = ?";
    private static final String select_item_charge_by_id = "SELECT * FROM item_charge WHERE item_charge_id = ?";
    private static final String select_all_item_use = "SELECT * FROM item_use";
    private static final String select_all_item_charge = "SELECT * FROM item_charge";
    private static final String update_item_use = "UPDATE item_use SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_battery = ?, item_use_time = ?, item_use_date = ?, item_use_day_part = ? WHERE item_use_id = ?";
    private static final String update_item_charge = "UPDATE item_charge SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_charge_date = ?, item_charge_time = ? WHERE item_charge_id = ?";
    private static final String test = "INSERT INTO item_use (item_user_id) VALUES (?)"; // for testing




    public DbFunctions(String item_user_name, int item_user_id, int item_id, EDbVarItemName item_name, int item_battery, LocalTime item_use_time, LocalDate item_use_date, EDbVarDayPart item_use_day_part, int item_use_id) {
        this.item_user_name = item_user_name;
        this.item_user_id = item_user_id;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_battery = item_battery;
        this.item_use_time = item_use_time;
        this.item_use_date = item_use_date;
        this.item_use_day_part = item_use_day_part;
        this.item_use_id = item_use_id;
    }

    public DbFunctions(String item_user_name, int item_user_id, int item_id, EDbVarItemName item_name, LocalDate item_charge_dete, LocalTime item_charge_time) {
        this.item_user_name = item_user_name;
        this.item_user_id = item_user_id;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_charge_dete = item_charge_dete;
        this.item_charge_time = item_charge_time;
    }

    @Override
    public void insertItem(DbFunctions dbFunctions) throws DbConnectionException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnector.getConnection(); // Get a connection
            String sql = "INSERT INTO item_use (item_user_id) VALUES (?)"; // SQL statement
            pstmt = conn.prepareStatement(sql); // Prepare the statement
            pstmt.setInt(1, dbFunctions.getItem_user_id()); // Set item_user_id
            pstmt.executeUpdate(); // Execute the update
        } catch (SQLException e) {
            throw new DbConnectionException("Failed to insert item: " + e.getMessage(), e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close(); // Close the PreparedStatement
                } catch (SQLException e) {
                    throw new DbConnectionException("Failed to close PreparedStatement: " + e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public DbFunctions getFunctionById(int id) {
        return null;
    }

    @Override
    public List<DbFunctions> getAllFunctions() {
        return List.of();
    }

    @Override
    public void updateFunction(DbFunctions dbFunctions) {

    }

    @Override
    public void deleteFunction(int id) {

    }

    @Override
    public List<DbFunctions> getDataTypeToTable(String tableName) {
        return List.of();
    }

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

    public LocalDate getItem_charge_dete() {
        return item_charge_dete;
    }

    public void setItem_charge_dete(LocalDate item_charge_dete) {
        this.item_charge_dete = item_charge_dete;
    }

    public LocalTime getItem_charge_time() {
        return item_charge_time;
    }

    public void setItem_charge_time(LocalTime item_charge_time) {
        this.item_charge_time = item_charge_time;
    }
}