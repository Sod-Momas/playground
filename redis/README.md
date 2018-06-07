# Jedis 

Jedis是一个非常小巧和聪明的Redis java客户端。  
github : 项目地址 [Jedis](https://github.com/xetorthio/jedis)

jedis实例是非线程安全的，所以需要池来维护

## 使用

1. 使用它非常简单，第一步是获得它，可以在  http://github.com/xetorthio/jedis/releases 下载它，
或者使用maven添加依赖

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```

2. 使用一个最简单的使用方法
```java
import redis.clients.jedis.Jedis;

class Test{
    
	public static void main(String[] args){
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
	}
}
```

## 集群

如果您的环境是Redis集群，那么请使用以下方式配置

```java
import redis.clients.jedis.Jedis;

class Test{
    
	public static void main(String[] args){
       Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
       //Jedis Cluster will attempt to discover cluster nodes automatically
       jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
       JedisCluster jc = new JedisCluster(jedisClusterNodes);
       jc.set("foo", "bar");
       String value = jc.get("foo");
	}
}
```

## 多线程

因为Jedis并非线程安全的类，所以启动应用的时候初始化相当量的实例是一个比较好的选择，
JedisPool就是这样一个池

要使用它，请先启动一个池

```java
JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
```
您可以将该池静态存储在某个位置,例如一个工厂类里,这是线程安全的。
JedisPool基于Commons Pool 2,因此您可能需要查看Commons Pool的配置。
有关更多详细信息，请参阅[GenericObjectPoolConfig](http://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPoolConfig.html)

启动好池后开始使用它：
```java

/// Jedis 实现了Closeable接口,在java7以后可以使用自动关闭语法
try (Jedis jedis = pool.getResource()) {
  /// 在这里做点事情,例如 ...
  jedis.set("foo", "bar");
  String foobar = jedis.get("foo");
  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike"); 
  Set<String> sose = jedis.zrange("sose", 0, -1);
}
/// 当程序退出的时候,记得关闭我们的池
pool.close();
```

如果您的Jedis实例是从池中获取,那它将会被归还到池中,  
如果您是直接创建的,那么它将会断开连接并关闭.

## 设置主从分配 

### 启用主从复制 

使myjedis成为 192.168.1.35 主人的奴隶 :
```java
myjedis.slaveof("192.168.1.35", 6379);
```
**注意：由于Redis 2.6从站只能默认读取，所以向它们写入请求将导致错误。**
### 关闭主从复制  

如果主人机失败了,您可能想推动某奴隶成为主人.  
您应该首先(尝试)禁用主服务器的复制,  
如果您有多台服务器,则启用其他服务器(如: 192.168.1.36)为新主服务器来实现主从复制 : 

```java
slave1jedis.slaveofNoOne();
slave2jedis.slaveof("192.168.1.36", 6379); 
```

## 参考

[Jedis 官方wiki](https://github.com/xetorthio/jedis/wiki/)