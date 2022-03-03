package cc.momas.sql.sqlite3;

import java.sql.*;

/**
 * SQLite3示例
 *
 * @author Sod-Momas
 * @since 2022/3/3
 */
public class Sample {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // sample.db 这个文件会存在 System.getProperty("user.dir") 当前文件夹下
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            statement.executeUpdate("insert into person values(3, 'Sod-Momas')");
            statement.executeUpdate("insert into person values(4, '莫弹弹')");
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
