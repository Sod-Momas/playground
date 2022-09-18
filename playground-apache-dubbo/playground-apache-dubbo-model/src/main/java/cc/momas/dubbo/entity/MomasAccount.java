package cc.momas.dubbo.entity;

import cc.momas.dubbo.constant.BloodTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Sod-Momas
 * @since 2021-02-03
 */
public class MomasAccount implements java.io.Serializable {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPwd;
    /**
     * 出生日期
     */
    private LocalDateTime birthday;
    /**
     * 体重，单位kg
     */
    private BigDecimal weight;
    /**
     * 血型
     */
    private BloodTypeEnum bloodType;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BloodTypeEnum getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodTypeEnum bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "MomasAccount{" +
                "loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", bloodType=" + bloodType +
                '}';
    }
}
