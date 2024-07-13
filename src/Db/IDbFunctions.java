package Db;

import Db.Exception.DbConnectionException;
import Item.Common;

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


}
