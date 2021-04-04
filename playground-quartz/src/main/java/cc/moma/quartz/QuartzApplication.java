package cc.moma.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 本类会调用到<strong>quartz.properties</strong>配置文件<br/>
 * 
 * <code>
 * # 配置内容:<br/>
 * org.quartz.scheduler.instanceName = MyScheduler<br/>
 * org.quartz.threadPool.threadCount = 3<br/>
 * org.quartz.jobStore.class =org.quartz.simpl.RAMJobStore<br/>
 * </code>
 * 
 * @author sod
 *
 */
public class QuartzApplication implements Job {
	/**
	 * 配置quartz,生成任务
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws SchedulerException {
		// 指定执行的类
		JobBuilder jb = JobBuilder.newJob(QuartzApplication.class);
		// 指定标识
		jb = jb.withIdentity("job1","group1");
		// 创建
		JobDetail job = jb.build();
		// -----
		// 使用工厂方法生成一个任务
		SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
		ssb = ssb.withIntervalInSeconds(5);
		ssb = ssb.repeatForever();
		// -----
		// 新建触发器
		TriggerBuilder<Trigger> tr = TriggerBuilder.newTrigger();
		// 给定标识
		TriggerBuilder<Trigger> tb = tr.withIdentity("trigger1", "group1");
		 //针对任务生成触发器
		TriggerBuilder<SimpleTrigger> tbs = tb.withSchedule(ssb);
		//生成简单触发器
		SimpleTrigger trigger = tbs.build();
		// ----
		//生成任务列表
		Scheduler scheduler  = new StdSchedulerFactory().getScheduler();
		scheduler.scheduleJob(job,trigger);
		scheduler.start();
	}

	/**
	 * 默认调用本方法
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(this.getClass() + "working at "+ System.currentTimeMillis());
	}
	
}
