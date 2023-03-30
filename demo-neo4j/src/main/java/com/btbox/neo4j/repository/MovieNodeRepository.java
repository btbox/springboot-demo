package com.btbox.neo4j.repository;

import com.btbox.neo4j.entity.MovieNode;
import com.btbox.neo4j.entity.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 10:25
 * @version: 1.0
 */
public interface MovieNodeRepository extends Neo4jRepository<MovieNode, Long> {



}