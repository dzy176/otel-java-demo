package com.example.httptogrpc.controller;

import demoGrpc.DemoRequest;
import demoGrpc.DemoResponse;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import demoGrpc.DemoServiceGrpc;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class HttpToGrpcController {
    @Value("${DEMO_GRPC_SERVER:localhost:8181}")
    private String grpcServerAddress; // gRPC 服务器地址

    private ManagedChannel channel;
    private DemoServiceGrpc.DemoServiceBlockingStub demoServiceBlockingStub;


    @PostConstruct
    public void init() {
        // 初始化 gRPC 通道和存根
        channel = io.grpc.ManagedChannelBuilder.forTarget(grpcServerAddress)
                .usePlaintext()
                .build();
        demoServiceBlockingStub = DemoServiceGrpc.newBlockingStub(channel);
    }

    @GetMapping("/ping")
    public String ping() {
        return "hello http-to-grpc";
    }

    @GetMapping("/mysql")
    public String mysql() {
        DemoRequest request = DemoRequest.newBuilder()
                .setIn("mysql")
                .build();
        DemoResponse response = demoServiceBlockingStub.demoMethod(request);
        if (response == null) {
            return "Node not found";
        }

        return response.getOut();
    }
}
