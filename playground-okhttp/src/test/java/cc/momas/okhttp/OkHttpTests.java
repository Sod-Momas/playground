package cc.momas.okhttp;

import okhttp3.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class OkHttpTests {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String URL_SECURED_BY_BASIC_AUTHENTICATION = "https://localhost/auth";
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Synchronous GET with OkHttp
     *
     * @throws IOException
     */
    @Test
    public void whenGetRequest_thenCorrect() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/date")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Asynchronous GET with OkHttp
     */
    @Test
    public void whenAsynchronousGetRequest_thenCorrect() {
        Request request = new Request.Builder()
                .url(BASE_URL + "/date")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                // ...
            }

            public void onFailure(Call call, IOException e) {
                fail();
            }
        });
    }

    /**
     * GET with Query Parameters
     *
     * @throws IOException
     */
    @Test
    public void whenGetRequestWithQueryParameter_thenCorrect()
            throws IOException {

        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(BASE_URL + "/ex/bars").newBuilder();
        urlBuilder.addQueryParameter("id", "1");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Basic POST
     *
     * @throws IOException
     */
    @Test
    public void whenSendPostRequest_thenCorrect()
            throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", "test")
                .add("password", "test")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * POST with Authorization
     *
     * @throws IOException
     */
    @Test
    public void whenSendPostRequestWithAuthorization_thenCorrect()
            throws IOException {
        String postBody = "test post";

        Request request = new Request.Builder()
                .url(URL_SECURED_BY_BASIC_AUTHENTICATION)
                .addHeader("Authorization", Credentials.basic("test", "test"))
                .post(RequestBody.create(
                        MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * POST with JSON
     *
     * @throws IOException
     */
    @Test
    public void whenPostJson_then_Correct() throws IOException {
        String json = "{\"id\":1,\"name\":\"John\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/detail")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Multipart POST Request
     *
     * @throws IOException
     */
    @Test
    public void whenSendMultipartRequest_thenCorrect()
            throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "test")
                .addFormDataPart("password", "test")
                .addFormDataPart("file", "file.txt",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("src/test/resources/test.txt")))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/multipart")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Upload a File
     *
     * @throws IOException
     */

    @Test
    public void whenUploadFile_thenCorrect() throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "file.txt",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("src/test/resources/test.txt")))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/upload")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Setting a Header on a Request
     */
    @Test
    public void whenSetHeader_thenCorrect() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        response.close();
    }


    /**
     * Setting a Default Header
     */

    @Test
    public void whenSetDefaultHeader_thenCorrect()
            throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new DefaultContentTypeInterceptor("application/json"))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        response.close();
    }

    /**
     * Do Not Follow Redirects
     * <p>
     * In this example, we’ll see how to configure the OkOkHttpClient to stop following redirects.
     * <p>
     * By default, if a GET request is answered with an HTTP 301 Moved Permanently the redirect is automatically followed. In some use cases, that may be perfectly fine, but there are certainly use cases where that’s not desired.
     * <p>
     * To achieve this behavior, when we build our client, we need to set followRedirects to false.
     * <p>
     * Note that the response will return an HTTP 301 status code:
     *
     * @throws IOException
     */
    @Test
    public void whenSetFollowRedirects_thenNotRedirected()
            throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();

        Request request = new Request.Builder()
                .url("http://t.co/I5YYd9tddw")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(301));
    }

    /**
     * Timeouts
     * <p>
     * Use timeouts to fail a call when its peer is unreachable. Network failures can be due to client connectivity problems, server availability problems, or anything between. OkHttp supports connect, read, and write timeouts.
     * <p>
     * In this example, we built our client with a readTimeout of 1 seconds, while the URL is served with 2 seconds of delay:
     *
     * @throws IOException
     */
    @Test
    public void whenSetRequestTimeout_thenFail()
            throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/delay/2")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        assertThat(response.code(), equalTo(200));
    }

    /**
     * Canceling a Call
     *
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void whenCancelRequest_thenCorrect()
            throws IOException {
//        Logger logger = Logger.getLogger(cc.momas.okhttp.OkHttpTests.class.getName());

        class Log {
            private void debug(String msg) {
                System.out.println(msg);
            }

            public void debug(String msg, Response response) {
                System.out.println(msg + "\n" + response.body());
            }
        }

        ScheduledExecutorService executor
                = Executors.newScheduledThreadPool(1);

        Request request = new Request.Builder()
                .url(BASE_URL + "/delay/2")
                .build();

        int seconds = 1;
        long startNanos = System.nanoTime();

        Call call = client.newCall(request);

        Log logger = new Log();

        executor.schedule(() -> {
            logger.debug("Canceling call: " + (System.nanoTime() - startNanos) / 1e9f);

            call.cancel();

            logger.debug("Canceled call: " + (System.nanoTime() - startNanos) / 1e9f);

        }, seconds, TimeUnit.SECONDS);

        logger.debug("Executing call: " + (System.nanoTime() - startNanos) / 1e9f);

        Response response = call.execute();

        logger.debug("Call was expected to fail, but completed: " + (System.nanoTime() - startNanos) / 1e9f, response);

    }


    /**
     * Response Caching
     *
     * @throws IOException
     */
    @Test
    public void whenSetResponseCache_thenCorrect()
            throws IOException {
        int cacheSize = 10 * 1024 * 1024;

        File cacheDirectory = new File("src/test/resources/cache");
        Cache cache = new Cache(cacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response1 = client.newCall(request).execute();
        System.out.println(response1);

        Response response2 = client.newCall(request).execute();
        System.out.println(response2);
    }
}
