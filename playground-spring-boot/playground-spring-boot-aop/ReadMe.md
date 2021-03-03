# spring-boot-aop

该项目演示了如何使用 spring-boot-aop

## 启动方法

直接启动 spring-boot 项目

```bash
mvn spring-boot:run
```

## 关键配置

关键配置是 `cc.momas.aop.config.AopConfig` 这个类, AOP 使用方法核心在于匹配表达式, 学会了匹配表达式相当于要切哪里就切哪里

## 参考资料

- [Spring Boot 学习笔记(十三) 整合 AOP](https://zdran.com/20190418.html)
- [11. Aspect Oriented Programming with Spring](https://docs.spring.io/spring/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html)
- [5. Aspect Oriented Programming with Spring](https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/core.html#aop)
- [Spring Boot中使用AOP统一处理Web请求日志](http://blog.didispace.com/springbootaoplog/)
