JSR-107 JCache是一个在JEE8中引入的与Java6兼容的标准化缓存API。Caffeine提供了JSR-107标准下的一个本地缓存实现。JCache provider通过[Typesafe的Config][config]库来进行配置。可以通过查看[reference.conf][conf]来了解更多细节。[FactoryCreator][creator]可以配置在依赖注入框架中进行实例管理。              

### 注解支持
匿名缓存将会根据规范的默认配置而创建。这意味着缓存里的数据永远不会过期并且根据缓存的值value所保存(序列化)。    

#### Spring
查看Spring[文档][spring-jcache].

推荐使用[Spring Cache][spring-cache]，Spring Framework 4.3和Spring Boot 1.4提供了Caffeine支持。

#### Guice
通过JCache provider为JSR提供了集成模块。

```gradle
implementation 'org.jsr107.ri:cache-annotations-ri-guice:1.1.0'
```

```java
Injector injector = Guice.createInjector(new CacheAnnotationsModule());
```

#### CDI
通过JCache provider为JSR提供了扩展。

```gradle
implementation 'org.jsr107.ri:cache-annotations-ri-cdi:1.1.0'
```

[config]: https://github.com/typesafehub/config
[conf]: https://github.com/ben-manes/caffeine/blob/master/jcache/src/main/resources/reference.conf
[expiry]: http://static.javadoc.io/javax.cache/cache-api/1.0.0/javax/cache/expiry/ExpiryPolicy.html
[resolver]: https://github.com/jsr107/RI/blob/master/cache-annotations-ri/cache-annotations-ri-common/src/main/java/org/jsr107/ri/annotations/DefaultCacheResolverFactory.java#L73
[caffeine-config]: https://github.com/ben-manes/caffeine/blob/master/jcache/src/main/java/com/github/benmanes/caffeine/jcache/configuration/TypesafeConfigurator.java#L59
[spring-jcache]: https://spring.io/blog/2014/04/14/cache-abstraction-jcache-jsr-107-annotations-support
[spring-cache]: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html
[creator]: https://github.com/ben-manes/caffeine/blob/master/jcache/src/main/java/com/github/benmanes/caffeine/jcache/configuration/TypesafeConfigurator.java#L111