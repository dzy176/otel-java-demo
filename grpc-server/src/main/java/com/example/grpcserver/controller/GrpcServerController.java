package com.example.grpcserver.controller;

import com.example.grpcserver.entity.NodeBasic;
import com.example.grpcserver.mapper.NodeBasicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GrpcServerController {
    @Autowired
    private final NodeBasicMapper nodeBasicMapper;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/ping")
    public String ping() {
        return "hello grpc-server";
    }

    @GetMapping("/mysql")
    public NodeBasic getNode(Integer id) {
        // 查询数据库中的第一个节点
        NodeBasic nodeBasic = nodeBasicMapper.selectById(id);
        if (nodeBasic == null) {
            return new NodeBasic(0, "Node not found", 0, 0);
        }
        return nodeBasic;
    }

    @GetMapping("/jdbc")
    public String jdbc(Integer id) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM node_basic WHERE node_id = ?", id);
        return results.toString();
    }
}
