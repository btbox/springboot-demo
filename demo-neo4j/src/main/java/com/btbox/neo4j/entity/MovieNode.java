package com.btbox.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 9:22
 * @version: 1.0
 */
@Data
@Node("Movie")
public class MovieNode {


    @Id
    @GeneratedValue
    private Long id;

    @Property("title")
    private String title;

    @Property("tagline")
    private String description;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private List<Roles> actorsAndRoles = new ArrayList<>();;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private List<PersonNode> directors = new ArrayList<>();;




}