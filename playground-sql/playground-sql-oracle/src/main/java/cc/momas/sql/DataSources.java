package cc.momas.sql;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
public abstract class DataSources {
    private static final Logger log = Logger.getGlobal();
    private static final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/helowin";
    private static final String username = "sys as sysdba";
    private static final String password = "oracle";
//    private static final String username = "scott";
//    private static final String password = "tiger";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int write(String sql, String... args) {
        log.info(sql);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            if (args != null && 0 < args.length) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int writeInTransaction(String sql, Object... args) {
        log.info(sql);
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            // 关闭自动提交，开启事务
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            if (args != null && 0 < args.length) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            int result = ps.executeUpdate();
            // 提交事务
            connection.commit();
            return result;
        } catch (SQLException ex) {
            silentClose(ps);
            silentClose(connection);
            throw new RuntimeException(ex);
        }
    }

    public static <T> T read(Function<ResultSet, T> consumer, String sql, String... args) {
        log.info(sql);
        Objects.requireNonNull(consumer);
        ResultSet rs = null;
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            if (args != null && 0 < args.length) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            rs = ps.executeQuery();
            return consumer.apply(rs);
        } catch (SQLException ex) {
            silentClose(rs);
            throw new RuntimeException(ex);
        }
    }

    private static void silentClose(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "close JDBC Connection error.", e);
        }
    }
}
