# JOL(Java Object Layout)

JOL (Java Object Layout) is the tiny toolbox to analyze object layout schemes in JVMs. 
These tools are using Unsafe, JVMTI, and Serviceability Agent (SA) heavily to decoder 
the actual object layout, footprint, and references. This makes JOL much more accurate 
than other tools relying on heap dumps, specification assumptions, etc.

- [Java Object Layout](http://openjdk.java.net/projects/code-tools/jol/)
- 引用了JOL官方示例