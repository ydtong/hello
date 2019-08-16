package com.allqj.virtual_number_administrate.configuration.threadPool.parameter;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ConfigurationProperties(prefix = "threadpool.other")
@Component
public class OtherThreadPoolParameter extends BaseThreadPoolParameter {

}
