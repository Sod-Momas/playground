# playground-dlock-mysql

基于MySQL的分布式锁

## 测试url

- http://localhost:8080/lock?bizName=123 开多个浏览器，同时访问同个资源(此处为 `123`)时，单线程执行后返回执行完成，有并发时返回 `获取锁失败`