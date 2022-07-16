# playground-spring-framework-event

Spring 事件机制演示, 核心类:

- org.springframework.context.ApplicationListener
- org.springframework.context.event.EventListener

## 要点讲解

- 使用`org.springframework.context.ApplicationEventPublisher.publishEvent(java.lang.Object)` 接口进行事件发布
- 项目里 `MyListener` 和 `MyListenerAsync` 都接入了 spring 的监听机制(一个实现接口,一个打了注解), 实现了对事件的监听
- `@Async` 注解使其具有了异步执行的能力, 其他逻辑没有区别
- 事件实体可以直接使用任意实体类, 也可以使用规范里的 `org.springframework.context.ApplicationEvent` 类, 没有区别,接收的时候能做判断就行

