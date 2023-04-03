package com.btbox.neo4j;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson.JSONObject;
import com.apifan.common.random.RandomSource;
import com.apifan.common.random.constant.CompetitionType;
import com.btbox.neo4j.entity.MovieNode;
import com.btbox.neo4j.entity.PersonNode;
import com.btbox.neo4j.entity.Roles;
import com.btbox.neo4j.repository.MovieNodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        // repository 方式

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


        // neo4JTemplate 方式
       // neo4JTemplate.save(movieNode);

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

    @Test
    public void findByOne() {
        // 根据id查询
        Optional<MovieNode> movieNode = movieNodeRepository.findById(55246L);
        log.info("根据id查询:" + movieNode);

        MovieNode movieNodeByTitle = movieNodeRepository.findMovieNodeByTitle("你的名字");
        log.info("根据title查询:" + movieNodeByTitle);

        /**
         * 根据cypher来查询
         */
        MovieNode movieNodeByTitleCypher = movieNodeRepository.findMovieNodeByTitleCypher("你的名字");
        log.info("根据cypher的title查询:" + movieNodeByTitleCypher);
        

    }

    @Test
    public void update() {
        // repository 方式
        // 注意如果实体的id是相同的则是修改，否则是新增
        MovieNode movieNode = new MovieNode();
        movieNode.setId(55246L);
        movieNode.setTitle("你的名字");
        movieNode.setDescription("影片讲述了男女高中生在梦中相遇，并寻找彼此的故事。交换身体并且相遇相识相爱");
        // movieNodeRepository.save(movieNode);

        // template方式
        neo4JTemplate.save(movieNode);


    }

    @Test
    public void remote() {
        // 根据id删除单个
        movieNodeRepository.deleteById(55246L);

        // 根据id删除多个
        movieNodeRepository.deleteAllById(Collections.singletonList(55246L));

    }


}