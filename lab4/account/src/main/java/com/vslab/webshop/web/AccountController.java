package com.vslab.webshop.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vslab.webshop.AccountService;
import com.vslab.webshop.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrix
public class AccountController {

    @Autowired
    private AccountService accountService;

    @DeleteMapping("account/{roleId}")
    @HystrixCommand(fallbackMethod = "indicateUnknownEntity")
    public String removeRole(@PathVariable long roleId){
        accountService.removeRole(roleId);
        return "Successfully deleted role with id " + roleId;
    }

    public String indicateUnknownEntity(long userId) {
        return "Resource not found";
    }

    public User[] indicateUnknownEntity() {
        return new User[0];
    }
}
