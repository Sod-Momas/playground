# synchronized 与 ReentrantLock 的区别

## synchronized

在 jdk 1.6 之前，直接使用了操作系统的 mutex 互斥量，从用户态切换到内核态消耗了性能

在 jdk 1.6 及之后，增加了对象偏向锁、对象轻量级锁，在竞争不激烈的情况下避免切换到内核态，
减少了性能消耗。在竞争激烈的情况下才升级到操作系统的mutex互斥量

```shell
无锁 
 -> 偏向锁（单线程使用资源） 
  -> 轻量级锁（资源可以交互使用，没有竞争就直接使用，有竞争会尝试自旋等待） 
   -> 重量级锁（资源同时被多个线程竞争并使用较长时间）
```

- 锁加粗：多个同步块合并成一个同步块
- 锁消除：经过 **逃逸分析** 去除没有竞争可能性的锁

![使用 synchronized 时作为锁的对象的markword](markword.jfif)

## ReentrantLock

使用了AQS机制实现

- 阻塞等待队列
- 共享/独占
- 公平/非公平
- 可重入
- 允许中断