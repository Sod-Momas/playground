//package cc.momas.sd.es.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
//import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
//import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
//
//import java.util.List;
//
///**
// * @author Sod-Momas
// * @since 2022/3/6
// */
////@Configuration
//public class ReactiveRestClientConfig extends AbstractReactiveElasticsearchConfiguration {
//
//    @Autowired
//    private ElasticsearchRestClientProperties properties;
//    @Autowired
//    private ObjectMapper objectMapper;
//    private final Logger log = LoggerFactory.getLogger(ReactiveRestClientConfig.class);
//
//
//    @Override
//    @Bean
//    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
//        try {
//            log.info("elasticsearch config properties(spring.elasticsearch.rest.) = {}", objectMapper.writeValueAsString(properties));
//        } catch (JsonProcessingException ignored) {
//        }
//        final List<String> uris = properties.getUris();
//        final String[] uriArray = uris.toArray(new String[uris.size()]);
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(uriArray)
//                .build();
//        return ReactiveRestClients.create(clientConfiguration);
//
//    }
//
////    IndexRequest request = new IndexRequest("spring-data")
////            .id(randomID())
////            .source(singletonMap("feature", "high-level-rest-client"))
////            .setRefreshPolicy(IMMEDIATE);
////
////    IndexResponse response = highLevelClient.index(request,RequestOptions.DEFAULT);
//}
