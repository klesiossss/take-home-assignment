package br.com.gaia.service;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import services.LoginRequest;
import services.LoginResponse;
import services.LoginServiceGrpc;

import static io.grpc.Status.ALREADY_EXISTS;
import static io.grpc.Status.NOT_FOUND;

@Service
public class ServiceLogin extends LoginServiceGrpc.LoginServiceImplBase {

    private Integer token;

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        try {
            responseObserver.onNext(checkout(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusException(NOT_FOUND));
            return;
        }
    }

    public LoginResponse checkout(LoginRequest login) {
        if (login.getUsername().equals("bla") && login.getPassword().equals("foo")) {
            return LoginResponse.newBuilder().setToken("1").build();
        }
        return null;
    }
}