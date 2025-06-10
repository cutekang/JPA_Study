package com.app.basic.domain.entity;


import com.app.basic.domain.type.ReplyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "TBL_REPLY")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reply {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include // 포함
    private Long id;
//    @EqualsAndHashCode.Exclude 제거
    private String replyContent;
    private String replyWriter;
    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus;
}
