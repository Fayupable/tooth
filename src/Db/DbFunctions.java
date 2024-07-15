package Db;

import Db.Exception.DbConnectionException;
import Item.Enum.ItemType;
import Item.Item;
import User.User;
import Item.Charge;
import Item.Use;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions implements IDbFunctions {


    //charge_time , use_time
//    private static final String insert_item_use = "INSERT ITEM use_time (item_user_name,item_user_id,item_id,item_name,item_battery,item_use_time,item_use_date,item_use_day_part,item_use_id) VALUES (?,?,?,?,?,?,?,?,?)";
//    private static final String insert_item_charge = "INSERT ITEM charge_time (item_user_name,item_user_id,item_id,item_name,item_charge_date,item_charge_time) VALUES (?,?,?,?,?,?)";
//    private static final String select_item_use_by_id = "SELECT * FROM use_time WHERE item_use_id = ?";
//    private static final String select_item_charge_by_id = "SELECT * FROM charge_time WHERE item_charge_id = ?";
//    private static final String select_all_item_use = "SELECT * FROM use_time";
//    private static final String select_all_item_charge = "SELECT * FROM charge_time";
//    private static final String update_item_use = "UPDATE use_time SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_battery = ?, item_use_time = ?, item_use_date = ?, item_use_day_part = ? WHERE item_use_id = ?";
//    private static final String update_item_charge = "UPDATE charge_time SET item_user_name = ?, item_user_id = ?, item_id = ?, item_name = ?, item_charge_date = ?, item_charge_time = ? WHERE item_charge_id = ?";
//    private static final String delete_item_use = "DELETE FROM use_time WHERE item_use_id = ?";
//    private static final String delete_item_charge = "DELETE FROM charge_time WHERE item_charge_id = ?";
//    private static final String test = "INSERT INTO use_time (item_user_id) VALUES (?)"; // for testing
    private static final String insert_item = "INSERT INTO Item(item_id, item_name, item_type) VALUES (?,?,?)";


    // date regex ^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$
    //time regex ^([01][0-9]|2[0-3])[-.:]([0-5][0-9])[-.:]([0-5][0-9])$
    //regex for 24 hour time ^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$
    //^(100|[1-9]?[0-9])$ regex regex for 1-100
    //^[0-9]$|^[1-9][0-9]$|^(100)$ regex for 1-100

    public DbFunctions() {

    }
    /*
     Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT customer (adi,soyadi) VALUES ('" + contract.getName() + "','" + contract.getSurname() + "')");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     */

    @Override
    public void InsertItem(Item item) throws DbConnectionException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbConnector.getConnection();
            stmt = conn.prepareStatement(insert_item);
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setString(3, String.valueOf(item.getType()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public void UpdateItem(Item item) throws DbConnectionException, SQLException {
        Connection conn = DbConnector.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Item SET item_name = '" + item.getName() + "', item_type = '" + item.getType() + "' WHERE item_id = " + item.getId());
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void DeleteItem(Item item) throws DbConnectionException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbConnector.getConnection();
            String sql = "DELETE FROM Item WHERE item_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing DeleteItem: " + e.getMessage(), e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    public List<Item> GetAllItems() throws DbConnectionException, SQLException {
        List<Item> items = new ArrayList<>();
        Connection conn = DbConnector.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Item");
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("item_name"));
                item.setType(ItemType.valueOf(rs.getString("item_type")));
                items.add(item);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public List<Item> SearchItem(String search) throws DbConnectionException, SQLException {
        List<Item> items = new ArrayList<>();
        Connection conn = DbConnector.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Item WHERE item_name LIKE '%" + search + "%'");
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("item_name"));
                item.setType(ItemType.valueOf(rs.getString("item_type")));
                items.add(item);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;

    }

    @Override
    public Item GetItemById(int id) throws DbConnectionException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Item item = null;
        try {
            conn = DbConnector.getConnection();
            String sql = "SELECT * FROM Item WHERE item_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("item_name"));
                item.setType(ItemType.valueOf(rs.getString("item_type")));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return item;
    }

    @Override
    public void InsertUser(User user) throws DbConnectionException, SQLException {

    }

    @Override
    public void UpdateUser(User user) throws DbConnectionException, SQLException {

    }

    @Override
    public void DeleteUser(User user) throws DbConnectionException, SQLException {

    }

    @Override
    public List<User> GetAllUsers() throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public List<User> SearchUser(String search) throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public User GetUserById(int id) throws DbConnectionException, SQLException {
        return null;
    }

    @Override
    public void InsertCharge(Charge charge) throws DbConnectionException, SQLException {

    }

    @Override
    public void UpdateCharge(Charge charge) throws DbConnectionException, SQLException {

    }

    @Override
    public void DeleteCharge(Charge charge) throws DbConnectionException, SQLException {

    }

    @Override
    public List<Charge> GetAllCharges() throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public List<Charge> SearchCharge(String search) throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public Charge GetChargeById(int id) throws DbConnectionException, SQLException {
        return null;
    }

    @Override
    public void InsertUse(Use use) throws DbConnectionException, SQLException {

    }

    @Override
    public void UpdateUse(Use use) throws DbConnectionException, SQLException {

    }

    @Override
    public void DeleteUse(Use use) throws DbConnectionException, SQLException {

    }

    @Override
    public List<Use> GetAllUses() throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public List<Use> SearchUse(String search) throws DbConnectionException, SQLException {
        return List.of();
    }

    @Override
    public Use GetUseById(int id) throws DbConnectionException, SQLException {
        return null;
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


}