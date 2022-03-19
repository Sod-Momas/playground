//package cc.momas.sd.es.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.lang.NonNull;
//import org.springframework.lang.NonNullApi;
//
//import java.util.List;
//
///**
// * @author Sod-Momas
// * @since 2022/3/6
// */
//@Configuration
//public class RestClientConfig extends AbstractElasticsearchConfiguration {
//
//    @Autowired
//    private ElasticsearchRestClientProperties properties;
//    @Autowired
//    private ObjectMapper objectMapper;
//    private final Logger log = LoggerFactory.getLogger(RestClientConfig.class);
//
//    @Override
//    @Bean
//    @NonNull
//    public RestHighLevelClient elasticsearchClient() {
//        try {
//            log.info("elasticsearch config properties(spring.elasticsearch.rest.) = {}", objectMapper.writeValueAsString(properties));
//        } catch (JsonProcessingException ignored) {
//        }
//        final List<String> uris = properties.getUris();
//        final String[] uriArray = uris.toArray(new String[uris.size()]);
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(uriArray)
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }
//
////Mono<IndexResponse> response = client.index(request ->
////
////        request.index("spring-data")
////                .id(randomID())
////                .source(singletonMap("feature", "reactive-client"));
////);
//
//
//}
