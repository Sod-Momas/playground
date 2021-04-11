package cc.momas.apache.hc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * apache httpclient 演示
 *
 * @author Sod-Momas
 * @since 2021.04.11
 */
public class HttpclientApplication {
    private static Log log = LogFactory.getLog(HttpclientApplication.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public void main() throws Exception {
        login();
        post();
        conn();
    }

    private void conn() throws IOException {
        int i = 5;
//        HttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(4, TimeUnit.SECONDS);
        while (i > 0) {
            HttpClientConnectionManager manager = new BasicHttpClientConnectionManager();
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpResponse response = httpClient.execute(new HttpPost("http://localhost:8080"));
                System.out.println("==============================");
            }
            --i;
        }
    }

    /**
     * 简单的http post
     */
    private void post() {
        try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
            HttpResponse response = httpClient.execute(new HttpPost("http://localhost:8080"));
            log.info("httpclient send.");
            String responseEntity = mapper.readValue(response.getEntity().getContent(), String.class);
            log.info("httpclient received.");
            System.out.println(responseEntity);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * 使用 post 发送表单登录请求，然后获取返回的用户信息
     */
    private void login() throws IOException {
        String username = "sod";
        String password = "password";
        HttpPost httpPost = new HttpPost("http://localhost:8080");
        httpPost.setHeader(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.displayName());
        username = URLEncoder.encode(username, StandardCharsets.UTF_8.displayName());

        httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("password", password)
        )));
        // 接收请求响应体
        String entity = send(httpPost);
        mapEntity(entity);
    }

    private void mapEntity(String entity) throws IOException {
        BaseResponse<UserBaseDto> response = mapper.readValue(entity, new TypeReference<BaseResponse<UserBaseDto>>() {
        });
        System.out.println("============");
        UserBaseDto data = response.getData();
        System.out.println(data);
        System.out.println(data.getAvatar());
        System.out.println(data.getGender());
        System.out.println(data.getCreationTime());
        System.out.println(data.getAge());
        System.out.println(data.getTest());
    }


    private static String send(HttpRequestBase request) {
        log.debug("httpclient send start.");
        try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
            HttpResponse response = httpClient.execute(request);
            log.info("httpclient send end.");
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }
}
