package com.wavelabs.utility;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.wavelabs.solr.service.SolrJobIndexing;

public class SolrIndexJobScheduler {

	private static Scheduler schedule;
	private static Logger logger = Logger.getLogger(SolrIndexJobScheduler.class);
	private static boolean flag;

	public static Boolean startSchedule(String time) {
		logger.setLevel(Level.INFO);
		try {
			JobDetail jobDetail = JobBuilder.newJob(SolrJobIndexing.class).withIdentity("solrJob", "insert").build();
			logger.info("job creation is completed");
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "insert")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(Integer.parseInt(time))
							.repeatForever())
					.build();
			logger.info("trigger creation completed");
			schedule = StdSchedulerFactory.getDefaultScheduler();
			schedule.start();
			schedule.scheduleJob(jobDetail, trigger);
			setFlag(true);
			logger.info("job scheduling completed");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean stopSchedule() {
		try {
			schedule.shutdown();
			setFlag(false);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static boolean getFlag() {
		return flag;
	}

	private static void setFlag(boolean flag) {
		SolrIndexJobScheduler.flag = flag;
	}
}
