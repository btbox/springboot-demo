package com.btbox.neo4j.repository;

import com.btbox.neo4j.entity.MovieNode;
import com.btbox.neo4j.entity.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 10:25
 * @version: 1.0
 */
public interface MovieNodeRepository extends Neo4jRepository<MovieNode, Long> {

    /**
     * 动态生成查询,根据title查询电影，无需 CypherQuery
     * @param title
     * @return
     */
    MovieNode findMovieNodeByTitle(String title);


    /**
     * 使用注解手动编写 cypher查询语句 注意：$ 是占位符，可以使用数字 0 来代替字段名称
     * @param title
     * @return
     */
    @Query("MATCH (n:Movie) WHERE n.title = $title RETURN n")
    MovieNode findMovieNodeByTitleCypher(String title);

}