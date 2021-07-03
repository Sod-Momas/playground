package cc.momas.sql.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
public class MysqlApplication {
    public static void main(String[] args) {
        testConnectionOk();
        testCreateTable();
        testWrite();
        testRead();
        testDropTable();
    }

    private static void testDropTable() {
        DataSources.write("DROP TABLE tb_test;");
    }

    private static void testRead() {
        final var result = DataSources.read((rs) -> {
            try {
                int index = 1;
                List<String> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getString(index));
                }
                return list;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }, "SELECT uuid_str FROM tb_test;");
        System.out.printf("query result= %s%n", result);
    }

    private static void testWrite() {
        String uuid = UUID.randomUUID().toString();
        System.out.printf("uuid=%s%n", uuid);
        final var success = DataSources.write("INSERT INTO tb_test(uuid_str)VALUE(?)", uuid);
        System.out.printf("insert data success? [%s]%n", success == 1);
    }

    private static void testCreateTable() {
        DataSources.write("CREATE TABLE tb_test(" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键'," +
                "uuid_str VARCHAR(64) NOT NULL DEFAULT '' COMMENT 'uuid'" +
                ") COMMENT='测试表';");
    }

    private static void testConnectionOk() {
        final int one = DataSources.read((rs) -> {
            try {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        }, "SELECT 1");
        System.out.println(one);
    }
}
