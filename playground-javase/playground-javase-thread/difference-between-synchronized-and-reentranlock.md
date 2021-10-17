# synchronized 与 ReentrantLock 的区别

## synchronized

在 jdk 1.6 之前，直接使用了操作系统的 mutex 互斥量，从用户态切换到内核态消耗了性能

在 jdk 1.6 及之后，增加了对象偏向锁、对象轻量级锁，在竞争不激烈的情况下避免切换到内核态，
减少了性能消耗。在竞争激烈的情况下才升级到操作系统的mutex互斥量