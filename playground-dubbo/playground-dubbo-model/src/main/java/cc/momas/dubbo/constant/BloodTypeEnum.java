package cc.momas.dubbo.constant;

/**
 * 血型
 *
 * @author Sod-Momas
 * @since 2021-02-03
 */
public enum BloodTypeEnum {
    A(0),
    B(1),
    AB(2),
    O(4),
    ;

    public final int code;

    BloodTypeEnum(int code) {
        this.code = code;
    }
}
