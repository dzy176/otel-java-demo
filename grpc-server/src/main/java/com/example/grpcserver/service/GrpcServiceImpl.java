package com.example.grpcserver.service;

import com.example.grpcserver.entity.NodeBasic;
import com.example.grpcserver.mapper.NodeBasicMapper;
import demoGrpc.DemoRequest;
import demoGrpc.DemoResponse;
import demoGrpc.DemoServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Random;

@GrpcService
@RequiredArgsConstructor
public class GrpcServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {
    @Autowired
    private final NodeBasicMapper nodeBasicMapper;
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void demoMethod(DemoRequest request, io.grpc.stub.StreamObserver<DemoResponse> responseObserver) {
        System.out.println("Received request: " + request.getIn());
        // 处理请求并构建响应
        if (request.getIn().equals("mysql")) {
            Random random = new Random();
            Integer randomInt = random.nextInt(30);
            if (randomInt%2==0) {
                NodeBasic nodeBasic = nodeBasicMapper.selectById(randomInt);
                if (nodeBasic == null) {
                    responseObserver.onError(new RuntimeException("Node not found"));
                    return;
                }
                DemoResponse response = DemoResponse.newBuilder()
                        .setOut(nodeBasic.toString())
                        .build();
                // 返回响应
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            } else {
                List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM node_basic WHERE node_id = ?", randomInt);
                DemoResponse response = DemoResponse.newBuilder()
                        .setOut(results.toString())
                        .build();
                // 返回响应
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }

        }
        responseObserver.onError(new RuntimeException("unknown input"));
    }

}
