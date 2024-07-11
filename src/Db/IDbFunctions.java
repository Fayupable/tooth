package Db;
import java.util.List;

public interface IDbFunctions {
    void insertItem(DbFunctions dbFunctions);
    DbFunctions getFunctionById(int id);
    List<DbFunctions> getAllFunctions();
    void updateFunction(DbFunctions dbFunctions);
    void deleteFunction(int id);
    List<DbFunctions> getDataTypeToTable(String tableName);
}
