# playground-spring-cloud-pivotal

Spring Cloud Pivotal 套件

## 端口分配

服务                          | 端口
-----------------------------|------
playground-scp-config-server | 2100
playground-scp-config-client | 2101

## 测试url

url                                                              | 结果和说明
-----------------------------------------------------------------|------
http://localhost:2100/playground-scp-config-client/dev/master    | 可以读取到 git仓库里的配置文件，也就是 `{"name":"playground-scp-config-client","profiles":["dev"],"label":"master","version":"0fc8081c507d694b27967e9074127b373d196431","state":null,"propertySources":[]} `