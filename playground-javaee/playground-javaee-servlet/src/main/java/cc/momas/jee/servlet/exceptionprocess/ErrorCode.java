package cc.momas.jee.servlet.exceptionprocess;

/**
 * 错误代码枚举
 *
 * @author Sod-Momas
 * @since 2021.06.13
 */
public enum ErrorCode {
    PARAM_VALID(400, "参数校验不通过"),
    NO_AUTH(401, "未登录"),
    SERVER_ERROR(500, "系统异常"),
    ;
    /**
     * 错误数字代码，方便客户传达错误信息
     */
    public final int errorCode;
    /**
     * 错误消息，这里可以使用占位符，以实现国际化
     */
    public final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
