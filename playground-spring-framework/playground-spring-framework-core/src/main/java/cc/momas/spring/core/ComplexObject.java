package cc.momas.spring.core;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class ComplexObject {
    private Properties adminEmails;
    private List<?> someList;
    private Map<?, ?> someMap;
    private Set<?> someSet;

    public Properties getAdminEmails() {
        return adminEmails;
    }

    public void setAdminEmails(Properties adminEmails) {
        this.adminEmails = adminEmails;
    }

    public List<?> getSomeList() {
        return someList;
    }

    public void setSomeList(List<?> someList) {
        this.someList = someList;
    }

    public Map<?, ?> getSomeMap() {
        return someMap;
    }

    public void setSomeMap(Map<?, ?> someMap) {
        this.someMap = someMap;
    }

    public Set<?> getSomeSet() {
        return someSet;
    }

    public void setSomeSet(Set<?> someSet) {
        this.someSet = someSet;
    }

    @Override
    public String toString() {
        return "ComplexObject{" +
                "adminEmails=" + adminEmails +
                ", someList=" + someList +
                ", someMap=" + someMap +
                ", someSet=" + someSet +
                '}';
    }
}
