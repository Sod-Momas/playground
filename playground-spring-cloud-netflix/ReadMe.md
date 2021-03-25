# playground-spring-cloud-neflix

Spring Cloud Netflix 套件

## 端口配置

服务                          | 端口
-----------------------------|------
playground-scn-eureka-server | 2000
playground-scn-eureka-teriri | 2001
playground-scn-eureka-kiana  | 2002
playground-scn-ribbon        | 2003
playground-scn-feign         | 2004
playground-scn-zuul          | 2005
playground-scn-hystrix       | 2006
playground-scn-turbine       | 2007

## 测试url

- http://localhost:2003/hi?name=sod 该请求发给 ribbon 会返回 {"msg":"sod from 2001"} 或者 {"msg":"sod from 2002"}
- http://localhost:2004/hi?name=sod 该请求发给 feign 会返回 {"msg":"sod from 2001"} 或者 {"msg":"sod from 2002"}
- http://localhost:2003/say?name=sod 该请求发给 ribbon 处理正常会返回 {"msg":"sod from 2001"} 或者 {"msg":"sod from 2002"} , 出现异常的时候被
  hystrix断路器处理, 会返回 {"msg":null,"error":"sorry, out of service"}
- http://localhost:2004/hi?name=sod 该请求发给 feign 会返回 {"msg":"sod from 2001"} 或者 {"msg":"sod from 2002"}, 出现异常的时候被
  hystrix断路器处理，会返回 {"msg":null,"error":"sorry, out of service"}
- http://localhost:2005/ribbon/hi?name=sod 发送给zuul, zuul会转发给 ribbon,ribbon会做负载均衡给kiana和teriri, 响应内容是 {"msg":"sod from
  2001"} 或者 {"msg":"sod from 2002"}
- http://localhost:2005/feign/hi?name=sod 发送给zuul, zuul会转发给 feign,feign会做负载均衡给kiana和teriri, 响应内容是 {"msg":"sod from 2001"
  } 或者 {"msg":"sod from 2002"}
- http://localhost:2006/hystrix 进行hystrix的web控制台
- http://localhost:2006/actuator/proxy.stream 用于hystrix控制台的连接端点

## 参考资料

- [Spring Cloud - Eureka /actuator/info 如何显示信息](https://blog.csdn.net/philosophyy/article/details/102726596)
- [女武神](https://www.bh3.com/valkyries) 起名灵感