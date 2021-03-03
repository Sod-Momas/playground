package cc.momas.spring.webflux.r2dbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由配置
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@Configuration
public class ProductRouter {

    public static final String SAVE = "/route/product/save";
    public static final String SAVE_FORM = "/route/product/saveForm";
    public static final String SAVE_MANY = "/route/product/saveMany";
    public static final String FIND = "/route/product/find/{productId}";
    public static final String FIND_ALL = "/route/product/findAll";
    public static final String FIND_ALL_PAGE = "/route/product/findAllByPage";
    public static final String FIND_BY_CATEGORY = "/route/product/findByCategory";
    public static final String UPDATE = "/route/product/update/{productId}";
    public static final String DELETE = "/route/product/delete/{productId}";
    public static final String FIND_STOCK = "/route/product/stock/get/{productId}";
    public static final String GET_MORE = "/route/product/getMore";

    @Bean
    public RouterFunction<ServerResponse> routes(ProductWebHandler productWebHandler) {
        return RouterFunctions.route()
                .POST(SAVE, productWebHandler::save)
//                .POST(SAVE_FORM, RequestPredicates.accept(MediaType.APPLICATION_FORM_URLENCODED),myWebHandler::saveForm)
                .GET(FIND, productWebHandler::find)
                .DELETE(DELETE, productWebHandler::delete)
                .PUT(UPDATE, productWebHandler::update)
                .GET(FIND_ALL, productWebHandler::findAll)
                .GET(FIND_ALL_PAGE, productWebHandler::findAllByPage)
                .POST(FIND_BY_CATEGORY, productWebHandler::findByCategoryPage)
                .POST(SAVE_MANY, productWebHandler::saveMany)
//                .GET(FIND_STOCK, productWebHandler::findStock)
//                .GET(GET_MORE,myWebHandler::getMoreStream)
                .build();
    }
}