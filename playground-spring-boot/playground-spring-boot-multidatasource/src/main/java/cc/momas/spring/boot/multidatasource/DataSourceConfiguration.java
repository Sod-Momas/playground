package cc.momas.spring.boot.multidatasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author Sod-Momas
 * @since 2021/9/28
 */
@Configuration
public class DataSourceConfiguration {
    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    //    @Bean
    DataSource _dataSource() {
        log.info("initial datasource...");
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("Myniwo,1");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
        log.info("datasource initialed.");
        return dataSource;
    }

    @Bean
    AbstractRoutingDataSource dataSource() {
        DataSource dataSource = _dataSource();
        return new RoutingDataSource(dataSource);
    }

    static class RoutingDataSource extends AbstractRoutingDataSource {

        public RoutingDataSource(DataSource dataSource) {
            setTargetDataSources(Collections.singletonMap("ds", dataSource));
        }

        @Override
        protected Object determineCurrentLookupKey() {
            log.info("lookup datasource key");
            return "ds";
        }
    }

}
