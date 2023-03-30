package com.btbox.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 10:08
 * @version: 1.0
 */
@Data
public class Follows {

    @RelationshipId
    @GeneratedValue
    private Long id;


    @TargetNode
    private PersonNode person;

}