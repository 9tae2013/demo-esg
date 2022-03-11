package com.example.demo;

import com.example.demo.entity.Company;
import com.example.demo.entity.UserProfile;
import com.example.demo.entity.draft.DraftEnvironment;
import com.example.demo.entity.draft.DraftEnvironmentalMgnt;
import com.example.demo.entity.draft.DraftReport;
import com.example.demo.entity.sent.SentReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
public class RelationTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    ObjectMapper mapper;

    @Transactional
    @Test
    public void testCreate() throws Exception {
        // prepare
        Company company = new Company();
        company.setName("comp1");
        em.persist(company);

        UserProfile updater = new UserProfile();
        updater.setName("updater");
        em.persist(updater);
        UserProfile confirmer = new UserProfile();
        confirmer.setName("confirmer");
        em.persist(confirmer);
        UserProfile approver = new UserProfile();
        approver.setName("approver");
        em.persist(approver);

        em.flush();
        em.clear();

        System.out.println("========================");
        System.out.println("Main Report page to create report");
        System.out.println("========================");
        DraftReport draftReport = new DraftReport();
        draftReport.setAsOfYear("2021");
        draftReport.setStatus("draft");
        draftReport.setStatusDate(LocalDateTime.now());
        em.persist(draftReport);

        em.flush();
        em.clear();

        System.out.println("========================");
        System.out.println("Environment page");
        System.out.println("========================");
        draftReport = em.find(DraftReport.class, draftReport.getId());
        DraftEnvironment environment = new DraftEnvironment();
        environment.setReport(draftReport); // bi-direction
        draftReport.setEnvironment(environment); // bi-direction
        em.persist(environment);
        em.persist(draftReport);

        em.flush();
        em.clear();

        System.out.println("========================");
        System.out.println("Environmental Mgnt page");
        System.out.println("========================");
        environment = em.find(DraftEnvironment.class, environment.getId());
        DraftEnvironmentalMgnt environmentalMgnt = new DraftEnvironmentalMgnt();
        environmentalMgnt.setProp1("something1");
        environmentalMgnt.setProp2("something2");
        environmentalMgnt.setProp3("something3");
        environmentalMgnt.setUpdateBy(updater);
        environmentalMgnt.setUpdateDate(LocalDateTime.now());
        environmentalMgnt.setConfirmBy(confirmer);
        environmentalMgnt.setConfirmDate(LocalDateTime.now());
        environmentalMgnt.setEnvironment(environment); // bi-direction
        environment.setEnvironmentalMgnt(environmentalMgnt); // bi-direction
        em.persist(environmentalMgnt);
        em.persist(environment);

        em.flush();
        em.clear();

        System.out.println("========================");
        System.out.println("Draft -> Sent");
        System.out.println("========================");
        EntityGraph<?> entityGraph = em.getEntityGraph("draftReport.fetch_all");
        Map<String, Object> properties = Map.of("javax.persistence.loadgraph", entityGraph);
        draftReport = em.find(DraftReport.class, draftReport.getId(), properties);
        SentReport sentReport = mapper.convertValue(draftReport, SentReport.class);
        sentReport.setApproveBy(approver);
        sentReport.setApproveDate(LocalDateTime.now());
        em.persist(sentReport);

        System.out.println("========================");
        System.out.println("Draft");
        System.out.println("========================");
        System.out.println(mapper.writeValueAsString(draftReport));
        System.out.println("========================");
        System.out.println("Sent");
        System.out.println("========================");
        System.out.println(mapper.writeValueAsString(sentReport));

        em.flush();
        em.clear();
    }
}
