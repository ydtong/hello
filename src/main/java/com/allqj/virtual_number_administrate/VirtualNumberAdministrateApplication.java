package com.allqj.virtual_number_administrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;


//@Import(CommonConfiguration.class)
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class VirtualNumberAdministrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualNumberAdministrateApplication.class, args);
    }

}
