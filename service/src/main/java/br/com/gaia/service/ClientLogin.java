package br.com.gaia.service;

import br.com.gaia.model.UserRequest;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import services.LoginRequest;
import services.LoginResponse;
import services.LoginServiceGrpc;

@Component
public class ClientLogin {

    public LoginResponse  client(UserRequest userRequest){
    var channel = ManagedChannelBuilder
            .forAddress("localhost", 9091)
            .usePlaintext()
            .build();

    var client = LoginServiceGrpc.newBlockingStub(channel);
    var loginRequest = LoginRequest.newBuilder().setUsername(userRequest.getUsername()).setPassword(userRequest.getPassword()).build();
    LoginResponse loginResponse = client.login(loginRequest);
    System.out.println("Token: " + loginResponse.getToken());
    return loginResponse;
}

}
