package Db;

import Db.Exception.DbConnectionException;
import Item.Charge;
import Item.Common;
import Item.Use;

import java.sql.SQLException;
import java.util.List;

public interface IDbFunctions {
    void insertItem_use(Common common) throws DbConnectionException, SQLException;


    Common getFunctionById(int id) throws DbConnectionException;

    List<DbFunctions> getAllFunctions();

    void updateFunction(Common common) throws DbConnectionException;

    void deleteFunction(Common common) throws DbConnectionException;

    List<DbFunctions> getDataTypeToTable(String tableName);
    List<Common> searchFunction(String search) throws DbConnectionException;

    List<Charge> getChargeById(int id) throws DbConnectionException;
    List<Charge> searchCharge(String search) throws DbConnectionException;
    List<Charge> getAllCharge() throws DbConnectionException;

    List<Use> getUseById(int id) throws DbConnectionException;
    List<Use> searchUse(String search) throws DbConnectionException;
    List<Use> getAllUse() throws DbConnectionException;




}
