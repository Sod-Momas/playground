//package cc.momas.dubbo.consumer;
//
//import cc.momas.dubbo.api.AccountService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.stereotype.Service;
//
///**
// * @author Sod-Momas
// * @since 2022/9/18
// */
//@SpringBootApplication
//@Service
//@EnableDubbo
//public class ConsumerApplication {
//    @DubboReference
//    private AccountService demoService;
//
//    public static void main(String[] args) {
//
//        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
//        ConsumerApplication application = context.getBean(ConsumerApplication.class);
//        String result = application.doSayHello("world");
//        System.out.println("result: " + result);
//    }
//
//    public String doSayHello(String name) {
//        return demoService.sayHello(name);
//    }
//}
