package cc.momas.sql;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2021/7/3
 */
public class OracleApplication {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        testConnectionOk();
        testCreateTable();
        testWrite();
        testRead();
        testDropTable();
    }

    private static void testDropTable() {
        String ddl = "DROP TABLE T_PLANTS";
        DataSources.write(ddl);
        log.info("drop table done");
    }

    private static void testRead() {
        String sql = "SELECT plant_id,common_name FROM T_PLANTS";
        final var result = DataSources.read((rs) -> {
            try {
                List<Map<String, Serializable>> list = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Serializable> obj = new HashMap<>();
                    obj.put("plant_id", rs.getInt("plant_id"));
                    obj.put("common_name", rs.getString("common_name"));

                    list.add(obj);
                    //Map.of("plant_id", rs.getInt("plant_id"), "common_name", rs.getString("common_name"));
                }
                return list;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }, sql);
        log.info(() -> String.format("query result= %s", result));
    }

    private static void testWrite() {
        for (int i = 0; i < 2; i++) {
            int id = i;
            String name = id + UUID.randomUUID().toString();
            log.info(() -> String.format("id=%s,name=%s", id, name));
            var success = DataSources.writeInTransaction("INSERT INTO T_PLANTS VALUES (?, ?)", String.valueOf(id), name);
            log.info(() -> String.format("insert data success? [%s]", success == 1));
        }
    }

    private static void testCreateTable() {
        String ddl = "CREATE TABLE T_PLANTS( plant_id NUMBER PRIMARY KEY, common_name VARCHAR2(64))";
        DataSources.write(ddl);
        log.info("created table T_PLANTS.");
    }

    private static void testConnectionOk() {
        String sql = "SELECT 1 FROM DUAL";
        final int one = DataSources.read((rs) -> {
            try {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, sql);
        log.info("connection ok? " + (one == 1));
    }
}
