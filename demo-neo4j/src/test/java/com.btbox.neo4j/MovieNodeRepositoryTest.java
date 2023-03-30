package com.btbox.neo4j;

import com.alibaba.fastjson2.JSONObject;
import com.btbox.neo4j.entity.MovieNode;
import com.btbox.neo4j.entity.PersonNode;
import com.btbox.neo4j.entity.Roles;
import com.btbox.neo4j.repository.MovieNodeRepository;
import com.btbox.neo4j.repository.PersonNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: BT-BOX(HJH)
 * @createDate: 2023/3/30 10:52
 * @version: 1.0
 */
@SpringBootTest
@Slf4j
public class MovieNodeRepositoryTest {

    @Autowired
    private MovieNodeRepository movieNodeRepository;

    @Autowired
    private Neo4jTemplate neo4JTemplate;

    /**
     * 新增person数据
     */
    @Test
    public void save() {

        MovieNode movieNode = new MovieNode();
        movieNode.setTitle("你的名字");
        movieNode.setDescription("影片讲述了男女高中生在梦中相遇，并寻找彼此的故事。");

        Roles roles1 = new Roles();
        roles1.setRoles(Collections.singletonList("宫水三叶"));
        roles1.setPerson(new PersonNode(1998,"上白石萌音"));
        Roles roles2 = new Roles();
        roles2.setRoles(Collections.singletonList("立花泷"));
        roles2.setPerson(new PersonNode(1993,"神木隆之介"));

        PersonNode director = new PersonNode(1973,"新海诚");

        movieNode.getActorsAndRoles().add(roles1);
        movieNode.getActorsAndRoles().add(roles2);
        movieNode.getDirectors().add(director);

        movieNodeRepository.save(movieNode);

    }

    /**
     * 获取 person 节点的所有数据
     */
    @Test
    public void findAll() {
        // 1. 查询当前节点类型下所有数据
        List<MovieNode> movieNodesAll = movieNodeRepository.findAll();
        log.info(JSONObject.toJSONString(movieNodesAll));

        // 2. 查询当前节点类型下所有数据并分页
        Pageable pageable = PageRequest.of(0, 10);
        Page<MovieNode> movieNodesPage = movieNodeRepository.findAll(pageable);
        log.info(JSONObject.toJSONString(movieNodesPage));

        // 3. 排序
        Sort sort = Sort.by(Sort.Direction.DESC, "born");
        List<MovieNode> movieNodesSort = movieNodeRepository.findAll(sort);
        log.info(JSONObject.toJSONString(movieNodesSort));


        // 4. neo4JTemplate 版本
        List<MovieNode> movieNodesTemplate = neo4JTemplate.findAll(MovieNode.class);
        log.info(JSONObject.toJSONString(movieNodesTemplate));
    }


}