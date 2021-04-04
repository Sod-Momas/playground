package cc.momas.scn.gw.zuul;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 熔断处理,调用失败的时候会回落到这里的逻辑
 *
 * @author Sod-Momas
 * @since 2021-01-30
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new MyResponseProvider(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return new MyResponseProvider(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static class MyResponseProvider implements ClientHttpResponse {
        final HttpStatus status;

        private MyResponseProvider(HttpStatus status) {
            Objects.requireNonNull(status);
            this.status = status;
        }

        @Override
        public HttpStatus getStatusCode() {
            return status;
        }

        @Override
        public int getRawStatusCode() {
            return status.value();
        }

        @Override
        public String getStatusText() {
            return status.getReasonPhrase();
        }

        @Override
        public void close() {
        }

        @Override
        public InputStream getBody() {
            byte[] resp = "{\"msg\":null,\"error\":\"sorry, out of service\"}".getBytes(StandardCharsets.UTF_8);
            return new ByteArrayInputStream(resp);
        }

        @Override
        public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return headers;
        }
    }
}
