package cc.momas.sql.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sod-Momas
 * @since 2021-02-21
 */
public class MssqlApplication {
    // 用户名密码验证
    private final String databaseUrl = "jdbc:sqlserver://localhost:1433\\SQLEXPRESS;databaseName=master;user=sa;password=sa;";
    // Windows 集成身份验证
//    private final String databaseUrl = "jdbc:sqlserver://BOOKA\\SQLEXPRESS;databaseName=master;integratedSecurity=true;enableSSL=false;";
    private final String databaseUser = "sa";
    private final String databasePassword = "sa";
    private final String SQL_CREATE_DB = "CREATE DATABASE TestData  \nGO";
    private final String SQL_CREATE_TB = "USE TestData  \nGO\n" +
            "CREATE TABLE dbo.Products  \n" +
            "   (ProductID int PRIMARY KEY NOT NULL,  \n" +
            "   ProductName varchar(25) NOT NULL,  \n" +
            "   Price money NULL,  \n" +
            "   ProductDescription varchar(max) NULL)  ";
    private final String SQL_INSERT_DATA = "INSERT dbo.Products " +
            "(ProductID, ProductName, Price, ProductDescription)  \n" +
            " VALUES (1, 'Clamp', 12.48, 'Workbench clamp')," +
            "(1,'Sod-Momas',1.1,'Momas clamp')";
    private final String SQL_SELECT_DATA = "SELECT * FROM  dbo.Products";
    private final String SQL_DROP_DB = "DROP TABLE dbo.Products";

    public static void main(String[] args) {
        try {
            System.out.println("test start");
            new MssqlApplication().test();
            System.out.println("test done");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void test() throws SQLException, ClassNotFoundException {
        // JDBC 4后不再需要手动注册驱动
//         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try (Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)) {
//            createDB(con);
            createTable(con);
            insertData(con);
            selectData(con);
            dropTable(con);
        }
    }

    private void dropTable(Connection con) throws SQLException {
        final var ps = con.prepareStatement(SQL_DROP_DB);
        final var execute = ps.execute();
        System.out.println("drop table result:" + execute);
    }

    private void selectData(Connection con) throws SQLException {

        var st = con.prepareStatement(SQL_SELECT_DATA);
        var rs = st.executeQuery();
        var objList = new ArrayList<HashMap<String, Object>>();
        while (rs.next()) {
            var obj = new HashMap<String, Object>();
            obj.put("ProductID", rs.getInt("ProductID"));
            obj.put("ProductName", rs.getString("ProductName"));
            obj.put("Price", rs.getDouble("Price"));
            obj.put("ProductDescription", rs.getString("ProductDescription"));
            objList.add(obj);
        }
        System.out.println(objList);
        st.close();
    }

    private void insertData(Connection con) throws SQLException {
        final var ps = con.prepareStatement(SQL_INSERT_DATA);
        final var update = ps.executeUpdate();
        System.out.println("insert data result:" + update);
    }

    private void createTable(Connection con) throws SQLException {
        final var ps = con.prepareStatement(SQL_CREATE_TB);
        final var execute = ps.execute();
        System.out.println("create table result:" + execute);
    }

    private void createDB(Connection con) throws SQLException {
        final var ps = con.createStatement();
        final var execute = ps.execute(SQL_CREATE_DB);
        System.out.println("create DB result:" + execute);
    }

}
