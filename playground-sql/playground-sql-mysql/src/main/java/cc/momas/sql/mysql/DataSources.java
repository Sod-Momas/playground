package cc.momas.sql.mysql;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
public abstract class DataSources {
    private static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String username = "root";
    private static final String password = "sod";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int write(String sql, String... args) {
        System.out.println(sql);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(sql, args)) {
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

    public static <T> T read(Function<ResultSet, T> consumer, String sql, String... args) {
        System.out.println(sql);
        Objects.requireNonNull(consumer);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(sql, args);
             ResultSet rs = ps.executeQuery()) {
            return consumer.apply(rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
