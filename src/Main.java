import Db.DbFunctions;
import Db.Exception.DbConnectionException;
import User.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, DbConnectionException {
        DbFunctions dbFunctions = new DbFunctions();
        List<User> users = dbFunctions.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }
}