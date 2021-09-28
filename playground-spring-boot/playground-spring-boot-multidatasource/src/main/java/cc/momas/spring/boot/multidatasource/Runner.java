package cc.momas.spring.boot.multidatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Sod-Momas
 * @since 2021/9/28
 */
@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private DataSource dataSource;
    private Logger logger = LoggerFactory.getLogger(Runner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 4; i++) {
            logger.info("running...");
            final Connection connection = dataSource.getConnection();
            final PreparedStatement ps = connection.prepareStatement("SELECT 1");
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final int result = rs.getInt(1);
                logger.info("result = " + result);
            }
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeConnection(connection);
            logger.info("run done.");
        }
    }
}
