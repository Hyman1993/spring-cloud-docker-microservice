package com.penghuang.microserviceconsumermovie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.penghuang.microserviceconsumermovie.dto.UserDto;
import com.penghuang.microserviceconsumermovie.service.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public UserDto findById(@PathVariable Long id) {
        return this.restTemplate.getForObject(userServiceUrl + id, UserDto.class);
//           return  this.userFeignClient.findById(id);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("" +
                "microservice-provider-user");

        MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(),
                serviceInstance.getHost(), serviceInstance.getPort());
    }


    public UserDto findByIdFallback(Long id, Throwable throwable) {
        LOGGER.info("进入回退方法,异常:" + throwable);
        UserDto user = new UserDto();
        user.setId((long) 1);
        user.setName("默认用户");
        return user;

    }
}