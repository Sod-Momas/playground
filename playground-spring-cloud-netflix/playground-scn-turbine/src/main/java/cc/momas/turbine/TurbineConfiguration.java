package cc.momas.turbine;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
//import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sod-Momas
 * @since 2021-03-26
 */
@Configuration
@EnableTurbine
@EnableHystrixDashboard
//@EnableTurbineStream
public class TurbineConfiguration {
}
