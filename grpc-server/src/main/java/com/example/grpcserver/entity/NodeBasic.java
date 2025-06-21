package com.example.grpcserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("node_basic")        // 对应数据库表名
public class NodeBasic {
    @TableId(type = IdType.INPUT)
    Integer nodeId;
    String nodeName;
    Integer prov;
    Integer isOffline;
}
