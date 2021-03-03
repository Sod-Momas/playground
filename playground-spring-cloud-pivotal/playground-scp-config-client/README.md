# playground-scp-config-client

## 配置说明

- 使用配置中心的项目要把配置写在 `bootstrap.properties` 文件而不是 `application.properties` 里
- 配置文件的匹配方式有以下几种：
    - /{application}/{profile}[/{label}]
    - /{application}-{profile}.yml
    - /{label}/{application}-{profile}.yml
    - /{application}-{profile}.properties
    - /{label}/{application}-{profile}.properties

# 测试url

- http://localhost:2101/config/ch 可以读取到` playground-scp-config-client-dev.yml `配置文件里的 `中文测试`
- http://localhost:2101/config/foo 可以读到 `bar`