package com.vslab.account;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vslab.account.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableHystrix
public class AccountController {

    @Autowired
    private AccountService accountService;

    @DeleteMapping("account/{roleId}")
    @HystrixCommand(fallbackMethod = "indicateUnknownEntity")
    public String removeRole(@PathVariable int roleId){
        accountService.removeRole(roleId);
        return "Succesfully deleted roleId";
    }

    public String indicateUnknownEntity(int userId) {
        return "Resource not found";
    }

    public User[] indicateUnknownEntity() {
        return new User[0];
    }
}
