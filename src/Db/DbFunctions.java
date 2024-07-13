package Db;

import Db.Enum.EDbVarDayPart;
import Db.Enum.EDbVarItemName;
import Db.Exception.DbConnectionException;
import Item.Charge;
import Item.Common;
import Item.Use;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions implements IDbFunctions {


    //charge_time , use_time
    private static final String insert_item_use = "INSERT ITEM use_time (item_user_name,item_user_id,item_id,item_name,item_battery,item_use_time,item_use_date,item_use_day_part,item_use_id) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String insert_item_charge = "INSERT ITEM charge_time (item_user_name,item_user_id,item_id,item_name,item_charge_date,item_charge_time) VALUES (?,?,?,?,?,?)";
    private static final String select_item_use_by_id = "SELECT * FROM use_time WHERE item_use_id = ?";
    private static final String select_item_charge_by_id = "SELECT * FROM charge_time WHERE item_charge_id = ?";
    private static final String select_all_item_use = "SELECT * FROM use_time";
    private static final String select_all_item_charge = "SELECT * FROM charge_time";
    private static final String update_item_use = "UPDATE use_time SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_battery = ?, item_use_time = ?, item_use_date = ?, item_use_day_part = ? WHERE item_use_id = ?";
    private static final String update_item_charge = "UPDATE charge_time SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_charge_date = ?, item_charge_time = ? WHERE item_charge_id = ?";
    private static final String delete_item_use = "DELETE FROM use_time WHERE item_use_id = ?";
    private static final String delete_item_charge = "DELETE FROM charge_time WHERE item_charge_id = ?";
    private static final String test = "INSERT INTO use_time (item_user_id) VALUES (?)"; // for testing


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
    public void insertItem_use(Common item) throws DbConnectionException, SQLException {
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;

        if (item instanceof Use) {
            Use use = (Use) item;
            psmt = conn.prepareStatement(insert_item_use);
            psmt.setString(1, use.getItem_user_name());
            psmt.setInt(2, use.getItem_user_id());
            psmt.setInt(3, use.getItem_id());
            psmt.setInt(4, use.getItem_name().getId());
            psmt.setInt(5, use.getItem_battery());
            psmt.setTime(6, Time.valueOf(use.getItem_use_time()));
            psmt.setDate(7, Date.valueOf(use.getItem_use_date()));
            psmt.setInt(8, use.getItem_use_day_part().getId());
            psmt.setInt(9, use.getItem_use_id());
            psmt.executeUpdate();
            conn.close();
            psmt.close();
        } else if (item instanceof Charge) {
            Charge charge = (Charge) item;
            psmt = conn.prepareStatement(insert_item_charge);
            psmt.setString(1, charge.getItem_user_name());
            psmt.setInt(2, charge.getItem_user_id());
            psmt.setInt(3, charge.getItem_id());
            psmt.setInt(4, charge.getItem_name().getId());
            psmt.setDate(5, Date.valueOf(charge.getItem_charge_date()));
            psmt.setTime(6, Time.valueOf(charge.getItem_charge_time()));
            psmt.executeUpdate();
            conn.close();
            psmt.close();
            {

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
    }


    @Override
    public Common getFunctionById(int id) throws DbConnectionException {
        Common common = new Common();
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = (PreparedStatement) conn.createStatement();
            rs = psmt.executeQuery("SELECT * FROM use_time WHERE item_use_id=" + id);
            while (rs.next()) {
                Common item = new Use(rs.getString("item_user_name"), rs.getInt("item_user_id"), rs.getInt("item_id"), EDbVarItemName.getById(rs.getInt("item_name")), rs.getInt("item_battery"), LocalTime.parse(rs.getString("item_use_time")), LocalDate.parse(rs.getString("item_use_date")), EDbVarDayPart.getById(rs.getInt("item_use_day_part")), rs.getInt("item_use_id"));
            }
            psmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return common;
    }

    @Override
    public List<DbFunctions> getAllFunctions() {

        return List.of();
    }

    @Override
    public void updateFunction(Common item) throws DbConnectionException {
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;
        if (item instanceof Use) {
            Use use = (Use) item;
            try {
                psmt = conn.prepareStatement(update_item_use);
                psmt.setString(1, use.getItem_user_name());
                psmt.setInt(2, use.getItem_user_id());
                psmt.setInt(3, use.getItem_id());
                psmt.setInt(4, use.getItem_name().getId());
                psmt.setInt(5, use.getItem_battery());
                psmt.setTime(6, Time.valueOf(use.getItem_use_time()));
                psmt.setDate(7, Date.valueOf(use.getItem_use_date()));
                psmt.setInt(8, use.getItem_use_day_part().getId());
                psmt.setInt(9, use.getItem_use_id());
                psmt.executeUpdate();
                conn.close();
                psmt.close();
            } catch (SQLException e) {
                throw new DbConnectionException("Failed to update item: " + e.getMessage(), e);
            }
        } else if (item instanceof Charge) {
            Charge charge = (Charge) item;
            try {
                psmt = conn.prepareStatement(update_item_charge);
                psmt.setString(1, charge.getItem_user_name());
                psmt.setInt(2, charge.getItem_user_id());
                psmt.setInt(3, charge.getItem_id());
                psmt.setInt(4, charge.getItem_name().getId());
                psmt.setDate(5, Date.valueOf(charge.getItem_charge_date()));
                psmt.setTime(6, Time.valueOf(charge.getItem_charge_time()));
                psmt.executeUpdate();
                conn.close();
                psmt.close();
            } catch (SQLException e) {
                throw new DbConnectionException("Failed to update item: " + e.getMessage(), e);
            }
        }


    }

    @Override
    public void deleteFunction(Common item) throws DbConnectionException {
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;
        if (item instanceof Use) {
            Use use = (Use) item;
            try {
                psmt = conn.prepareStatement(delete_item_use);
                psmt.setInt(1, use.getItem_use_id());
                psmt.executeUpdate();
                conn.close();
                psmt.close();
            } catch (SQLException e) {
                throw new DbConnectionException("Failed to delete item: " + e.getMessage(), e);
            }
        }

    }

    @Override
    public List<DbFunctions> getDataTypeToTable(String tableName) {
        return List.of();
    }

    @Override
    public List<Common> searchFunction(String search) throws DbConnectionException {
        List<Common> commonList = new ArrayList<Common>();
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = conn.prepareStatement("SELECT * FROM use_time WHERE item_user_name LIKE '%" + search + "%'");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Common item = new Use(rs.getString("item_user_name"), rs.getInt("item_user_id"), rs.getInt("item_id"), EDbVarItemName.getById(rs.getInt("item_name")), rs.getInt("item_battery"), LocalTime.parse(rs.getString("item_use_time")), LocalDate.parse(rs.getString("item_use_date")), EDbVarDayPart.getById(rs.getInt("item_use_day_part")), rs.getInt("item_use_id"));
                commonList.add(item);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbConnectionException("Failed to search item: " + e.getMessage(), e);
        }


        return List.of();
    }
    public List<Common> searchFunctionCharge(String search) throws DbConnectionException {
        List<Common> commonList = new ArrayList<Common>();
        Connection conn = DbConnector.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = conn.prepareStatement("SELECT * FROM charge_time WHERE item_user_name LIKE '%" + search + "%'");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Common item = new Charge(rs.getString("item_user_name"), rs.getInt("item_user_id"), rs.getInt("item_id"), EDbVarItemName.getById(rs.getInt("item_name")), LocalTime.parse(rs.getString("item_charge_time")), LocalDate.parse(rs.getString("item_charge_date")));
                commonList.add(item);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new DbConnectionException("Failed to search item: " + e.getMessage(), e);
        }

        return List.of();
    }

}