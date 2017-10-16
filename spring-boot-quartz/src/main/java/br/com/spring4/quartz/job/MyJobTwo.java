package br.com.spring4.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyJobTwo extends QuartzJobBean {
	public static final String COUNT = "count";
	private String name;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
	
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
		int cnt = dataMap.getInt(COUNT);
		JobKey jobKey = ctx.getJobDetail().getKey();
		
		StringBuilder sb = new StringBuilder(" ->>>>> ");
		sb.append(sdf.format(new Date()))
			.append(" = ")		
			.append(jobKey)
			.append(" : ")
			.append(name)
			.append(" : ")
			.append(cnt);
			
		System.out.println(sb.toString());

		cnt++;
		dataMap.put(COUNT, cnt);
	}

	public void setName(String name) {
		this.name = name;
	}
}
