package com.app.basic.domain.entity;

import com.app.basic.domain.type.MemberGender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional @Commit
class MemberTest {

    @Test
    public void ordinalTest() {
        MemberGender m = MemberGender.MALE;
        log.info("MALE : {}", m.toString());
    }

    //    옛날 방식
    @Autowired
    EntityManagerFactory ef;

    @Test
    public void memberTest() {
        EntityManager em = ef.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = new Member();

        em.persist(member);
        transaction.commit();
    }

    //    최근 방식
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void memberInsertTest() {
        Member member = new Member();
        member.setMemberEmail("test123@gmail.com");
        member.setMemberPassword("1234");
        member.setMemberName("서희동");
        member.setMemberAge(2);
        member.setMemberGender(MemberGender.MALE);

        // 영속 상태
        entityManager.persist(member);

        Member foundMember = entityManager.find(Member.class, member.getId());
        log.info("result : {}", member == foundMember);
    }

//    조회
//    마지막 회원가입한 유저를 조회 후 log 에 출력하기
    @Test
    public void findLastMemberTest() {
//        Member lastMember = entityManager.find(Member.class, 102L);
        Member lastMember = entityManager.createQuery(
        "SELECT m FROM Member m ORDER BY m.id DESC", Member.class)
                .setMaxResults(1)
                .getSingleResult();
        log.info("lastMember : {}", lastMember);
    }

    @Test
    public void memberDeleteTest() {
        Member foundMember = entityManager.find(Member.class, 102L);
        entityManager.remove(foundMember);
        log.info("result : {}", foundMember);
    }

    @Test
    public void memberMergeTest(){
//        영속 상태 -> 준 영속 상태
//        entityManager.flush(); entityManager.detach();
//        준 영속 상태 -> 영속 상태
//        entityManager.merge();
//        (영속)
        Member foundMember = entityManager.find(Member.class, 2L);
//        (준영속)
        entityManager.detach(foundMember);
        foundMember.setMemberName("마이콜");
//        (영속)
        entityManager.merge(foundMember);
        foundMember.setMemberName("또치");
        log.info("result : {}", foundMember);
    }
}
