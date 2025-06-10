package com.app.basic.domain.entity;

import com.app.basic.domain.type.ReplyStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j @Transactional @Commit
class ReplyTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void insertReplyTest(){
        Reply reply = new Reply();
        reply.setReplyContent("내 이름은 장재영");
        reply.setReplyWriter("김동건");
        reply.setReplyStatus(ReplyStatus.PUBLIC);

        entityManager.persist(reply);
    }

    @Test
    public void selectReplyTest(){
        Reply findReply = entityManager.find(Reply.class, 1L);

        log.info("reply : {}", findReply);
    }

    @Test
    public void updateReplyTest(){
        Reply findReply = entityManager.find(Reply.class, 1L);
        findReply.setReplyWriter("신제이슨");
        log.info("reply : {}", findReply);
    }

    @Test
    public void deleteReplyTest(){
        Reply findReply = entityManager.find(Reply.class, 1L);
        entityManager.remove(findReply);
        log.info("reply : {}", findReply);
    }

    @Test
    public void replySelectAllTest(){
//        JPA QL
//        1) * (와일드 카드) 사용이 불가능하다
//        테이블에 알리아스를 붙여서 모든 데이터를 조회할 수 있다
//        2) FROM 절에서 가져오는 테이블은 DB의 테이블이 아니라 엔터티 객체이다.
//        3) 타입을 알려주어야 한다.
        log.info("나는 장재영이 아닙니다. {}", entityManager.createQuery("SELECT r FROM Reply r", Reply.class).getResultList().toString());

        entityManager.createQuery("SELECT r FROM Reply r WHERE r.id = :id", Reply.class).setParameter("id", 1L).getSingleResult();
    }
}