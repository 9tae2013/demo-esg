package com.example.demo;

import com.example.demo.entity.test.Post;
import com.example.demo.entity.test.PostDetails;
import com.example.demo.repository.DraftEnvironmentRepository;
import com.example.demo.repository.DraftReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {
    @PersistenceContext
    EntityManager em;
    @Autowired
    ObjectMapper mapper;

    @Transactional
    @Test
    void contextLoads() throws Exception {
        Post post = new Post();
        post.setTitle("test");

        PostDetails details = new PostDetails();
        details.setCreatedOn(LocalDate.now());
        post.setDetails(details);
        em.persist(post);
        em.persist(details);

        System.out.println(mapper.writeValueAsString(post));

        em.flush();
        em.clear();
    }

}
