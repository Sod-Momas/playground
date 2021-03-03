package cc.momas.scn.gw.zuul;

/**
 * 业务常量定义，用于标记错误的异常代码，类似于微软的蓝屏代码，标识某一类错误
 *
 * @author Sod-Momas
 * @since 2021-01-30
 */
public enum ErrorCodeEnum {
    GATEWAY_ERROR(500, "Gateway Error"),
    ;

    public final int code;
    public final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
