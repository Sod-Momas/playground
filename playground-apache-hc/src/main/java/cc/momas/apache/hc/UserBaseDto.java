package cc.momas.apache.hc;

import java.util.Date;

/**
 * 从用户中心获取的用户信息,作为传输介质使用
 *
 * @author Chen
 */
public class UserBaseDto {

    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String displayName;
    /**
     * 用户性别
     */
    private Boolean gender;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户登录次数
     */
    private Integer loginCount;
    /**
     * 用户最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 用户是否启用
     */
    private Boolean enable;
    /**
     * 用户登录凭证
     */
    private String token;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 修改时间
     */
    private Date modificationTime;
    /**
     * 创建用户
     */
    private String createUserId;
    /**
     * 修改用户
     */
    private String modifyUserId;

    private String test;

    private int age;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserBaseDto{" +
                "email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", loginCount=" + loginCount +
                ", lastLoginTime=" + lastLoginTime +
                ", enable=" + enable +
                ", token='" + token + '\'' +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                ", createUserId='" + createUserId + '\'' +
                ", modifyUserId='" + modifyUserId + '\'' +
                ", test='" + test + '\'' +
                ", age=" + age +
                '}';
    }
}