package br.com.gaia.controller;


import br.com.gaia.api.AdapterUser;
import br.com.gaia.model.AccountInfo;
import br.com.gaia.model.UserInfo;
import br.com.gaia.model.UserRequest;
import br.com.gaia.model.UserResponse;
import br.com.gaia.service.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marionete/useraccount")
public class Controller {

    @Autowired
    ClientLogin clientLogin;

    @Autowired
    AdapterUser adapterUser;


    private String token = "";

    @PostMapping
    ResponseEntity<UserResponse> getUserResponse(@RequestBody UserRequest userRequest){
       var userAccount = UserResponse.builder()
                .accountInfo(AccountInfo.builder()
                        .accountNumber("12345-3346-3335-4456")
                        .build())
                .userInfo(UserInfo.builder()
                        .age(32)
                        .name("John")
                        .surname("Doe")
                        .sex("male")
                        .build())
                .build();
        var token = clientLogin.client(userRequest);
        setToken(token.getToken());
       return ResponseEntity.ok(userAccount);

    }

    @GetMapping("/user")
    ResponseEntity<UserInfo> getUserInfo(){
        var user = UserInfo.builder()
                .age(32)
                .name("John")
                .surname("Doe")
                .sex("male")
                .build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/account")
    ResponseEntity<AccountInfo> getAccountInfo(){
        var account = AccountInfo.builder()
                .accountNumber("12345-3346-3335-4456")
                .build();
        return ResponseEntity.ok(account);
    }




    @GetMapping("/userApi")
    ResponseEntity<UserInfo> getUserInfoApi(){
        var userInfo = adapterUser.getUserAccountApi(this.token);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/accountApi")
    ResponseEntity<AccountInfo> getAccountInfoApi(){
        var accountInfo = adapterUser.getAccountInfoApi(this.token);
        return ResponseEntity.ok(accountInfo);
    }


    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }


}
