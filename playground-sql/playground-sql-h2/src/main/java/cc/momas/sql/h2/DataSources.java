package cc.momas.sql.h2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
public abstract class DataSources {
    // mem 是内存模式，
//    private static final String jdbcUrl = "jdbc:h2:mem:~/db_test";
    // jdbc:h2:~/test 会把数据库默认存到当前用户的用户文件夹下的 db_test.db 文件
    private static final String jdbcUrl = "jdbc:h2:~/db_test";
    private static final String username = "sa";
    private static final String password = "";

    static {
        try {
            Class.forName("org.h2.Driver");
//            addCleaner();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    private static void addCleaner() {
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try {
//                Files.deleteIfExists(Paths.get("~/db_test.db"));
//            } catch (IOException ignored) {
//                System.err.println("delete ~/db_test.db fail, please delete it yourself.");
//            }
//        }));
//    }

    public static int write(String sql, String... args) {
        System.out.println(sql);
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

    public static <T> T read(Function<ResultSet, T> consumer, String sql) {
        System.out.println(sql);
        Objects.requireNonNull(consumer);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return consumer.apply(rs);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
