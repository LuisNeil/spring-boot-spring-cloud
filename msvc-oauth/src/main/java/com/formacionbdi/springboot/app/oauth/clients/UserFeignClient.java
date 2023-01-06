package com.formacionbdi.springboot.app.oauth.clients;

import com.formacionbdi.springboot.app.commons.users.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("msvc-users")
public interface UserFeignClient {

    @GetMapping("/users/search/find-username")
    public User findByUsername(@RequestParam String username);
}
