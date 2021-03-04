package cc.momas.okhttp;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;

import java.io.IOException;
import java.time.Duration;

/**
 * @author Sod-Momas
 * @since 2021-03-04
 */
public class HttpClient {
    private static final OkHttpClient delegate;

    static {
        delegate = new OkHttpClient.Builder()
                .readTimeout(Duration.ofSeconds(30))
                .writeTimeout(Duration.ofSeconds(30))
                .connectTimeout(Duration.ofSeconds(30))
                .callTimeout(Duration.ofSeconds(30))
                .addInterceptor(ContentTypeInterceptor.INSTANCE)
                .build();
    }

    /**
     * 发送给远程主机
     *
     * @param request 请求内容
     * @return 响应体
     * @throws IOException 当请求失败时
     */
    public static Response send(Request request) throws IOException {
        return delegate.newCall(request).execute();
    }

    /**
     * 发送并获取响应体里的字符串
     *
     * @param request 请求内容
     * @return 响应体字符串
     * @throws IOException 当请求失败时
     */
    public static String sendAndGetBody(Request request) throws IOException {
        final var body = send(request).body();
        return body == null ? "" : body.string();
    }

    /**
     * 媒体类型拦截器
     */
    static class ContentTypeInterceptor implements Interceptor {
        public static final ContentTypeInterceptor INSTANCE = new ContentTypeInterceptor();

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest
                    .newBuilder()
                    .header("Content-Type", "application/json")
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

}
