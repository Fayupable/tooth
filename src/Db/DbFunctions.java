package Db;

import Db.Enum.EDbVarDayPart;
import Db.Enum.EDbVarItemName;
import Db.Exception.DbConnectionException;
import Db.Exception.DbDataNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DbFunctions implements IDbFunctions {



    private static final String insert_item_use = "INSERT ITEM item_use (item_user_name,item_user_id,item_id,item_name,item_battery,item_use_time,item_use_date,item_use_day_part) VALUES (?,?,?,?,?,?,?,?)";
    private static final String insert_item_charge = "INSERT ITEM item_charge (item_user_name,item_user_id,item_id,item_name,item_charge_date,item_charge_time) VALUES (?,?,?,?,?,?)";
    private static final String select_item_use_by_id = "SELECT * FROM item_use WHERE item_use_id = ?";
    private static final String select_item_charge_by_id = "SELECT * FROM item_charge WHERE item_charge_id = ?";
    private static final String select_all_item_use = "SELECT * FROM item_use";
    private static final String select_all_item_charge = "SELECT * FROM item_charge";
    private static final String update_item_use = "UPDATE item_use SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_battery = ?, item_use_time = ?, item_use_date = ?, item_use_day_part = ? WHERE item_use_id = ?";
    private static final String update_item_charge = "UPDATE item_charge SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_charge_date = ?, item_charge_time = ? WHERE item_charge_id = ?";
    private static final String test = "INSERT INTO item_use (item_user_id) VALUES (?)"; // for testing


    // date regex ^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$
    //time regex ^([01][0-9]|2[0-3])[-.:]([0-5][0-9])[-.:]([0-5][0-9])$
    //regex for 24 hour time ^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$
    //^(100|[1-9]?[0-9])$ regex regex for 1-100
    //^[0-9]$|^[1-9][0-9]$|^(100)$ regex for 1-100

    public DbFunctions() {

    }





    /*
    public void insert(CustomerContract contract) {
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT customer (adi,soyadi) VALUES ('" + contract.getName() + "','" + contract.getSurname() + "')");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     */

    @Override
    public void insertItem(DbFunctions dbFunctions) throws DbConnectionException {
        Connection conn = DbConnector.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(insert_item_use);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbConnectionException("Failed to insert item: " + e.getMessage(), e);

        }
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DbConnector.getConnection(); // Get a connection
//            String sql = test; // SQL statement
//            pstmt = conn.prepareStatement(sql); // Prepare the statement
//            pstmt.setInt(1, dbFunctions.getItem_user_id()); // Set item_user_id
//            pstmt.executeUpdate(); // Execute the update
//        } catch (SQLException e) {
//            throw new DbConnectionException("Failed to insert item: " + e.getMessage(), e);
//        } finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close(); // Close the PreparedStatement
//                } catch (SQLException e) {
//                    throw new DbConnectionException("Failed to close PreparedStatement: " + e.getMessage(), e);
//                }
//            }
//        }
    }

    @Override
    public DbFunctions getFunctionById(int id) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs=null;
//        try{
//            conn = DbConnector.getConnection();
//            String sql = select_item_use_by_id;
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, id);
//            rs=pstmt.executeQuery();
//            if (rs.next()){
//                String item_user_name = rs.getString("item_user_name");
//                int item_user_id = rs.getInt("item_user_id");
//                int item_id = rs.getInt("item_id");
//                EDbVarItemName item_name = EDbVarItemName.getById(rs.getInt("item_name"));
//                int item_battery = rs.getInt("item_battery");
//                LocalTime item_use_time = LocalTime.parse(rs.getString("item_use_time"));
//                LocalDate item_use_date = LocalDate.parse(rs.getString("item_use_date"));
//                EDbVarDayPart item_use_day_part = EDbVarDayPart.getById(rs.getInt("item_use_day_part"));
//                int item_use_id = rs.getInt("item_use_id");
//                LocalTime item_charge_time = LocalTime.parse(rs.getString("item_charge_time"));
//                LocalDate item_charge_date = LocalDate.parse(rs.getString("item_charge_date"));
//                return new DbFunctions(item_user_name, item_user_id, item_id, item_name, item_battery, item_use_time, item_use_date, item_use_day_part, item_use_id);
//
//
//            }
//
//        } catch (DbConnectionException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

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

    }