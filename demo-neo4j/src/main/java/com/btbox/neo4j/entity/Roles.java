package com.btbox.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 9:47
 * @version: 1.0
 */
@Data
@RelationshipProperties
public class Roles {

    @RelationshipId
    @GeneratedValue
    private Long id;

    @Property
    private List<String> roles;

    @TargetNode
    private PersonNode person;

}