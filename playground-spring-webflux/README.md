# playground-spring-webflux

spring 提供的web编程框架，基于Reactive 技术栈

创建数据库表

```sql
CREATE DATABASE blog;
CREATE TABLE t_product(
    id     INT       AUTO_INCREMENT,
    price  DECIMAL,
    name   VARCHAR(50),
    stock  INT,
    category VARCHAR(100),
    PRIMARY KEY(id)
);
```

## 测试url

- http://localhost/anno/get 测试基于注解的接口
- http://localhost/java-cfg/get 测试基于java配置的接口

- - - 

1. 添加商品

```
curl -s http://localhost/route/product/save -X POST -H 'Content-Type:application/json' -d ' { "price":1, "name":"a product", "stock":12, "category":"A" }'
```

2. 查询商品

```
curl -s http://localhost/route/product/find/1
curl -s http://localhost/route/product/findAll
curl -s http://localhost/route/product/findByCategory -X POST -H 'Content-Type:application/json' -d ' { "category":"A","page":1,"pageSize":10 }'
```

3. 修改商品

```
curl -s http://localhost/route/product/update/1 -X PUT -H 'Content-Type:application/json' -d ' {"id":1,  "price":2, "name":"B product", "stock":88, "category":"B" }'
```

4. 查询商品

```
curl -s http://localhost/route/product/find/1
```

5. 删除商品

```
curl -s -X DELETE http://localhost/route/product/delete/1
```

# 参考资料

- [Web on Reactive Stack](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#spring-webflux)
- R2DBC官网 ：https://r2dbc.io/