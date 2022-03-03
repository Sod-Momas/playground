package cc.momas.sql.sqlite3;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
public abstract class DataSources {
    /**
     * momas.db 是数据文件名
     */
    private static String jdbcUrl = "jdbc:sqlite:momas.db";

    /**
     * 内存模式，连接断开的时候数据会消失
     */
//    private static String jdbcUrl = "jdbc:sqlite::memory:";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int write(String sql, String... args) {
        System.out.println(sql);
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
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

    public static <T> T read(Function<ResultSet, T> consumer, String sql) {
        System.out.println(sql);
        Objects.requireNonNull(consumer);
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return consumer.apply(rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
