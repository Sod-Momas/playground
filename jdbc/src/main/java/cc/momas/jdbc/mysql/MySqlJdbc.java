package cc.momas.jdbc.mysql;

import java.sql.*;

public class MySqlJdbc {
    private final String databaseUrl = "jdbc:mysql://localhost:3306/test";
    private final String databaseUser = "root";
    private final String databasePassword = "root";

    public static void main(String[] args){
        try {
            new MySqlJdbc().test();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void test() throws ClassNotFoundException, SQLException {
        System.out.println("testing");

        String sql = "select count(*) from news";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
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
