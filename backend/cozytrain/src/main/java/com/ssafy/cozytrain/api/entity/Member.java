package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    private String memberLoginId;
    @NotNull
    @Column(length = 100)
    private String memberPassword;

    private String memberName;
    private String memberAge;

    @Column(length = 200)
    private String memberImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CheckList> checkList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Report> reports;


    @Builder
    public Member(MemberDto.SignupReq signupReq) {
        this.memberLoginId = signupReq.getMemberId();
        this.memberPassword = signupReq.getMemberPassword();
        this.memberName = signupReq.getMemberName();
        this.memberAge = signupReq.getMemberAge();
//        this.memberImageUrl = signupReq.getMemberProfileImg();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
