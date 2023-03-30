package com.btbox.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 9:33
 * @version: 1.0
 */
@Data
@Node("Person")
@NoArgsConstructor
public class PersonNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Property("born")
    private Integer born;

//    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.INCOMING)
//    private List<Follows> follows;


    public PersonNode(Integer born, String name) {
        this.name = name;
        this.born = born;
    }

}