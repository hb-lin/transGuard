package com.tansun.task;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

//@Configuration
//@EnableScheduling
public class CompleteScheduleConfig /*implements SchedulingConfigurer */{

//    @Mapper
//    public interface CronMapper {
//        @Select("select cron from cron limit 1")
//        String getCron();
//    }
//
//    @Autowired
//    @SuppressWarnings("all")
//    CronMapper cronMapper;

   /* *//**
     * 执行定时任务.
     *//*
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行定时任务2: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
//                    String cron = cronMapper.getCron();
                    String cron = "0/10 * * * * ? ";
                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                        // Omitted Code ..
//                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行定时任务3: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
//                    String cron = cronMapper.getCron();
                    String cron = "0/10 * * * * ? ";
                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                        // Omitted Code ..
//                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }*/

}