package Db;

import Db.Exception.DbConnectionException;
import Item.Item;
import User.User;
import Item.Charge;
import Item.Use;


import java.sql.SQLException;
import java.util.List;

public interface IDbFunctions {



    void insertItem(Item item) throws DbConnectionException, SQLException;
    void updateItem(Item item) throws DbConnectionException, SQLException;
    void deleteItem(Item item) throws DbConnectionException, SQLException;
    List<Item> getAllItems() throws DbConnectionException, SQLException;
    List<Item> searchItem(String search) throws DbConnectionException, SQLException;
    Item getItemById(int id) throws DbConnectionException, SQLException;

    void insertUser(User user) throws DbConnectionException, SQLException;
    void updateUser(User user) throws DbConnectionException, SQLException;
    void deleteUser(User user) throws DbConnectionException, SQLException;
    List<User> getAllUsers() throws DbConnectionException, SQLException;
    List<User> searchUser(String search) throws DbConnectionException, SQLException;
    User getUserById(int id) throws DbConnectionException, SQLException;
//    List<User> getUserById1(int id) throws DbConnectionException, SQLException;

    void insertCharge(Charge charge) throws DbConnectionException, SQLException;
    void updateCharge(Charge charge) throws DbConnectionException, SQLException;
    void deleteCharge(Charge charge) throws DbConnectionException, SQLException;
    List<Charge> getAllCharges() throws DbConnectionException, SQLException;
    List<Charge> searchCharge(String search) throws DbConnectionException, SQLException;
    List<Charge> getChargeById(int id) throws DbConnectionException, SQLException;


    void insertUse(Use use) throws DbConnectionException, SQLException;
    void updateUse(Use use) throws DbConnectionException, SQLException;
    void deleteUse(Use use) throws DbConnectionException, SQLException;
    List<Use> getAllUses() throws DbConnectionException, SQLException;
    List<Use> searchUse(String search) throws DbConnectionException, SQLException;
    Use getUseById(int id) throws DbConnectionException, SQLException;
    List<Use>getUseById1(int id) throws DbConnectionException, SQLException;


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
