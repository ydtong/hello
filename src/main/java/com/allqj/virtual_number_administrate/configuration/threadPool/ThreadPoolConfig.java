package com.allqj.virtual_number_administrate.configuration.threadPool;


import com.allqj.virtual_number_administrate.configuration.threadPool.parameter.OtherThreadPoolParameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "otherThreadPool")
    public ThreadPoolTaskExecutor otherThreadPool(OtherThreadPoolParameter asyncOtherThreadPoolConfig) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncOtherThreadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(asyncOtherThreadPoolConfig.getMaxPoolSize());
        executor.setThreadNamePrefix(asyncOtherThreadPoolConfig.getThreadNamePrefix());
        executor.setWaitForTasksToCompleteOnShutdown(asyncOtherThreadPoolConfig.isWaitForTasksToCompleteOnShutdown());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setAwaitTerminationSeconds(asyncOtherThreadPoolConfig.getAwaitTerminationSeconds());
        executor.initialize();
        return executor;
    }
}
