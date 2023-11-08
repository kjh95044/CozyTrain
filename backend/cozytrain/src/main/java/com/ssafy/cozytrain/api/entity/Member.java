package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @Column(length = 255, columnDefinition = "VARCHAR(255) DEFAULT 'https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg'")
    private String memberImageUrl;
    private String memberImageName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Dream> dream;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(mappedBy = "memberFirst")
    private List<Friend> friendMemberFirst;

    @OneToMany(mappedBy = "memberSecond")
    private List<Friend> friendMemberSecond;

    @OneToMany(mappedBy = "memberFirst")
    private List<ChatRoom> chatMemberFirst;

    @OneToMany(mappedBy = "memberSecond")
    private List<ChatRoom> chatMemberSecond;

    @OneToMany(mappedBy = "senderMember")
    private List<Message> message;

    @OneToOne(mappedBy = "member")
    private Train train;

    @OneToMany(mappedBy = "member")
    private List<ItemBox> itemBoxes;

    @OneToMany(mappedBy = "member")
    private List<ItemList> itemLists;

    @ManyToOne
    @JoinColumn(name = "item_list_id", referencedColumnName = "item_list_id")
    private ItemList item;

    @Builder
    public Member(MemberDto.SignupReq signupReq) {
        this.memberLoginId = signupReq.getMemberId();
        this.memberPassword = signupReq.getMemberPassword();
        this.memberName = signupReq.getMemberName();
        this.memberImageUrl = "https://cozytrain.s3.ap-northeast-2.amazonaws.com/profile/profile.jpg";
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

    public void updateMemberCollection(ItemList item){
        this.item = item;
    }
}
