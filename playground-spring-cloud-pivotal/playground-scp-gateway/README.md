# playground-spring-cloud-gateway

spring cloud gateway 网关服务

## 测试url

url                                                                  | 结果和说明
---------------------------------------------------------------------|------
http://localhost:2102/playground-scn-eureka-client/hi?name=%E5%80%BC | 正常请求时返回 `{"msg":"值 from 2001"}`，当触发限流规则时会返回 429 状态码

## 参考链接
- [Spring Cloud Gateway 之 服务注册与发现](https://blog.csdn.net/u010562966/article/details/88012906) 网关自动转发请求给对应的服务
- [spring cloud gateway之服务注册与发现](https://blog.csdn.net/forezp/article/details/85210153)

