# playground-spring-data-elasticsearch

example for Spring Data Elasticsearch

## 启动环境

使用 docker-compose 来启动环境:

```shell
docker-compose -f docker-compose.yml up
```

如果遇到日志打印 `exited with code 137` 请考虑给 docker 多分配一些内存

启动完成之后,可以在 localhost:5601 访问 Kibana ,也可以直接使用工具访问 Elasticsearch 集群,地址为 localhost:9200;localhost:9201;localhost:9202

## reference

- [Running the Elastic Stack on Docker](https://www.elastic.co/guide/en/elastic-stack-get-started/7.6/get-started-docker.html) for docker-compose.yml
- [Spring Data Elasticsearch](https://spring.io/projects/spring-data-elasticsearch)
- [Spring Data Elasticsearch - Reference Documentation](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)
- [Spring Data Elasticsearch_小试牛刀](https://blog.csdn.net/JunSIrhl/article/details/106067186)
- [springboot 2.3.7.RELEASE集成elasticsearch7.6.2](https://blog.csdn.net/brian8271/article/details/111932507)