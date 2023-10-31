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

    @Column(length = 200)
    private String memberImageUrl;
    private String memberImageName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CheckList> checkList;


    @Builder
    public Member(MemberDto.SignupReq signupReq) {
        this.memberLoginId = signupReq.getMemberId();
        this.memberPassword = signupReq.getMemberPassword();
        this.memberName = signupReq.getMemberName();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateImg(String imgUrl, String imgName) {
        this.memberImageUrl = imgUrl;
        this.memberImageName = imgName;
    }

    public void updateMemberName(String memberName) {
        this.memberName = memberName;
    }
}
