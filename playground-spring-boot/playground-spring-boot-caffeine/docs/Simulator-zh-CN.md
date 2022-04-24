模拟器中包含一系列的驱逐策略和数据分发生成器。这将帮助我们了解各个驱逐策略是否适合使用场景。   

### 使用方法
在配置完所需要的 [配置][config]之后，在IDE中运行[模拟器][simulator]。或者也可以选择在命令行中使用:

```
# Single run, displays ascii table by default
gradlew simulator:run -q -PjvmArgs=<optional>

# Multiple runs, writes a combined report, and renders a chart
gradlew simulator:simulate -q \
  --maximumSize=<comma separated list; supports _ numeric literal> \
  --theme=<light, dark> \
  --jvmArgs=<optional> \
  --title=<optional>

# In both, additional configuration may be supplied as system properties
  -Dcaffeine.simulator.files.paths.0=multi3.trace.gz
```

命令。支持以下几种追踪格式：   

| 仓库 | 位置 | 仓库 | 位置
|:--|:--|:--|:--|
| AdaptSize | [author's simulator][adapt-size] | Address (UCSD) | [git repository][address]
| ARC | [author's homepage][arc] | Cache2k | [git repository][cache2k]
| CameLab | [project's homepage][camelab] | Cloud Physics | [git repository][cloud-physics], [lirs2 drive][cloud-physics-drive]
| Corda | [git repository][corda] | Gradle | [git repository][gradle]
| Kaggle | [project's homepage][kaggle] | LIRS | [git repository][lirs] 
| LRB | [author's simulator][lrb] | Scarab | [git repository][scarab]
| SNIA | [project's homepage][snia] | Tragen | [project's homepage][tragen]
| Twitter | [project's homepage][twitter] | UMass | [project's homepage][umass]
| WikiBench | [project's homepage][wikibench]

### 采样报告
由于批处理和数据广播的原因，时间指标只有在独立运行各个策略的时候才有比较的价值。   
```
╔══════════════════════╤══════════╤════════════╤════════════╤════════════╤════════════╤═══════════╗
║ Policy               │ Hit Rate │ Hits       │ Misses     │ Requests   │ Evictions  │ Time      ║
╠══════════════════════╪══════════╪════════════╪════════════╪════════════╪════════════╪═══════════╣
║ opt.Clairvoyant      │ 48.09 %  │ 21,019,597 │ 22,685,382 │ 43,704,979 │ 18,685,382 │ 2.301 min ║
╟──────────────────────┼──────────┼────────────┼────────────┼────────────┼────────────┼───────────╢
║ sketch.WindowTinyLfu │ 45.25 %  │ 19,775,085 │ 23,929,894 │ 43,704,979 │ 19,929,894 │ 1.460 min ║
╟──────────────────────┼──────────┼────────────┼────────────┼────────────┼────────────┼───────────╢
║ irr.Lirs             │ 38.14 %  │ 16,668,577 │ 27,036,402 │ 43,704,979 │ 23,036,402 │ 1.319 min ║
╟──────────────────────┼──────────┼────────────┼────────────┼────────────┼────────────┼───────────╢
║ adaptive.Arc         │ 29.60 %  │ 12,938,241 │ 30,766,738 │ 43,704,979 │ 26,766,738 │ 1.249 min ║
╟──────────────────────┼──────────┼────────────┼────────────┼────────────┼────────────┼───────────╢
║ linked.Lru           │ 20.24 %  │  8,847,982 │ 34,856,997 │ 43,704,979 │ 30,856,997 │ 1.218 min ║
╚══════════════════════╧══════════╧════════════╧════════════╧════════════╧════════════╧═══════════╝
```
[[ https://raw.githubusercontent.com/ben-manes/caffeine/master/wiki/simulator/hit_rate.png | height = 500px ]]

[adapt-size]: https://github.com/dasebe/webcachesim#how-to-get-traces
[address]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/address
[arc]: http://researcher.watson.ibm.com/researcher/view_person_subpage.php?id=4700
[cache2k]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/cache2k
[akka]: http://akka.io
[camelab]: https://trace.camelab.org/2016/03/01/flash.html
[cloud-physics]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/cloud_physics
[cloud-physics-drive]: https://drive.google.com/drive/folders/1vvnhze4UqCDk4ZnwHTPL6zarA2d9YHaR
[config]: https://github.com/ben-manes/caffeine/blob/master/simulator/src/main/resources/reference.conf
[corda]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/corda
[gradle]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/gradle
[kaggle]: https://www.kaggle.com/c/outbrain-click-prediction/data
[lirs]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/lirs
[lrb]: https://github.com/sunnyszy/lrb#trace
[scarab]: https://github.com/ben-manes/caffeine/tree/master/simulator/src/main/resources/com/github/benmanes/caffeine/cache/simulator/parser/scarab
[simulator]: https://github.com/ben-manes/caffeine/blob/master/simulator/src/main/java/com/github/benmanes/caffeine/cache/simulator/Simulator.java
[snia]: http://iotta.snia.org
[tragen]: https://github.com/UMass-LIDS/Tragen
[twitter]: https://github.com/twitter/cache-trace#trace-download
[umass]: http://traces.cs.umass.edu/index.php/Storage/Storage
[wikibench]: http://www.wikibench.eu
