package com.allqj.virtual_number_administrate.configuration.threadPool.parameter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseThreadPoolParameter {
    private int corePoolSize;
    private int maxPoolSize;
    private String threadNamePrefix;
    private boolean waitForTasksToCompleteOnShutdown;
    private int awaitTerminationSeconds;
}
