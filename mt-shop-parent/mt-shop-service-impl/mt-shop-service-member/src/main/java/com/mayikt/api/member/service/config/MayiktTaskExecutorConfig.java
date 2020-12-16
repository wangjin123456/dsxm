
package com.mayikt.api.member.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
*@title: MayiktTaskExecutorConfig
*@description； 项目
*@author taotao
*@date 2020/12/11 9:53
*/  
@Configuration
public class MayiktTaskExecutorConfig   implements AsyncConfigurer {
    /**
     * 设置ThreadPoolExecutor的核心池大小。
     */
    private static final int CORE_POOL_SIZE = 2;
    /**
     * 设置ThreadPoolExecutor的最大池大小。
     */
    private static final int MAX_POOL_SIZE = 2;
    /**
     * 设置ThreadPoolExecutor的BlockingQueue的容量。
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 通过重写getAsyncExecutor方法，制定默认的任务执行由该方法产生
     * <p>
     * 配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
     * 这样我们就获得了一个基于线程池的TaskExecutor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.initialize();
        return taskExecutor;
    }

}
