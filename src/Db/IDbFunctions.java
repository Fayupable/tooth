package Db;

import Db.Exception.DbConnectionException;
import Item.Item;
import User.User;
import Item.Charge;
import Item.Use;


import java.sql.SQLException;
import java.util.List;

public interface IDbFunctions {



    void InsertItem(Item item) throws DbConnectionException, SQLException;
    void UpdateItem(Item item) throws DbConnectionException, SQLException;
    void DeleteItem(Item item) throws DbConnectionException, SQLException;
    List<Item> GetAllItems() throws DbConnectionException, SQLException;
    List<Item> SearchItem(String search) throws DbConnectionException, SQLException;
    Item GetItemById(int id) throws DbConnectionException, SQLException;

    void InsertUser(User user) throws DbConnectionException, SQLException;
    void UpdateUser(User user) throws DbConnectionException, SQLException;
    void DeleteUser(User user) throws DbConnectionException, SQLException;
    List<User> GetAllUsers() throws DbConnectionException, SQLException;
    List<User> SearchUser(String search) throws DbConnectionException, SQLException;
    User GetUserById(int id) throws DbConnectionException, SQLException;

    void InsertCharge(Charge charge) throws DbConnectionException, SQLException;
    void UpdateCharge(Charge charge) throws DbConnectionException, SQLException;
    void DeleteCharge(Charge charge) throws DbConnectionException, SQLException;
    List<Charge> GetAllCharges() throws DbConnectionException, SQLException;
    List<Charge> SearchCharge(String search) throws DbConnectionException, SQLException;
    Charge GetChargeById(int id) throws DbConnectionException, SQLException;

    void InsertUse(Use use) throws DbConnectionException, SQLException;
    void UpdateUse(Use use) throws DbConnectionException, SQLException;
    void DeleteUse(Use use) throws DbConnectionException, SQLException;
    List<Use> GetAllUses() throws DbConnectionException, SQLException;
    List<Use> SearchUse(String search) throws DbConnectionException, SQLException;
    Use GetUseById(int id) throws DbConnectionException, SQLException;


//    void insertItem(Common common) throws DbConnectionException, SQLException;
//
//
//    Common getFunctionById(int id) throws DbConnectionException;
//
//    List<DbFunctions> getAllFunctions();
//
//    void updateFunction(Common common) throws DbConnectionException;
//
//    void deleteFunction(Common common) throws DbConnectionException;
//
//    List<DbFunctions> getDataTypeToTable(String tableName);
//    List<Common> searchFunction(String search) throws DbConnectionException;
//
//    List<Charge> getChargeById(int id) throws DbConnectionException;
//    List<Charge> searchCharge(String search) throws DbConnectionException;
//    List<Charge> getAllCharge() throws DbConnectionException;
//
//    List<Use> getUseById(int id) throws DbConnectionException;
//    List<Use> searchUse(String search) throws DbConnectionException;
//    List<Use> getAllUse() throws DbConnectionException;




}
