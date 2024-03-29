package com.proposalmanager.user;

import com.proposalmanager.user.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-manager", url = "http://user-manager:8090")
interface FeignServiceUserManager {

    @GetMapping("/get-user")
    UserDto getUserById(@RequestParam Long userId);
}
