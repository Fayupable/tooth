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

    private DbConnector dbConnector = new DbConnector();
    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;
    private User user;
    private Item item;
    private Charge charge;
    private Use use;

    private static final String loginQuery = "SELECT * FROM User WHERE user_name = ?";
    private static final String GET_USER_QUERY = "SELECT * FROM User WHERE user_name = ?";


    //Item
    private static final String insert_item = "INSERT INTO Item(item_name, item_type) VALUES (?,?)";
    private static final String update_item = "UPDATE Item SET item_name = ?, item_type = ? WHERE item_id = ?";
    private static final String delete_item = "DELETE FROM Item WHERE item_id = ?";
    private static final String select_all_items = "SELECT * FROM Item";
    private static final String search_item = "SELECT * FROM Item WHERE item_name LIKE ?";
    private static final String select_item_by_id = "SELECT * FROM User_Inventory WHERE item_id = ?";
    private static final String select_items_by_user_inventory =
            "SELECT i.item_id, i.item_name, i.item_type " +
                    "FROM User_Inventory ui " +
                    "JOIN Item i ON ui.item_id = i.item_id " +
                    "WHERE ui.user_id = ?";

    //user_inventory
    private static final String insert_user_inventory = "INSERT INTO User_Inventory(user_id, item_id, quantity) VALUES (?, ?, ?)";


    //User
    private static final String insert_user = "INSERT INTO User(user_name, user_surname) VALUES (?,?)";
    private static final String update_user = "UPDATE User SET user_name = ?, user_surname = ? WHERE user_id = ?";
    private static final String delete_user = "DELETE FROM User WHERE user_id = ?";
    private static final String select_all_users = "SELECT * FROM User";
    private static final String search_user = "SELECT * FROM User WHERE user_name LIKE ?";

    //Charge
    private static final String insert_charge = "INSERT INTO Charge(user_id, item_id, charge_time,charge_date) VALUES (?,?,?,?)";
    private static final String update_charge = "UPDATE Charge SET user_id = ?, item_id = ?, charge_time = ?, charge_date = ? WHERE charge_id = ?";
    private static final String delete_charge = "DELETE FROM Charge WHERE charge_id = ?";
    private static final String select_all_charges = "SELECT * FROM Charge";
    private static final String search_charge = "SELECT * FROM Charge WHERE charge_time LIKE ?";
    private static final String select_charge_by_id = "SELECT * FROM Charge WHERE user_id = ?";

    //Use
    private static final String insert_use = "INSERT INTO `Use`(user_id, item_id, use_date,use_time,battery) VALUES (?,?,?,?,?)";
    private static final String update_use = "UPDATE `Use` SET user_id = ?, item_id = ?, use_date = ?, use_time = ?, battery = ? WHERE use_id = ?";
    private static final String delete_use = "DELETE FROM `Use` WHERE use_id = ?";
    private static final String select_all_uses = "SELECT * FROM `Use`";
    private static final String search_use = "SELECT * FROM `Use` WHERE use_time LIKE ?";
    private static final String select_use_by_id = "SELECT * FROM `Use` WHERE user_id = ?";


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

//    @Override
//    public void insertItem(Item item) throws DbConnectionException, SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            conn = DbConnector.getConnection();
//            stmt = conn.prepareStatement(insert_item);
//            stmt.setInt(1, item.getId());
//            stmt.setString(2, item.getName());
//            stmt.setString(3, String.valueOf(item.getType()));
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (stmt != null) stmt.close();
//            if (conn != null) conn.close();
//        }
//    }
//
//    @Override
//    public void updateItem(Item item) throws DbConnectionException, SQLException {
//        Connection conn = DbConnector.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("UPDATE Item SET item_name = '" + item.getName() + "', item_type = '" + item.getType() + "' WHERE item_id = " + item.getId());
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public void deleteItem(Item item) throws DbConnectionException, SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            conn = DbConnector.getConnection();
//            String sql = "DELETE FROM Item WHERE item_id = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, item.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error executing DeleteItem: " + e.getMessage(), e);
//        } finally {
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing statement: " + e.getMessage());
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing connection: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//    public List<Item> getAllItems() throws DbConnectionException, SQLException {
//        List<Item> items = new ArrayList<>();
//        Connection conn = DbConnector.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Item");
//            while (rs.next()) {
//                Item item = new Item();
//                item.setId(rs.getInt("item_id"));
//                item.setName(rs.getString("item_name"));
//                item.setType(ItemType.valueOf(rs.getString("item_type")));
//                items.add(item);
//            }
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return items;
//    }
//
//    @Override
//    public List<Item> searchItem(String search) throws DbConnectionException, SQLException {
//        List<Item> items = new ArrayList<>();
//        Connection conn = DbConnector.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Item WHERE item_name LIKE '%" + search + "%'");
//            while (rs.next()) {
//                Item item = new Item();
//                item.setId(rs.getInt("item_id"));
//                item.setName(rs.getString("item_name"));
//                item.setType(ItemType.valueOf(rs.getString("item_type")));
//                items.add(item);
//            }
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return items;
//
//    }
//
//    @Override
//    public Item getItemById(int id) throws DbConnectionException, SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Item item = null;
//        try {
//            conn = DbConnector.getConnection();
//            String sql = "SELECT * FROM Item WHERE item_id = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, id);
//            rs = stmt.executeQuery();
//            if (rs.next()) {
//                item = new Item();
//                item.setId(rs.getInt("item_id"));
//                item.setName(rs.getString("item_name"));
//                item.setType(ItemType.valueOf(rs.getString("item_type")));
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (rs != null) rs.close();
//            if (stmt != null) stmt.close();
//            if (conn != null) conn.close();
//        }
//        return item;
//    }


    @Override
    public void addItemToUserInventory(int userId, int itemId, int quantity) throws SQLException, DbConnectionException {
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insert_user_inventory)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, itemId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void insertItem(Item item) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(insert_item);
        pstmt.setString(1, item.getName());
        pstmt.setString(2, String.valueOf(item.getType()));
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void updateItem(Item item) throws DbConnectionException, SQLException {
        conn = dbConnector.getConnection();
        pstmt = conn.prepareStatement(update_item);
        pstmt.setString(1, item.getName());
        pstmt.setString(2, String.valueOf(item.getType()));
        pstmt.setInt(3, item.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void deleteItem(Item item) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(delete_item);
        pstmt.setInt(1, item.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public List<Item> getAllItems() throws DbConnectionException, SQLException {
        List<Item> items = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(select_all_items);
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("item_id"));
            item.setName(rs.getString("item_name"));
            item.setType(ItemType.valueOf(rs.getString("item_type")));
            items.add(item);
        }
        stmt.close();
        conn.close();
        return items;

    }

    @Override
    public List<Item> searchItem(String search) throws DbConnectionException, SQLException {
        List<Item> items = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(search_item);
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("item_id"));
            item.setName(rs.getString("item_name"));
            item.setType(ItemType.valueOf(rs.getString("item_type")));
            items.add(item);
        }
        stmt.close();
        conn.close();
        return items;

    }


    public List<Item> getItemsByUserInventory(int userId) throws DbConnectionException, SQLException {
        List<Item> items = new ArrayList<>();
        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(select_items_by_user_inventory)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("item_name"));
                item.setType(ItemType.valueOf(rs.getString("item_type")));
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public Item getItemById(int id) throws DbConnectionException, SQLException {
        return null;
    }

    @Override
    public void insertUser(User user) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(insert_user);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void updateUser(User user) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(update_user);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setInt(3, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void deleteUser(User user) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(delete_user);
        pstmt.setInt(1, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public List<User> getAllUsers() throws DbConnectionException, SQLException {
        List<User> users = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(select_all_users);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            user.setSurname(rs.getString("user_surname"));
            users.add(user);
        }
        stmt.close();
        conn.close();
        return users;
    }

    @Override
    public List<User> searchUser(String search) throws DbConnectionException, SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = DbConnector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(search_user);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            user.setSurname(rs.getString("user_surname"));
            users.add(user);
        }
        stmt.close();
        conn.close();
        return users;
    }

    @Override
    public User getUserById(int id) throws DbConnectionException, SQLException {
        return null;
    }

    @Override
    public void insertCharge(Charge charge) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(insert_charge);
        pstmt.setInt(1, charge.getUser_id());
        pstmt.setInt(2, charge.getItem_id());
        pstmt.setInt(3, charge.getCharge_time());
        pstmt.setString(4, charge.getCharge_date().toString());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void updateCharge(Charge charge) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(update_charge);
        pstmt.setInt(1, charge.getUser_id());
        pstmt.setInt(2, charge.getItem_id());
        pstmt.setInt(3, charge.getCharge_time());
        pstmt.setString(4, charge.getCharge_date().toString());
        pstmt.setInt(5, charge.getCharge_id());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    @Override
    public void deleteCharge(Charge charge) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(delete_charge);
        pstmt.setInt(1, charge.getCharge_id());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    @Override
    public List<Charge> getAllCharges() throws DbConnectionException, SQLException {
        List<Charge> charges = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(select_all_charges);
        while (rs.next()) {
            Charge charge = new Charge();
            charge.setCharge_id(rs.getInt("charge_id"));
            charge.setUser_id(rs.getInt("user_id"));
            charge.setItem_id(rs.getInt("item_id"));
            charge.setCharge_time(rs.getInt("charge_time"));
            charge.setCharge_date(rs.getDate("charge_date").toLocalDate());
            charges.add(charge);
        }
        stmt.close();
        conn.close();
        return charges;
    }

    @Override
    public List<Charge> searchCharge(String search) throws DbConnectionException, SQLException {
        List<Charge> charges = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(search_charge);
        while (rs.next()) {
            Charge charge = new Charge();
            charge.setCharge_id(rs.getInt("charge_id"));
            charge.setUser_id(rs.getInt("user_id"));
            charge.setItem_id(rs.getInt("item_id"));
            charge.setCharge_time(rs.getInt("charge_time"));
            charge.setCharge_date(rs.getDate("charge_date").toLocalDate());
            charges.add(charge);
        }
        stmt.close();
        conn.close();
        return charges;
    }

    public Charge getChargesByUserId(User user) throws DbConnectionException, SQLException {
        List<Charge> charges = new ArrayList<>();

        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(select_charge_by_id)) {

            pstmt.setInt(1, user.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Charge charge = new Charge();
                    charge.setCharge_id(rs.getInt("charge_id"));
                    charge.setUser_id(rs.getInt("user_id"));
                    charge.setItem_id(rs.getInt("item_id"));
                    charge.setCharge_time(rs.getInt("charge_time"));
                    charge.setCharge_date(rs.getDate("charge_date").toLocalDate());
                    charges.add(charge);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database query error: " + e.getMessage());
        }
        return charges.get(0);

    }

    @Override
    public void insertUse(Use use) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(insert_use);
        pstmt.setInt(1, use.getUser_id());
        pstmt.setInt(2, use.getItem_id());
        pstmt.setDate(3, Date.valueOf(use.getUse_date()));
        pstmt.setInt(4, use.getUse_time());
        pstmt.setInt(5, use.getBattery());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void updateUse(Use use) throws DbConnectionException, SQLException {
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(update_use);
        pstmt.setInt(1, use.getUser_id());
        pstmt.setInt(2, use.getItem_id());
        pstmt.setDate(3, Date.valueOf(use.getUse_date()));
        pstmt.setInt(4, use.getUse_time());
        pstmt.setInt(5, use.getBattery());
        pstmt.setInt(6, use.getUse_id());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public void deleteUse(Use use) throws DbConnectionException, SQLException {

    }

    @Override
    public List<Use> getAllUses() throws DbConnectionException, SQLException {
        List<Use> uses = new ArrayList<>();
        conn = DbConnector.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(select_all_uses);
        while (rs.next()) {
            Use use = new Use();
            use.setUse_id(rs.getInt("use_id"));
            use.setUser_id(rs.getInt("user_id"));
            use.setItem_id(rs.getInt("item_id"));
            use.setUse_time(rs.getInt("use_time"));
            use.setUse_date(rs.getDate("use_date").toLocalDate());
            use.setBattery(rs.getInt("battery"));
            uses.add(use);
        }
        return uses;

    }

    @Override
    public List<Use> searchUse(String search) throws DbConnectionException, SQLException {
        return List.of();
    }


    @Override
    public Use getUseById(int id) throws DbConnectionException, SQLException {
        return null;
    }

    @Override
    public List<Use> getUseById1(int id) throws DbConnectionException, SQLException {
        List<Use> uses = new ArrayList<>();
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(select_use_by_id);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Use use = new Use();
            use.setUse_id(rs.getInt("use_id"));
            use.setUser_id(rs.getInt("user_id"));
            use.setItem_id(rs.getInt("item_id"));
            use.setUse_time(rs.getInt("use_time"));
            use.setUse_date(rs.getDate("use_date").toLocalDate());
            use.setBattery(rs.getInt("battery"));
            uses.add(use);
        }
        return uses;

    }

    @Override
    public List<Charge> getChargeById(int id) throws DbConnectionException, SQLException {
        List<Charge> charges = new ArrayList<>();
        conn = DbConnector.getConnection();
        pstmt = conn.prepareStatement(select_charge_by_id);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Charge charge = new Charge();
            charge.setCharge_id(rs.getInt("charge_id"));
            charge.setUser_id(rs.getInt("user_id"));
            charge.setItem_id(rs.getInt("item_id"));
            charge.setCharge_time(rs.getInt("charge_time"));
            charge.setCharge_date(rs.getDate("charge_date").toLocalDate());
            charges.add(charge);
        }
        return charges;
    }

    public boolean Login(String username) throws DbConnectionException, SQLException {
        boolean loginSuccessful = false;

        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(loginQuery)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login successful");
                    loginSuccessful = true;
                } else {
                    System.out.println("Login failed");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database query error: " + e.getMessage());
        }

        return loginSuccessful;
    }


    public User getUser(String username) throws DbConnectionException, SQLException {
        User user = null;

        try (Connection conn = DbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(GET_USER_QUERY)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("user_name"));
                    user.setSurname(rs.getString("user_surname"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database query error: " + e.getMessage());
        }

        return user;
    }
}

