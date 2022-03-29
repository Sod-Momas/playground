package cc.momas.spring.boot.web;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Sod-Momas
 * @since 2022/3/29
 */
@ControllerAdvice
@Order(10000)
public class CVE_2022_22950FixControllerAdvice {
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        String[] abd = new String[]{"class.*", "Class. *", "*.class.*", "*.Class. *"};
        dataBinder.setDisallowedFields(abd);
    }
}
