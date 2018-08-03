package cc.momas.jdbc.test;

import org.junit.Test;

import java.sql.SQLException;

public class TestMain {

    @Test
    public void create() throws SQLException {
        new com.mysql.jdbc.Driver();
    }
}
