package com.carTravelsky.task;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.StringUtils;

@EnableScheduling
public class Motoitor implements SchedulingConfigurer {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 初始化加入spring
	 */
	private String cron = "0/5 * * * * ?";

	@PostConstruct
	public void init() {
	}
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				// 任务逻辑
				//logger.debug("dynamicCronTask is running...");
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 任务触发，可修改任务的执行周期
				 new Thread(new Runnable() {  
			            @Override  
			            public void run() {  
			                try {  
			                    Thread.sleep(15 * 1000);  
			                } catch (InterruptedException e) {  
			                    e.printStackTrace();  
			                }
			                cron = "0/20 * * * * ?";
			                if(!StringUtils.equals(cron, "0/20 * * * * ?")){
				                System.err.println("cron change to: " + cron);  
			                }
			            }  
			        }).start();  
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		});
	}
}
