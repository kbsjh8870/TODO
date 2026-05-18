package org.example.todo.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: DataSource 같은걸로 변경 예정
public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/todolist";
    private static final String user = "todolist_user";
    private static final String password = "rlawogur6685@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
