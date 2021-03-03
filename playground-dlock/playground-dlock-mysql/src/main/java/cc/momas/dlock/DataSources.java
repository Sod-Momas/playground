package cc.momas.dlock;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

/**
 * 数据源工具类
 *
 * @author Sod-Momas
 * @since 2021-02-05
 */
public abstract class DataSources {
    private static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String username = "root";
    private static final String password = "sod";
    // 锁已经初始化
    private static boolean lockInited;

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

    public static void init() {
        if (lockInited) {
            return;
        }
        DataSources.write("CREATE TABLE IF NOT EXISTS tb_dlock(\n" +
                "lock_id    INT(11) NOT NULL AUTO_INCREMENT COMMENT '锁id',\n" +
                "lock_name  VARCHAR(128) NOT NULL DEFAULT '' COMMENT '锁名',\n" +
                "lock_desc  VARCHAR(128) DEFAULT '' COMMENT '锁备注信息',\n" +
                "update_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '锁更新时间',\n" +
                "PRIMARY KEY (lock_id),\n" +
                "UNIQUE KEY uidx_lock_name (lock_name) USING BTREE\n" +
                ") COMMENT = '分布式锁表';");
        lockInited = true;
    }

    /**
     * 尝试锁定指定方法
     *
     * @param bizName 方法名
     * @param remark  备注(可选)
     * @return true表示锁成功，false表示锁失败
     */
    public static boolean tryLock(String bizName, String remark) {
        try {
            final int write = write("INSERT INTO tb_dlock(lock_name,lock_desc) VALUES(?,?)", bizName, remark);
            return 0 < write;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 释放某个锁
     *
     * @param bizName 锁名
     */
    public static void releaseLock(String bizName) {
        try {
            final int write = write("DELETE FROM tb_dlock WHERE lock_name=?;", bizName);
            System.out.println("release lock success?[" + (0 < write) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理所有分布式锁
     */
    public static void cleanLock() {
        write("TRUNCATE TABLE tb_dlock");
    }
}
