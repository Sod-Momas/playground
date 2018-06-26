package cc.momas.jdbc;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("testing");
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        PreparedStatement st = con.prepareStatement("select count(*) from news");
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getInt(1));
        }
        st.close();
        con.close();
        System.out.println("done");
    }
}
