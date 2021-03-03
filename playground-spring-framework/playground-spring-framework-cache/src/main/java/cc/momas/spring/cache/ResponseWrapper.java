package cc.momas.spring.cache;

import java.time.LocalDateTime;

/**
 * 响应包装器
 *
 * @author Sod-Momas
 * @since 2021.02.21
 */
public class ResponseWrapper<T> {
    private T data;
    private LocalDateTime time = LocalDateTime.now();
    private int total;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "data=" + data +
                ", time=" + time +
                ", total=" + total +
                '}';
    }
}
