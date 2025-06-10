package com.app.basic.domain.entity;

import com.app.basic.domain.type.MemberGender;
import jakarta.persistence.*;
import lombok.*;

// 1. PK 키가 필요하다.
// 2. *기본 생성자, *초기화 생성자, Getter, Setter, ToString 재정의, Hash|Equals 재정의
// 3.

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
@Entity @Table(name = "TBL_MEMBER")
public class Member {

    @Id
    @GeneratedValue // auto, JPA가 시퀀스를 관리
//    @GeneratedValue(strategy = GenerationType.AUTO) // default 오라클 -> SEQUENCE, MySql 이라면 Identity
    private Long id;

    @Column(name="MEMBER_EMAIL")
    private String memberEmail;
    private String memberName;
    private String memberPassword;
    private Integer memberAge;

    @Enumerated(EnumType.STRING)
    private MemberGender memberGender;
}
