package cc.momas.jdbc.microsoft;

import java.sql.*;

/**
 * 用于测试微软家的数据库
 */
public class MsSqlServerJdbc {
    // 用户名密码验证
    private final String databaseUrl = "jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=sa;";
    // Windows 集成身份验证
//    private final String databaseUrl = "jdbc:sqlserver://localhost;databaseName=test;integratedSecurity=true;";
    private final String databaseUser = "sa";
    private final String databasePassword = "sa";

    public static void main(String[] args) {
        try {
            new MsSqlServerJdbc().test();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void test() throws ClassNotFoundException, SQLException {
        System.out.println("testing");

        String sql = "select count(*) from test";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
        st.close();
        con.close();

        System.out.println("done");
    }
}
