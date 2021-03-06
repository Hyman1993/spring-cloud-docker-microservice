package com.penghuang.microserviceconsumermovie.service;

import com.penghuang.microserviceconsumermovie.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public UserDto findById(@PathVariable("id") Long id);
}
